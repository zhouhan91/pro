package com.wemeCity.components.file.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.components.file.exception.FileUploadException;
import com.wemeCity.components.file.model.FileUpload;

/**
 * FileUploadService Service接口
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-19 新建
 */
public interface FileUploadService {

	/**
	 * 新增fileUpload
	 *
	 * @param fileUpload
	 * @return 新增的对象
	 * @author  2017-6-19 新建
	 */
	void insertFileUpload(FileUpload fileUpload) throws FileUploadException;

	/**
	 * 删除fileUpload
	 *
	 * @param id 主键
	 * @return 
	 * @author  2017-6-19 新建
	 */
	int deleteFileUpload(long id) throws FileUploadException;

	/**
	 * 修改fileUpload
	 *
	 * @param condition
	 * @return 
	 * @author  2017-6-19 新建
	 */
	int updateFileUpload(Map<String, Object> condition) throws FileUploadException;

	/**
	 * 查询fileUpload
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author  2017-6-19 新建
	 */
	FileUpload readFileUpload(long id) throws FileUploadException;

	/**
	 * 查询fileUpload集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author  2017-6-19 新建
	 */
	List<FileUpload> queryFileUploadList(Map<String, Object> condition) throws FileUploadException;

	/**
	 * 查询fileUpload集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author  2017-6-19 新建
	 */
	long queryFileUploadCount(Map<String, Object> condition) throws FileUploadException;

}