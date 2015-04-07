package com.huayu.core.mail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkTools {
	protected Logger LOG = Logger.getLogger(this.getClass());

	/** 存放模板文件夹的路径 ***/
	private static String baseDir = "classpath:template/mail/";
	private static Configuration cfg = new Configuration();

	/** 编码格式 ***/
	private static String charset = "UTF-8";

	/**
	 * 根据路径获取模板
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Template templateFactory(String path) throws IOException {
		ResourcePatternResolver r = new PathMatchingResourcePatternResolver();
		Resource resource = r.getResource(baseDir);
		File file = new File(resource.getURI());
		FileTemplateLoader templateLoader = new FileTemplateLoader(file);
		cfg.setTemplateLoader(templateLoader);
		Template t = cfg.getTemplate(path, charset);

		return t;
	}

	/**
	 * 根据指定的模板引用freenark生成字符串格式
	 * 
	 * @param path
	 * @param root
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static String getResult(String path, Map<String, Object> param) throws TemplateException,
			IOException {
		Template t = templateFactory(path);
		ByteArrayOutputStream bos = null;
		StringBuffer sbuff = new StringBuffer();
		bos = new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(bos, charset);
		t.process(param, out);
		sbuff.append(new String(bos.toByteArray(), charset));
		return sbuff.toString();
	}
}
