package com.wemeCity.components.file.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.components.file.model.FileUpload;

/**
 * FileUploadMapper数据库访问类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-19 新建
 */
@Repository
public interface FileUploadMapper {

	/**
	 * 新增fileUpload
	 *
	 * @param fileUpload
	 * @return 新增的对象
	 * @author  2017-6-19 新建
	 */
	void insertFileUpload(FileUpload fileUpload);

	/**
	 * 删除fileUpload
	 *
	 * @param id 主键
	 * @return 
	 * @author  2017-6-19 新建
	 */
	int deleteFileUpload(long id);

	/**
	 * 修改fileUpload
	 *
	 * @param condition
	 * @return 
	 * @author  2017-6-19 新建
	 */
	int updateFileUpload(Map<String, Object> condition);

	/**
	 * 查询fileUpload
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author  2017-6-19 新建
	 */
	FileUpload readFileUpload(long id);

	/**
	 * 查询fileUpload集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author  2017-6-19 新建
	 */
	List<FileUpload> queryFileUploadList(Map<String, Object> condition);

	/**
	 * 查询fileUpload集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author  2017-6-19 新建
	 */
	long queryFileUploadCount(Map<String, Object> condition);

}