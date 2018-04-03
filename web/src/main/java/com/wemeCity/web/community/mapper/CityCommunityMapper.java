
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.CityCommunity;

/**
 * CityCommunityMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface CityCommunityMapper {

	/**
	 * 新增cityCommunity
	 *
	 * @param cityCommunity
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCityCommunity(CityCommunity cityCommunity);

	/**
	 * 删除cityCommunity
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCityCommunity(long id);

	/**
	 * 修改cityCommunity
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCityCommunity(Map<String, Object> condition);

	/**
	 * 查询cityCommunity
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	CityCommunity readCityCommunity(long id);

	/**
	 * 查询cityCommunity集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<CityCommunity> queryCityCommunityList(Map<String, Object> condition);

	/**
	 * 查询cityCommunity集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCityCommunityCount(Map<String, Object> condition);

}