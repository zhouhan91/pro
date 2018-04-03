
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.Community;

/**
 * CommunityMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface CommunityMapper {

	/**
	 * 新增community
	 *
	 * @param community
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCommunity(Community community);

	/**
	 * 删除community
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCommunity(long id);

	/**
	 * 修改community
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCommunity(Map<String, Object> condition);

	/**
	 * 查询community
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	Community readCommunity(long id);

	/**
	 * 查询community集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<Community> queryCommunityList(Map<String, Object> condition);

	/**
	 * 查询community集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCommunityCount(Map<String, Object> condition);

}