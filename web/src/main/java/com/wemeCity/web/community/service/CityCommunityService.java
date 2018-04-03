
package com.wemeCity.web.community.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.community.exception.CityCommunityException;
import com.wemeCity.web.community.model.CityCommunity;

/**
 * CityCommunityService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public interface CityCommunityService {

	/**
	 * 新增cityCommunity
	 *
	 * @param cityCommunity
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCityCommunity(CityCommunity cityCommunity) throws CityCommunityException;

	/**
	 * 删除cityCommunity
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCityCommunity(long id) throws CityCommunityException;

	/**
	 * 修改cityCommunity
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCityCommunity(Map<String, Object> condition) throws CityCommunityException;

	/**
	 * 查询cityCommunity
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	CityCommunity readCityCommunity(long id) throws CityCommunityException;

	/**
	 * 查询cityCommunity集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<CityCommunity> queryCityCommunityList(Map<String, Object> condition) ;

	/**
	 * 查询cityCommunity集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCityCommunityCount(Map<String, Object> condition) throws CityCommunityException;

}