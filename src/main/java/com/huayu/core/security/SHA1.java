package com.huayu.core.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * TODO(添加描述) 
 * @author ibupsmaster@gmail.com
 * @date 2013-6-14 上午12:20:07 
 * @version V1.0 
 * @copyright http://www.ihuayu.com
 */
@Component("sha1")
public class SHA1 implements IAsymmetric {

	/* (non-Javadoc)
	 * @see com.huayu.core.security.ISecurity#encrypt(java.lang.String)
	 */
	@SuppressWarnings("deprecation")
	public String encrypt(String input) {
		return DigestUtils.shaHex(input);
	}
	
}
