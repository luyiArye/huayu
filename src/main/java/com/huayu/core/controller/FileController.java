package com.huayu.core.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.bean.vo.HuayuResources;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.service.IFileService;

/**
 * 文件操作相关类
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/file")
public class FileController extends BaseController{
	@Autowired
	private IFileService fileService;
	
	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/upload/{fileType}", method=RequestMethod.POST)
	@ResponseBody
	public DataResponse upload(@PathVariable("fileType") int fileType, @RequestParam("file") MultipartFile[] files) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		List<HuayuResources> resourceList=fileService.upload(files, getLoginUser(), fileType);
		dataResponse.setData(resourceList);
		
		return dataResponse;
	}
	
	/**
	 * 读取文件
	 * @param resourceId
	 * @throws ApplicationException
	 * @throws IOException 
	 */
	@NoLoginRequired
	@RequestMapping(value="/read/{resourceId}", method=RequestMethod.GET)
	public void read(@PathVariable("resourceId") int resourceId) throws ApplicationException, IOException{
		HuayuResources huayuResource=fileService.read(resourceId);
		
		if(huayuResource==null){
			//输出异常图片
			outNotFoundImage();
		}
		
		String fileName=huayuResource.getResourceName();
		String contentType=huayuResource.getContentType();
		response.setContentType(contentType);
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
		
		OutputStream os=null;
		InputStream is=null;
		try {
			is=new FileInputStream(huayuResource.getResourcePath());
			os=response.getOutputStream();
			
			byte[] bytes=new byte[is.available()];
			while(is.read(bytes)>0){
				os.write(bytes);
			}
			os.flush();
		} 
		catch (IOException e) {
			if(contentType.toLowerCase().startsWith("image/")){
				//输出异常图片
				outNotFoundImage();
			}
			else{
				throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE, e);
			}
		}
		finally{
			if(os!=null){
				os.close();
			}
			if(is!=null){
				is.close();
			}
		}
	}
	
	/**
	 * 输出没有找到文件的异常图片
	 * @throws IOException
	 */
	private void outNotFoundImage() throws IOException{
		OutputStream exOs=null;
		InputStream exIs=null;
		try{
			exIs=new FileInputStream(getWebappPath("/images/")+File.separator+"notFound.jpg");
			exOs=response.getOutputStream();
			
			byte[] bytes=new byte[exIs.available()];
			while(exIs.read(bytes)>0){
				exOs.write(bytes);
			}
			exOs.flush();
		}
		finally{
			if(exOs!=null){
				exOs.close();
			}
			if(exIs!=null){
				exIs.close();
			}
		}
	}
}
