
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.CommunityOrder;

/**
 * CommunityOrderMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
@Repository
public interface CommunityOrderMapper {

	/**
	 * 新增communityOrder
	 *
	 * @param communityOrder
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	void insertCommunityOrder(CommunityOrder communityOrder);

	/**
	 * 删除communityOrder
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int deleteCommunityOrder(long id);

	/**
	 * 修改communityOrder
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int updateCommunityOrder(Map<String, Object> condition);

	/**
	 * 查询communityOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	CommunityOrder readCommunityOrder(long id);

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	List<CommunityOrder> queryCommunityOrderList(Map<String, Object> condition);

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	long queryCommunityOrderCount(Map<String, Object> condition);

}