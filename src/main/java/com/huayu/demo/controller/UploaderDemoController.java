package com.huayu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.controller.BaseController;
import com.huayu.core.exception.ApplicationException;

@Controller
@RequestMapping("/uploaderdemo")
public class UploaderDemoController extends BaseController{
	@NoLoginRequired
	@RequestMapping(value="/uploadtest", method=RequestMethod.GET)
	public String uploadtest() throws ApplicationException{
		return "uploadtest.jsp";
	}
	
	@NoLoginRequired
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(){
		return "test.jsp";
	}
	
	@NoLoginRequired
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public void upload(@RequestParam("file") MultipartFile[] files) throws ApplicationException{
		LOG.debug(files.length);
	}
}
