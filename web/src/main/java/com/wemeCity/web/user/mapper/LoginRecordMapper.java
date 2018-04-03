
package com.wemeCity.web.user.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.user.model.LoginRecord;

/**
 * LoginRecordMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
@Repository
public interface LoginRecordMapper {

	/**
	 * 新增loginRecord
	 *
	 * @param loginRecord
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	void insertLoginRecord(LoginRecord loginRecord);

	/**
	 * 删除loginRecord
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int deleteLoginRecord(long id);

	/**
	 * 修改loginRecord
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int updateLoginRecord(Map<String, Object> condition);

	/**
	 * 查询loginRecord
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	LoginRecord readLoginRecord(long id);

	/**
	 * 查询loginRecord集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	List<LoginRecord> queryLoginRecordList(Map<String, Object> condition);

	/**
	 * 查询loginRecord集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	long queryLoginRecordCount(Map<String, Object> condition);

}