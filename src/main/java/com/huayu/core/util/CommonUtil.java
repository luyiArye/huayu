package com.huayu.core.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.huayu.core.HuayuConstants;

public class CommonUtil {
	/**
	 * 检查boolean标识是否为true
	 * @param value
	 * @return
	 */
	public static boolean isTrue(Object value){
		return value!=null && HuayuConstants.BOOLEAN_TRUE.equals(value);
	}
	
	/**
	 * 
	 * 检查并创建不存在的目录 StartupServlet.java
	 * 
	 * @author luyi_java
	 * @time 2013-11-3 下午9:17:45
	 */
	public static boolean checkOrCreateFolder(String folderPath) {
		File file = new File(folderPath);

		if (!file.exists()) {
			return file.mkdirs();
		}

		return true;
	}
	
	/**
	 * 获取系统当前时间带星期
	 * @return
	 */
	public static String getSysDateWeek(){
		Date date=new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd日");
		String sysDate=df.format(date)+" "+HuayuConstants.WEEK_MAP.get(cal.get(Calendar.DAY_OF_WEEK));
		
		return sysDate;
	}
}
