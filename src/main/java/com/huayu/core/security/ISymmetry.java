package com.huayu.core.security;

 /**  
 * 对称加密
 * @author ibupsmaster@gmail.com
 * @date 2013-6-13 下午8:43:38 
 * @version V1.0 
 * @copyright http://www.ihuayu.com
 */
public interface ISymmetry extends IAsymmetric {
	
	/**
	 * 解密给定的数据
	 * @author ibupsmaster@gmail.com
	 * @time 下午8:44:11
	 * @param input 需要解密的数据（明文）
	 * @return 返回解密过后的明文数据
	 * @exception
	 */
	String decrypt(String input);
}
