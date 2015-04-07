package com.huayu.core.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import com.huayu.core.annotation.DataValidation;
import com.huayu.core.bean.ValidationError;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;

public class ValidationAOP implements MethodBeforeAdvice {
	private Logger LOG = Logger.getLogger(this.getClass());
	
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		Integer[] argIdxs=hasMyValidationArgs(method);
		if(argIdxs==null || argIdxs.length<=0){
			return ;
		}
		
		ValidatorFactory validatorFactory=Validation.buildDefaultValidatorFactory();
		Validator validator=validatorFactory.getValidator();
		
		Set<ConstraintViolation<Object>> validationResultSet=null;
		Iterator<ConstraintViolation<Object>> vrsIt=null;
		ConstraintViolation<Object> cv=null;
		List<ValidationError> validationErrors=null;
		ValidationError validationError=null;
		for(int argIdx: argIdxs){
			validationResultSet=validator.validate(args[argIdx]);
			
			//解析错误结果
			if(validationResultSet!=null && !validationResultSet.isEmpty()){
				//如果包含错误信息
				if(validationErrors==null){
					validationErrors=new ArrayList<ValidationError>();
				}
				
				//迭代错误信息
				vrsIt=validationResultSet.iterator();
				while(vrsIt.hasNext()){
					cv=vrsIt.next();
					
					validationError=new ValidationError();
					validationError.setPropertyPath(cv.getPropertyPath().toString());
					validationError.setMessage(cv.getMessage());
					//validationError.setMessageTemplage(cv.getMessageTemplate());
					validationError.setInvalidValue(cv.getInvalidValue());
					
					validationErrors.add(validationError);
					LOG.debug(validationError);
				}
			}
		}
		
		if(validationErrors!=null && !validationErrors.isEmpty()){
			//如果校验存在错误
			ApplicationException applicationEx=new ApplicationException(ApplicationExceptionCode.VALIDATION_ERROR_EX_CODE);
			//设置错误信息
			applicationEx.setValidationErrors(validationErrors);
			throw applicationEx;
		}
	}
	
	/**
	 * 检查方法参数是否存在DataValidation注解
	 * @param method
	 * @return
	 */
	private Integer[] hasMyValidationArgs(Method method){
		List<Integer> argIdx=new ArrayList<Integer>();
		
		Annotation[][] paramAnnotations=method.getParameterAnnotations();
		if(method!=null && paramAnnotations!=null && paramAnnotations.length>0){
			Annotation[] anns=null;
			Annotation ann=null;
			//按参数循环
			for(int i=0;i<paramAnnotations.length;i++){
				anns=paramAnnotations[i];
				if(anns!=null && anns.length>0){
					//循环参数所有的注解
					for(int j=0;j<anns.length;j++){
						ann=anns[j];
						if(ann instanceof DataValidation){
							//如果发现参数存在DataValidation注解则记录该参数额下标
							argIdx.add(i);
							break;
						}
					}
				}
			}
		}
		
		return argIdx.toArray(new Integer[]{});
	}
}
