package com.wemeCity.web.user.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.user.exception.UserSessionException;
import com.wemeCity.web.user.model.UserSession;

/**
 * UserSessionService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public interface UserSessionService {

	/**
	 * 新增userSession
	 *
	 * @param userSession
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	void insertUserSession(UserSession userSession) throws UserSessionException;

	/**
	 * 删除userSession
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	int deleteUserSession(long id) throws UserSessionException;

	/**
	 * 修改userSession
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	int updateUserSession(Map<String, Object> condition) throws UserSessionException;

	/**
	 * 查询userSession
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	UserSession readUserSession(long id) throws UserSessionException;

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
	long queryUserSessionCount(Map<String, Object> condition) throws UserSessionException;

	/**
	 * 根据userKey获取userSession
	 *
	 * @param userKey
	 * @return
	 * @throws UserSessionException
	 * @Author Xiang xiaowen 2017年9月26日 新建
	 */
	UserSession readUserSessionByUserKey(String userKey) throws UserSessionException;

	/**
	 * 检查登录是否有效
	 *
	 * @param userKey
	 * @return
	 * @throws UserSessionException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	BaseDTO checkSession(String userKey) throws UserSessionException;

}