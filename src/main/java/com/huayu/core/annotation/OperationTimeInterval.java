package com.huayu.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作时间间隔
 * @author arye.luyi Administrator
 * @date 2015-3-16 下午3:28:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationTimeInterval {
	//时间，单位：s
	public int value() default 0;
}
