
package com.wemeCity.web.user.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.user.exception.LoginRecordException;
import com.wemeCity.web.user.model.LoginRecord;

/**
 * LoginRecordService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public interface LoginRecordService {

	/**
	 * 新增loginRecord
	 *
	 * @param loginRecord
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	void insertLoginRecord(LoginRecord loginRecord) throws LoginRecordException;

	/**
	 * 删除loginRecord
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int deleteLoginRecord(long id) throws LoginRecordException;

	/**
	 * 修改loginRecord
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int updateLoginRecord(Map<String, Object> condition) throws LoginRecordException;

	/**
	 * 查询loginRecord
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	LoginRecord readLoginRecord(long id) throws LoginRecordException;

	/**
	 * 查询loginRecord集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	List<LoginRecord> queryLoginRecordList(Map<String, Object> condition) ;

	/**
	 * 查询loginRecord集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	long queryLoginRecordCount(Map<String, Object> condition) throws LoginRecordException;

}