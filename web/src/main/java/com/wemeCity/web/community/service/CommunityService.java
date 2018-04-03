
package com.wemeCity.web.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.web.community.exception.CommunityException;
import com.wemeCity.web.community.model.Community;

/**
 * CommunityService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public interface CommunityService {

	/**
	 * 新增community
	 *
	 * @param community
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCommunity(Community community) throws CommunityException;

	/**
	 * 删除community
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCommunity(long id) throws CommunityException;

	/**
	 * 修改community
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCommunity(Map<String, Object> condition) throws CommunityException;

	/**
	 * 查询community
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Community readCommunity(long id) throws CommunityException;

	/**
	 * 查询community集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Community> queryCommunityList(Map<String, Object> condition) ;

	/**
	 * 查询community集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCommunityCount(Map<String, Object> condition) throws CommunityException;
	
}