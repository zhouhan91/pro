
package com.wemeCity.web.user.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.user.model.User;

/**
 * UserMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
@Repository
public interface UserMapper {

	/**
	 * 新增user
	 *
	 * @param user
	 * @return 新增的对象
	 * @author 向小文 2017-9-26 新建
	 */
	void insertUser(User user);

	/**
	 * 删除user
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int deleteUser(long id);

	/**
	 * 修改user
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-26 新建
	 */
	int updateUser(Map<String, Object> condition);

	/**
	 * 查询user
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-26 新建
	 */
	User readUser(long id);

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
	long queryUserCount(Map<String, Object> condition);

}