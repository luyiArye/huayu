package com.huayu.core.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.huayu.core.bean.vo.HuayuResources;
import com.huayu.core.bean.vo.User;
import com.huayu.core.exception.ApplicationException;

public interface IFileService {
	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws ApplicationException
	 */
	public List<HuayuResources> upload(MultipartFile[] files, User user, int ft) throws ApplicationException;
	
	/**
	 * 读取文件
	 * @param resourceId
	 * @return
	 * @throws ApplicationException
	 */
	public HuayuResources read(int resourceId) throws ApplicationException;
}
