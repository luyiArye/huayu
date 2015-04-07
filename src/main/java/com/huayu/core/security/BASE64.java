package com.huayu.core.security;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

/**
 * TODO(添加描述) 
 * @author ibupsmaster@gmail.com
 * @date 2013-6-14 上午12:19:47 
 * @version V1.0 
 * @copyright http://www.ihuayu.com
 */
@Component("base64")
public class BASE64 implements ISymmetry {
    
	/* (non-Javadoc)
	 * @see com.huayu.core.security.ISecurity#encrypt(java.lang.String)
	 */
	public String encrypt(String input) {
		if(input==null){
			return null;
		}
		return new String(Base64.encodeBase64(input.getBytes(),false));
	}

	/* (non-Javadoc)
	 * @see com.huayu.core.security.ISecurity#decrypt(java.lang.String)
	 */
	public String decrypt(String input) {
		if(input==null){
			return null;
		}
		
		return new String(Base64.decodeBase64(input.getBytes()));
	}

}
