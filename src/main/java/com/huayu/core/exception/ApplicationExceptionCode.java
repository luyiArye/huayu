package com.huayu.core.exception;

import java.util.HashMap;
import java.util.Map;

public class ApplicationExceptionCode {
	public static Map<String, String> EX_MAP=new HashMap<String, String>();
	
	//异常编码
	/** 系统异常 */
	public static final String SYSTEM_EX_CODE="EX_000000";
	/** 转化Json异常 */
	public static final String PARSE_JSON_ERROR_CODE="EX_000001";
	/** 未登录、登录超时 */
	public static final String NO_LOGIN_OR_TIME_OUT_EX_CODE="EX_000002";
	/** 权限不足 */
	public static final String NO_PERMISSIONS_EX_CODE="EX_000003";
	/** 验证码错误 */
	public static final String VCODE_ERROR_EX_CODE="EX_000004";
	/** 数据校验存在错误 */
	public static final String VALIDATION_ERROR_EX_CODE = "EX_000005";
	/** 权限被限制 */
	public static final String PERMISSIONS_LIMIT_EX_CODE = "EX_000006";
	/** 操作太快 */
	public static final String OPERATION_TOO_FAST_EX_CODE = "EX_000007";
	
	//用户相关
	/** 用户不存在 */
	public static final String USER_ACCOUNT_NOT_FOUND_EX_CODE="U_EX_000001";
	/** 用户名和密码不匹配 */
	public static final String USER_PASSWORD_ERROR_EX_CODE="U_EX_000002";
	/** 邮箱已经注册 */
	public static final String USER_ACCOUNT_WAS_EXISTS_EX_CODE="U_EX_000003";
	/** 未找到用户信息 */
	public static final String CAN_NOT_FOUND_USER_EX_CODE="U_EX_000004";
	/** 忘记密码校验信息错误或者未找到 */
	public static final String FORGET_PWD_VF_ERROR_OR_NOT_FOUND_EX_CODE="U_EX_000005";
	/** 忘记密码校验信息已过期 */
	public static final String FORGET_PWD_VF_TIMEOUT_EX_CODE="U_EX_000006";
	/** 忘记密码校验信息已经使用 */
	public static final String FORGET_PWD_VF_ALREADY_USED_EX_CODE="U_EX_000007";
	
	//作品相关
	/** 作品未找到或者已经被删除 */
	public static final String PRODUCTION_NOT_FOUND_EX_CODE="P_EX_000001";
	
	//作品操作
	/** 已经进行过了该操作 */
	public static final String ALREADY_OPREATED_EX_CODE="PO_EX_000001";
}
