package com.wemeCity.web.user.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.user.dto.WechatLoginSuccessDTO;
import com.wemeCity.web.user.exception.UserException;
import com.wemeCity.web.user.model.User;

/**
 * UserService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public interface UserService {

	/**
	 * 新增user
	 *
	 * @param user
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	void insertUser(User user) throws UserException;

	/**
	 * 删除user
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	int deleteUser(long id) throws UserException;

	/**
	 * 修改user
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-26 新建
	 */
	int updateUser(Map<String, Object> condition) throws UserException;

	/**
	 * 查询user
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	User readUser(long id) throws UserException;

	/**
	 * 查询user集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-26 新建
	 */
	List<User> queryUserList(Map<String, Object> condition);

	/**
	 * 查询user集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-26 新建
	 */
	long queryUserCount(Map<String, Object> condition) throws UserException;

	/**
	 * 根据unionId获取用户信息，未做状态判断，去除密码等敏感信息
	 *
	 * @param unionId
	 * @return
	 * @throws UserException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	User readUserByUnionId(String unionId) throws UserException;
	
	/**
	 * 根据openid获取用户
	 *
	 * @param openId
	 * @return
	 * @throws UserException
	 * @history 2017年12月13日 新建
	 * @auther xiaowen
	 */
	User readUserByOpenId(String openId) throws UserException;

	/**
	 * 登录
	 *
	 * @param loginDTO 从微信获取的sessionKey等信息
	 * @return
	 * @throws UserException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	BaseDTO login(WechatLoginSuccessDTO loginDTO) throws UserException;

	/**
	 * 修改用户信息
	 *
	 * @param user
	 * @return
	 * @throws UserException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	BaseDTO updateUserInfo(User user) throws UserException;

}