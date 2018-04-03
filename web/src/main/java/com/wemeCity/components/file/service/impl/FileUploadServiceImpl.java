package com.wemeCity.components.file.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wemeCity.components.file.exception.FileUploadException;
import com.wemeCity.components.file.mapper.FileUploadMapper;
import com.wemeCity.components.file.model.FileUpload;
import com.wemeCity.components.file.service.FileUploadService;
/**
 * FileUploadServiceImpl AppService类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-19 新建
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService{

	private Logger logger=LoggerFactory.getLogger(FileUploadServiceImpl.class);

	/** 数据访问接口 */
	@Autowired
	private FileUploadMapper fileUploadMapper;

	/**
	 * 新增fileUpload
	 *
	 * @param fileUpload
	 * @return 新增的对象
	 * @author  2017-6-19 新建
	 */
	@Override
	public void insertFileUpload(FileUpload fileUpload) throws FileUploadException{
		try{
			fileUploadMapper.insertFileUpload(fileUpload);
		}catch(Exception e){
			logger.error("新增FileUpload时报错", e);
			throw new FileUploadException("新增FileUpload时报错", e);
		}
	}

	/**
	 * 删除fileUpload
	 *
	 * @param id 主键
	 * @return 
	 * @author  2017-6-19 新建
	 */
	@Override
	public int deleteFileUpload(long id) throws FileUploadException{
		try{
			return this.fileUploadMapper.deleteFileUpload(id);
		}catch(Exception e){
			logger.error("删除FileUpload时报错", e);
			throw new FileUploadException("删除FileUpload时报错", e);
		}
	}

	/**
	 * 修改fileUpload
	 *
	 * @param condition
	 * @return 
	 * @author  2017-6-19 新建
	 */
	@Override
	public int updateFileUpload(Map<String, Object> condition) throws FileUploadException{
		try{
			return this.fileUploadMapper.updateFileUpload(condition);
		}catch(Exception e){
			logger.error("修改FileUpload时报错", e);
			throw new FileUploadException("修改FileUpload时报错", e);
		}
	}

	/**
	 * 查询fileUpload
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author  2017-6-19 新建
	 */
	@Override
	public FileUpload readFileUpload(long id) throws FileUploadException{
		try{
			return this.fileUploadMapper.readFileUpload(id);
		}catch(Exception e){
			logger.error("查询FileUpload时报错", e);
			throw new FileUploadException("查询FileUpload时报错", e);
		}
	}

	/**
	 * 查询fileUpload集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author  2017-6-19 新建
	 */
	@Override
	public List<FileUpload> queryFileUploadList(Map<String, Object> condition){
		try{
			return this.fileUploadMapper.queryFileUploadList(condition);
		}catch(Exception e){
			logger.error("查询FileUpload时报错", e);
			return null;
		}
	}

	/**
	 * 查询fileUpload集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author  2017-6-19 新建
	 */
	@Override
	public long queryFileUploadCount(Map<String, Object> condition) throws FileUploadException{
		try{
			return this.fileUploadMapper.queryFileUploadCount(condition);
		}catch(Exception e){
			logger.error("查询FileUpload时报错", e);
			throw new FileUploadException("查询FileUpload时报错", e);
		}
	}

}