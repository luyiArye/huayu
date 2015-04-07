package com.huayu.core.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;

@Scope("request")
@Controller
@RequestMapping("/")
public class BaseController {
	protected Logger LOG = Logger.getLogger(this.getClass());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ModelAndView modelAndView;

	@ModelAttribute
	public void initController(HttpServletRequest request,
			HttpServletResponse response, ModelAndView modelAndView) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.modelAndView = modelAndView;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	@NoLoginRequired
	public ModelAndView index() throws ApplicationException{
		modelAndView.setViewName("index.jsp");
		return modelAndView;
	}

	/**
	 * 获取国际化资源信息
	 * 
	 * @param code
	 * @return
	 */
	protected String getLocalMessage(String code) {
		RequestContext requestContext = new RequestContext(request);
		return requestContext.getMessage(code);
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @return
	 */
	protected String getClientIP() {
		String ipAddress = null;
		// ipAddress = request.getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	protected DataResponse getDataResponse() {
		return new DataResponse();
	}
	
	/**
	 * 获取登录用户信息
	 * @return
	 */
	protected User getLoginUser(){
		User u=null;
		
		Object userObj=session.getAttribute(HuayuConstants.LOGIN_USER_SESSION_KEY);
		if(userObj!=null){
			u=(User)userObj;
		}
		
		return u;
	}
	
	/**
	 * 获取验证码文本
	 * @return
	 */
	protected String getVCodeText(){
		String vCode="";
		
		Object vCodeObj=session.getAttribute(HuayuConstants.V_CODE_SESSION_KEY);
		if(vCodeObj!=null){
			vCode=vCodeObj.toString();
		}
		
		return vCode;
	}
	
	/**
	 * 转到成功页面
	 * @return
	 */
	public ModelAndView goSuccess() throws ApplicationException{
		modelAndView.setViewName("success/common.jsp");
		return modelAndView;
	}
	
	/**
	 * 获取操作失败的次数
	 * @return
	 * @throws ApplicationException
	 */
	@RequestMapping(value = "/getOfc", method = RequestMethod.GET)
	@ResponseBody
	@NoLoginRequired
	public DataResponse getOfc() throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		int ofc=0;
		Object ofcObj=session.getAttribute(HuayuConstants.OPERATION_FAILED_COUNT);
		if(ofcObj!=null){
			ofc=Integer.parseInt(ofcObj.toString());
		}
		dataResponse.setData(ofc);
		
		return dataResponse;
	}
	
	protected String getWebappPath(String path) {
		if(path==null || "".equals(path.trim())){
			path="/";
		}
		return session.getServletContext().getRealPath(path);
	}
	
	/**
	 * 
	 * @author arye.luyi Administrator
	 * @date 2015-3-14 下午8:29:49
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/loadFaces", method=RequestMethod.GET)
	public DataResponse loadFaces() throws ApplicationException{
		if(HuayuConstants.FACE_IMAGES.isEmpty()){
			String facesPath=getWebappPath(HuayuConstants.FACE_IMAGES_FOLDER);
			File facesFloder=new File(facesPath);
			if(facesFloder.isDirectory()){
				String imageName=null;
				File[] faceImagesFile=facesFloder.listFiles();
				if(faceImagesFile!=null){
					for(File f: faceImagesFile){
						if(f.isFile()){
							imageName=f.getName();
							
							HuayuConstants.FACE_IMAGES.add(imageName.substring(0, imageName.lastIndexOf(".")));
						}
					}
				}
			}
		}
		
		DataResponse dataResponse=getDataResponse();
		dataResponse.setData(HuayuConstants.FACE_IMAGES);
		
		return dataResponse;
	}
}
