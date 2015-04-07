package com.huayu.core.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.bean.BootstrapValidatorResult;
import com.huayu.core.exception.ApplicationException;

/**
 * 验证码
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/vcode")
public class CaptchaController extends BaseController{
	@Autowired  
    private Producer captchaProducer; 
	
	/**
	 * 校验验证码是否正确
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/checkVCode", method=RequestMethod.POST)
	public BootstrapValidatorResult checkVCode(@RequestParam("vCode_") String vCode) throws ApplicationException{
		BootstrapValidatorResult bvr=new BootstrapValidatorResult();
		
		//比较输入的验证码也输入的验证码是否相同（不区分大小写）
		bvr.setValid(getVCodeText().equalsIgnoreCase(vCode));
		
		return bvr;
	}
	
	/**
	 * 输出验证码图片
	 * @throws IOException
	 */
	@NoLoginRequired
	@RequestMapping(value="/image", method = RequestMethod.GET) 
    public void getKaptchaImage() throws IOException {
		Object codeObj=session.getAttribute(HuayuConstants.V_CODE_SESSION_KEY);
		String code=null;
		if(codeObj!=null){
			code=codeObj.toString();
		}
		else{
			code = captchaProducer.createText();
		}
		session.setAttribute(HuayuConstants.V_CODE_SESSION_KEY, code);  
		
		response.setDateHeader("Expires", 0);  
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  
        // return a jpeg  
        response.setContentType("image/jpeg"); 
        
        BufferedImage bi = captchaProducer.createImage(code);  
        ServletOutputStream out = response.getOutputStream();  
          
        // write the data out  
        try {  
        	ImageIO.write(bi, "jpg", out);
            out.flush();  
        } 
        finally {  
            out.close();  
        }  
	}
	
	/**
	 * 刷新验证码
	 * @throws IOException
	 */
	@NoLoginRequired
	@RequestMapping(value="/refresh", method = RequestMethod.GET) 
	public void refresh() throws IOException{
		String code = captchaProducer.createText();
		session.setAttribute(HuayuConstants.V_CODE_SESSION_KEY, code);
		getKaptchaImage();
	}
}
