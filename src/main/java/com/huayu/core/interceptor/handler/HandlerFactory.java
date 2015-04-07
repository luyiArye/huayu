package com.huayu.core.interceptor.handler;

import java.lang.annotation.Annotation;

import com.huayu.core.annotation.OperationTimeInterval;
import com.huayu.core.annotation.PermissionsResource;
import com.huayu.core.annotation.RequiredVCode;

public class HandlerFactory {
	public static HandlerBase getHandler(Annotation a){
		HandlerBase handlerBase=null;
		
		if(a==null){
			return null;
		}
		
		if(a instanceof RequiredVCode){
			//验证码校验
			handlerBase=new RequiredVCodeHandler();
		}
		else if(a instanceof PermissionsResource){
			//功能权限校验
			handlerBase=new PermissionsResourceHandler();
		}
		else if(a instanceof OperationTimeInterval){
			//操作时间间隔校验
			handlerBase=new OperationTimeIntervalHandler();
		}
		
		return handlerBase;
	}
}
