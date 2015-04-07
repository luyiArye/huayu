package com.huayu.core.security;

 /**  
 * 非对称加密 
 * @author ibupsmaster@gmail.com
 * @date 2013-6-13 下午8:45:54 
 * @version V1.0 
 * @copyright http://www.ihuayu.com
 */
public interface IAsymmetric {

	/**
	 * 将明文转换成密文
	 * @author ibupsmaster@gmail.com
	 * @time 下午8:46:25
	 * @param input 需要转换成密文的明文
	 * @return String 经过转换的密文
	 * @exception
	 */
	String encrypt(String input);
	
}
