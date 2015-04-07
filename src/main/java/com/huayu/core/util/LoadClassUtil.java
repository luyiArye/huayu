package com.huayu.core.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;

@Component
public class LoadClassUtil {
	protected Logger LOG=Logger.getLogger(this.getClass());
	
	//定义类加载器	
	private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	
	private static final String FILE_PROTOCOL="file";
	private static final String JAR_PROTOCOL="jar";
	
	
	/**
	 * 加载指定包下的所有类（可指定注解）
	 * @param packages
	 * @param annotation
	 * @return
	 * @throws IOException 
	 */
	public List<Class<?>> loadClass(String packageName, Class<? extends Annotation> annotation) throws ApplicationException{
		List<String> packagesName=new ArrayList<String>();
		packagesName.add(packageName);
		
		return loadClass(packagesName, annotation);
	}
	
	/**
	 * 加载指定包下的所有类（可指定注解）
	 * @param packages
	 * @param annotation
	 * @return
	 * @throws IOException 
	 */
	public List<Class<?>> loadClass(List<String> packagesName, final Class<? extends Annotation> annotation) throws ApplicationException{
		final List<Class<?>> classList=new ArrayList<Class<?>>();
		
		List<String> packagesPath=null;
		try {
			packagesPath=getPackagesPath(packagesName);
		} 
		catch (IOException e) {
			LOG.error(e.getMessage(), e);
			throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE, e);
		}
		
		if(packagesPath!=null && !packagesPath.isEmpty()){
			for(final String pkgPath: packagesPath){
				Enumeration<URL> pkgUrls=null;
				try {
					pkgUrls = classLoader.getResources(pkgPath);
				} 
				catch (IOException e) {
					LOG.error(e.getMessage(), e);
					throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE, e);
				}
				
				while(pkgUrls.hasMoreElements()) {
					URL pkgUrl = pkgUrls.nextElement();
					String protocol = pkgUrl.getProtocol();
					
					if(FILE_PROTOCOL.equals(protocol)){
						String path = pkgUrl.getPath();
						File file=new File(path);
						
						//如果有.class文件则返回
						file.listFiles(new FileFilter() {
							public boolean accept(File f) {
								String fileName=f.getName();
								int classTag=fileName.lastIndexOf(".class");
								if(f.isFile() && classTag!=-1){
									addClass(pkgPath+fileName, classList, annotation);
									return true;
								}
								
								return false;
							}
						});
					}
					else if(JAR_PROTOCOL.equals(protocol)){
						JarURLConnection jarURLConnection=null;
						JarFile jarFile = null;
						try {
							jarURLConnection = (JarURLConnection) pkgUrl.openConnection();
							jarFile=jarURLConnection.getJarFile();
						} 
						catch (IOException e) {
							LOG.warn(e.getMessage(), e);
						}
						
						Enumeration<JarEntry> jarEntries = jarFile.entries();
						while (jarEntries.hasMoreElements()) {
							JarEntry jarEntry = jarEntries.nextElement();
							//获取类路径 egsun/security/internal/interfaces/TlsMasterSecret.class
							String jarEntryName = jarEntry.getName();
							if(jarEntryName.endsWith(".class")){
								//class类文件则是需要加载的Class
								addClass(jarEntryName, classList, annotation);
							}
						}
					}
				}
			}
		}
		
		return classList;
	}
	
	/**
	 * 添加匹配的类
	 * @param classPath
	 * @param classList
	 * @param annotation
	 * @throws ApplicationException
	 */
	private void addClass(String classPath, List<Class<?>> classList, Class<? extends Annotation> annotation){
		classPath=classPath.substring(0, classPath.lastIndexOf(".class"));
		classPath=classPath.replaceAll("/", ".");
		
		LOG.debug("load class: "+classPath);
		
		Class<?> clazz=null;
		try {
			//反射获取class实例
			clazz = Class.forName(classPath);
		} 
		catch (NoClassDefFoundError e) {
			LOG.warn(e.getMessage());
			return;
		} 
		catch (ClassNotFoundException e) {
			LOG.warn(e.getMessage());
			return;
		}
		
		//注解不为空则需要判断类的注解
		if(annotation!=null){
			if(clazz.isAnnotationPresent(annotation)){
				classList.add(clazz);
			}
		}
		else{
			classList.add(clazz);
		}
	}
	
	/**
	 * 获取所有匹配的包
	 * @param packages
	 * @return
	 * @throws IOException
	 */
	private List<String> getPackagesPath(List<String> packages) throws IOException{
		List<String> packagesPath=new ArrayList<String>();
		
		if(packages!=null && !packages.isEmpty()){
			for(String pkg: packages){
				String filePath = pkg.replaceAll("\\.", "/");
				if(!filePath.endsWith("/")){
					filePath+="/";
				}
				
				if(filePath.indexOf("*")!=-1){
					String subPath=filePath.substring(0, filePath.indexOf("*"));
					
					//替换第一个星号
					Enumeration<URL> subUrls=classLoader.getResources(subPath);
					
					while(subUrls.hasMoreElements()) {
						URL subUrl = subUrls.nextElement();
						String protocol = subUrl.getProtocol();
						
						if(FILE_PROTOCOL.equals(protocol)){
							// 本地自己可见的代码
							String subPkgPath = subUrl.getPath();
							File subPkgFile=new File(subPkgPath);
							
							//获取目录下所有的文件夹
							File[] replaceDics=subPkgFile.listFiles(new FileFilter() {
								public boolean accept(File file) {
									// 如果为文件夹则返回
									return file.isDirectory();
								}
							});
							
							//将星号替换问文件夹的名字
							if(replaceDics!=null){
								for(File f: replaceDics){
									packagesPath.add(subPath+f.getName()+filePath.substring(filePath.indexOf("*")+1));
								}
							}
						}
						else if (JAR_PROTOCOL.equals(protocol)) {
							// 引用第三方jar的代码
							JarURLConnection jarURLConnection = (JarURLConnection) subUrl.openConnection();
							JarFile jarFile = jarURLConnection.getJarFile();
							Enumeration<JarEntry> jarEntries = jarFile.entries();
							while (jarEntries.hasMoreElements()) {
								JarEntry jarEntry = jarEntries.nextElement();
								//获取类路径 egsun/security/internal/interfaces/TlsMasterSecret.class
								String jarEntryName = jarEntry.getName();
								if(jarEntryName.lastIndexOf(".class")==-1){
									//如果不是class文件则匹配路径
									//按规则匹配包路径 org/springframework/*/io
									String[] targetPkgRule=filePath.split("/");
									String[] classPkg=jarEntryName.split("/");
									boolean matchFlag=true;
									if(matchFlag=(targetPkgRule.length==classPkg.length)){
										for(int i=0;i<targetPkgRule.length;i++){
											//如果不能与规则匹配则将标识设置为flase
											if(!targetPkgRule[i].equals(classPkg[i]) && !"*".equals(targetPkgRule[i])){
												matchFlag=false;
											}
										}
									}
									
									if(matchFlag){
										//如果包路径匹配则记录
										packagesPath.add(jarEntryName);
									}
								}
							}
						}
					}
					
					//递归调用
					getPackagesPath(packagesPath);
				}
				else{
					packagesPath.add(filePath);
				}
			}
		}
		
		return packagesPath;
	}
}