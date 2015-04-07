package com.huayu.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huayu.core.annotation.NoLoginRequired;
import com.huayu.core.annotation.RequiredVCode;
import com.huayu.core.bean.DataResponse;
import com.huayu.core.bean.vo.User;
import com.huayu.core.controller.BaseController;
import com.huayu.core.exception.ApplicationException;
import com.huayu.user.service.IForgetPwdService;

/**
 * 忘记密码
 * @author Administrator
 *
 */
@Scope("request")
@Controller
@RequestMapping("/forgetPwd")
public class ForgetPwdController extends BaseController{
	@Autowired
	private IForgetPwdService forgetPwdService;
	
	/**
	 * 忘记密码
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ModelAndView forgetPwd() throws ApplicationException{
		modelAndView.setViewName("user/forgetPwd.jsp");
		return modelAndView;
	}
	
	/**
	 * 提交忘记密码
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequiredVCode
	@ResponseBody
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public DataResponse submitForgetPwd(User user) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		StringBuffer basePath=new StringBuffer();
		basePath.append(request.getScheme())
		.append("://")
		.append(request.getServerName())
		.append(":")
		.append(request.getServerPort())
		.append(request.getContextPath())
		.append("/");

		forgetPwdService.submitForgetPwd(user, basePath.toString());
		
		return dataResponse;
	}
	
	/**
	 * 重置密码页面
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequestMapping(value="/resetPwd", method=RequestMethod.GET)
	public ModelAndView resetPwd(@RequestParam("fv") String fv) throws ApplicationException{
		User user=forgetPwdService.resetPwdParseUser(fv);
		modelAndView.addObject("user", user);
		
		modelAndView.setViewName("user/resetPwd.jsp");
		return modelAndView;
	}
	
	/**
	 * 重置密码提交
	 * @return
	 * @throws ApplicationException
	 */
	@NoLoginRequired
	@RequiredVCode
	@ResponseBody
	@RequestMapping(value="/resetPwd/submit", method=RequestMethod.POST)
	public DataResponse submitResetPwd(User user) throws ApplicationException{
		DataResponse dataResponse=getDataResponse();
		
		//重置密码
		forgetPwdService.submitResetPwd(user);
		
		return dataResponse;
	}
}
