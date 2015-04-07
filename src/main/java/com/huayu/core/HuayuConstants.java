package com.huayu.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HuayuConstants {
	/** 用户登录保存用户信息到session中的key */
	public static final String LOGIN_USER_SESSION_KEY="_login_user";
	/** 超时登录后跳转的url */
	public static final String REDIRECTURL_KEY="_redirectUrl";
	/** 验证码保存在session中的key */
	public static final String V_CODE_SESSION_KEY="_VCode";
	/** 操作失败的次数session key */
	public static final String OPERATION_FAILED_COUNT="_ofc";
	/** 保存项目跟路径的key */
	public static final String CONTEXTPATH_KEY="contextPath";
	/** 用户作品目录名称 */
	public static final String USER_PRODUCTION_FOLDER="p";
	/** 用户头像目录名称 */
	public static final String USER_HEAD_ICO_FOLDER="h_ico";
	/** 作品类型文件 */
	public static final int FILE_TYPE_PRODUCTION=1;
	/** 头像类型文件 */
	public static final int FILE_TYPE_HEAD_ICO=2;
	/** 页面消息key */
	public static final String PAGE_MESSAGE_KEY="message";
	/** 表情图片根目录 */
	public static final String FACE_IMAGES_FOLDER="images/faces/";
	
	/** Boolean值true */
	public static final BigDecimal BOOLEAN_TRUE=new BigDecimal(1);
	/** Boolean值false */
	public static final BigDecimal BOOLEAN_FALSE=new BigDecimal(0);
	
	/** 星期Map */
	public static final Map<Integer, String> WEEK_MAP=new HashMap<Integer, String>();
	static{
		WEEK_MAP.put(2, "星期一");
		WEEK_MAP.put(3, "星期二");
		WEEK_MAP.put(4, "星期三");
		WEEK_MAP.put(5, "星期四");
		WEEK_MAP.put(6, "星期五");
		WEEK_MAP.put(7, "星期六");
		WEEK_MAP.put(1, "星期日");
	}
	
	/** 表情集合 */
	public static final List<String> FACE_IMAGES=new ArrayList<String>();
	
	/** 1: 赞作品操作 */
	public static final BigDecimal PRODUCTION_PRAISE_OPERATION=new BigDecimal(1);
	/** 2: 踩作品操作 */
	public static final BigDecimal PRODUCTION_TRAMPLE_OPERATION=new BigDecimal(2);
	/** 3: 浏览作品操作 */
	public static final BigDecimal PRODUCTION_BROWSE_OPERATION=new BigDecimal(3);
	/** 4: 删除作品操作 */
	public static final BigDecimal PRODUCTION_DELETE_OPERATION=new BigDecimal(4);
	/** 5：转载作品操作 */
	public static final BigDecimal PRODUCTION_TRANS_OPERATION=new BigDecimal(5);
	/** 6：分享作品操作 */
	public static final BigDecimal PRODUCTION_SHARE_OPERATION=new BigDecimal(6);
	/** 7：收藏作品操作 */
	public static final BigDecimal PRODUCTION_COLLECT_OPERATION=new BigDecimal(7);
	
	/** 作品删除状态 */
	public static final BigDecimal PRODUCTION_DELETE_STATUS=new BigDecimal(0);
	/** 作品正常状态 */
	public static final BigDecimal PRODUCTION_NORMAL_STATUS=new BigDecimal(1);
	
	/** 一天时间的毫秒数 */
	public static final long ONE_DAY_MILLISECOND=86400000;
}
