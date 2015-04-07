package com.huayu.core.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * TODO(添加描述) 
 * @author ibupsmaster@gmail.com
 * @date 2013-6-14 上午12:19:59 
 * @version V1.0 
 * @copyright http://www.ihuayu.com
 */
@Component("md5")
public class MD5 implements IAsymmetric {

	/* (non-Javadoc)
	 * @see com.huayu.core.security.ISecurity#encrypt(java.lang.String)
	 */
	public String encrypt(String input) {
		return DigestUtils.md5Hex(input);
	}

}
