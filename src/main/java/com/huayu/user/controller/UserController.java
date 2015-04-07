package com.huayu.user.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Producer;
import com.huayu.core.HuayuConstants;
import com.huayu.core.annotation.DataValidation;
import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.annotation.OperationTimeInterval;
import com.huayu.core.annotation.RequiredVCode;
import com.huayu.core.bean.BootstrapValidatorResult;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.bean.vo.User;
import com.huayu.core.bean.vo.UserHeadIco;
import com.huayu.core.controller.BaseController;
import com.huayu.core.controller.FileController;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.service.IUserService;
import com.huayu.user.service.IUserStatisticsService;

/**
 * 用户
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	@Autowired  
    private Producer captchaProducer; 
	@Autowired
	private IUserStatisticsService userStatisticsService;
	
	@Autowired
	private FileController fileController;
	
	/**
	 * 登录页面
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login() throws ApplicationException{
		if(getLoginUser()!=null){
			//如果已经登录则跳转到首页
			return index();
		}
		
		modelAndView.addObject("ofc", getOfc());
		modelAndView.setViewName("user/login.jsp");
		return modelAndView;
	}
	
	/**
	 * 登录验证
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@OperationTimeInterval(value=3)
	@RequiredVCode(failedCount=3)
	@RequestMapping(value="/checkLogin", method=RequestMethod.POST)
	public DataResponse checkLogin(@ModelAttribute User user) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		User loginUser=userService.checkLogin(user);
		
		//将登录的用户信息保存到session
		session.setAttribute(HuayuConstants.LOGIN_USER_SESSION_KEY, loginUser);
		dataResponse.setData(loginUser);
		
		//登录成功跳转到首页
		return dataResponse;
	}
	
	/**
	 * 注销
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/loginOut", method=RequestMethod.GET)
	public ModelAndView loginOut() throws ApplicationException{
		session.removeAttribute(HuayuConstants.LOGIN_USER_SESSION_KEY);
		
		return index();
	}
	
	/**
	 * 注册页面
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register() throws ApplicationException{
		String sessionVCode=captchaProducer.createText();
		session.setAttribute(HuayuConstants.V_CODE_SESSION_KEY, sessionVCode);  
		modelAndView.setViewName("user/register.jsp");
		return modelAndView;
	}
	
	/**
	 * 注册页面
	 * @return
	 * @throws ApplicationException
	 */
	@RequiredVCode
	@NoLoginRequired
	@RequestMapping(value="/saveRegister", method=RequestMethod.POST)
	public ModelAndView saveRegister(@ModelAttribute @DataValidation User user) throws ApplicationException{
		//新增用户
		userService.insertUser(user);
		modelAndView.addObject(HuayuConstants.PAGE_MESSAGE_KEY, "注册成功！");
		
		//查询注册的用户信息
		checkLogin(user);
		
		return goSuccess();
	}
	
	/**
	 * 校验邮箱是否已经注册
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/checkUserAccount", method=RequestMethod.POST)
	public BootstrapValidatorResult checkUserAccount(@ModelAttribute User user) throws ApplicationException{
		BootstrapValidatorResult bvr=new BootstrapValidatorResult();
		
		//检查是否存在指定的用户
		bvr.setValid(!userService.checkExistsUser(user));
		
		return bvr;
	}
	
	/**
	 * 加载用户头像
	 * @param headIcoType
	 * @param userId
	 * @throws ApplicationException 
	 * @throws IOException 
	 */
	@NoLoginRequired
	@RequestMapping(value="/headIco/{headIcoType}/{userId}", method=RequestMethod.GET)
	public void headIco(UserHeadIco userHeadIco) throws ApplicationException, IOException{
		UserHeadIco uhi=userService.selectHeadIco(userHeadIco);
		if(uhi!=null && uhi.getResourceId()!=null){
			int resourceId=uhi.getResourceId().intValue();
			try {
				fileController.read(resourceId);
			} 
			catch (IOException e) {
				LOG.warn(e.getMessage(), e);
				//显示默认头像
				outDefaultHeadIco();
			}
		}
		else{
			//如果没有头像则显示默认头像
			outDefaultHeadIco();
		}
	}
	
	/**
	 * 输出默认头像
	 * @throws IOException
	 * @throws ApplicationException
	 */
	private void outDefaultHeadIco() throws IOException, ApplicationException{
		String defaultIcoPath=getWebappPath("/images")+"/user_head.png";
		
		response.setContentType("image/png");
		response.setCharacterEncoding("UTF-8");
		
		OutputStream os=null;
		InputStream is=null;
		try {
			is=new FileInputStream(defaultIcoPath);
			os=response.getOutputStream();
			
			byte[] bytes=new byte[is.available()];
			while(is.read(bytes)>0){
				os.write(bytes);
			}
			os.flush();
		} 
		catch (IOException e) {
			throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE);
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
	 * 检查登录
	 * @author arye.luyi Administrator
	 * @date 2015-3-15 下午4:54:00
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/checkLogin", method=RequestMethod.GET)
	public DataResponse checkLogin() throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		//设置登录用户信息
		dataResponse.setData(getLoginUser());
		
		return dataResponse;
	}
	
	/**
	 * 用户空间页面
	 * @author arye.luyi Administrator
	 * @date 2015-3-18 下午5:49:43
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/room/{id}", method=RequestMethod.GET)
	public ModelAndView room(User user) throws ApplicationException{
		User u=userService.selectUserById(user);
		
		modelAndView.addObject("user", u);
		
		modelAndView.setViewName("user/room.jsp");
		return modelAndView;
	}
	
	/**
	 * 用户相关统计
	 * @param user
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@ResponseBody
	@RequestMapping(value="/statistics/{id}", method=RequestMethod.GET)
	public DataResponse statistics(User user) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		dataResponse.setData(userStatisticsService.statistics(user));
		
		return dataResponse;
	}
}
