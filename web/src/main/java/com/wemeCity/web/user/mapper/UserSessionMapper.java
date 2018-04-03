
package com.wemeCity.web.user.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.user.model.UserSession;

/**
 * UserSessionMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
@Repository
public interface UserSessionMapper {

	/**
	 * 新增userSession
	 *
	 * @param userSession
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	void insertUserSession(UserSession userSession);

	/**
	 * 删除userSession
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int deleteUserSession(long id);

	/**
	 * 修改userSession
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int updateUserSession(Map<String, Object> condition);

	/**
	 * 查询userSession
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	UserSession readUserSession(long id);

	/**
	 * 查询userSession集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	List<UserSession> queryUserSessionList(Map<String, Object> condition);

	/**
	 * 查询userSession集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	long queryUserSessionCount(Map<String, Object> condition);

}