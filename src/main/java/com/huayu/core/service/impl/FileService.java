package com.huayu.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.huayu.core.HuayuConstants;
import com.huayu.core.bean.vo.HuayuResources;
import com.huayu.core.bean.vo.User;
import com.huayu.core.config.SystemConfiguer;
import com.huayu.core.dao.mapper.HuayuResourceMaper;
import com.huayu.core.exception.ApplicationException;
import com.huayu.core.exception.ApplicationExceptionCode;
import com.huayu.core.service.IFileService;
import com.huayu.core.util.CommonUtil;

@Service
public class FileService extends BaseService implements IFileService {
	private static String SEPARATOR=File.separator;
	
	@Autowired
	private HuayuResourceMaper huayuResourceMaper;
	
	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws ApplicationException
	 */
	public List<HuayuResources> upload(MultipartFile[] files, User user, int ft) throws ApplicationException{
		//文件上传目录
		String fileFolder=SystemConfiguer.usersBaseLoacation;
		BigDecimal userId=null;
		if(user!=null){
			userId=user.getId();
			//如果用户不为空，则将文件上传到用户目录
			if(ft==HuayuConstants.FILE_TYPE_PRODUCTION){
				//用户作品目录
				fileFolder=fileFolder+SEPARATOR+userId+SEPARATOR+HuayuConstants.USER_PRODUCTION_FOLDER;
			}
			else if(ft==HuayuConstants.FILE_TYPE_HEAD_ICO){
				//用户头像目录
				fileFolder=fileFolder+SEPARATOR+userId+SEPARATOR+HuayuConstants.USER_HEAD_ICO_FOLDER;
			}
			CommonUtil.checkOrCreateFolder(fileFolder);
		}
		
		List<HuayuResources> resourceList=new ArrayList<HuayuResources>();
		HuayuResources huayuResources=null;
		String fileName=null;
		String fileTempName=null;
		String filePath=null;
		String fileType=null;
		for(MultipartFile file : files){ 
			if(file.isEmpty()){
				LOG.warn("upload file is empty.");
				continue;
			}
			LOG.debug("file content type: "+file.getContentType());
			fileType=null;
			fileName=file.getOriginalFilename();
			if(fileName.lastIndexOf(".")!=-1){
				fileType=fileName.substring(fileName.lastIndexOf(".")+1);
			}
			fileTempName=getTempFileName(fileType);
			filePath=fileFolder+SEPARATOR+fileTempName;
			
			try {
				//将文件复制到指定目录
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileFolder, fileTempName));
			} 
			catch (IOException e) {
				throw new ApplicationException(ApplicationExceptionCode.SYSTEM_EX_CODE);
			}
			
			huayuResources=new HuayuResources();
			huayuResources.setResourceName(fileName);
			huayuResources.setResourcePath(filePath);
			huayuResources.setCreatedBy(userId);
			huayuResources.setResourceType(fileType);
			huayuResources.setResourceSize(new BigDecimal(file.getSize()));
			huayuResources.setContentType(file.getContentType());
			
			//新增资源
			huayuResourceMaper.insertResource(huayuResources);
			
			resourceList.add(huayuResources);
		}
		
		return resourceList;
	}
	
	/**
	 * 读取文件
	 * @param resourceId
	 * @return
	 * @throws ApplicationException
	 */
	public HuayuResources read(int resourceId) throws ApplicationException{
		return huayuResourceMaper.selectResourceById(resourceId);
	}
	
	/**
	 * 获取临时文件名
	 * @return
	 */
	private String getTempFileName(String fileType){
		String uuid=UUID.randomUUID().toString();
		if(fileType!=null && !"".equals(fileType.trim())){
			return uuid+"."+fileType;
		}
		return uuid;
	}
}
