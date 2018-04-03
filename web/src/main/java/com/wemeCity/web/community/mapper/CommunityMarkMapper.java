
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.CommunityMark;

/**
 * CommunityMarkMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
@Repository
public interface CommunityMarkMapper {

	/**
	 * 新增communityMark
	 *
	 * @param communityMark
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	void insertCommunityMark(CommunityMark communityMark);

	/**
	 * 删除communityMark
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int deleteCommunityMark(long id);

	/**
	 * 修改communityMark
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int updateCommunityMark(Map<String, Object> condition);

	/**
	 * 查询communityMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	CommunityMark readCommunityMark(long id);

	/**
	 * 查询communityMark集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	List<CommunityMark> queryCommunityMarkList(Map<String, Object> condition);

	/**
	 * 查询communityMark集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	long queryCommunityMarkCount(Map<String, Object> condition);
	
	
	/**
	 * 取消收藏
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int cancelMark(Map<String, Object> condition);

}