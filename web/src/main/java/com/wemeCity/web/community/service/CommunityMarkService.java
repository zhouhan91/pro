
package com.wemeCity.web.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.community.exception.CommunityMarkException;
import com.wemeCity.web.community.model.CommunityMark;

/**
 * CommunityMarkService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
public interface CommunityMarkService {

	/**
	 * 新增communityMark
	 *
	 * @param communityMark
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	void insertCommunityMark(CommunityMark communityMark) throws CommunityMarkException;

	/**
	 * 删除communityMark
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int deleteCommunityMark(long id) throws CommunityMarkException;

	/**
	 * 修改communityMark
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-28 新建
	 */
	int updateCommunityMark(Map<String, Object> condition) throws CommunityMarkException;

	/**
	 * 查询communityMark
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	CommunityMark readCommunityMark(long id) throws CommunityMarkException;

	/**
	 * 查询communityMark集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	List<CommunityMark> queryCommunityMarkList(Map<String, Object> condition) ;

	/**
	 * 查询communityMark集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	long queryCommunityMarkCount(Map<String, Object> condition) throws CommunityMarkException;

	
	/**
	 * 收藏公寓，将公寓收藏数+1
	 *
	 * @param userId
	 * @param communityId
	 * @return
	 * @throws CommunityMarkException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	BaseDTO mark(long userId, long communityId) throws CommunityMarkException;
	
	/**
	 * 取消收藏,不减收藏数
	 *
	 * @param userId
	 * @param communityId
	 * @return
	 * @throws CommunityMarkException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	BaseDTO cancelMark(long userId, long communityId) throws CommunityMarkException;
	
	/**
	 * 检查是否已收藏
	 *
	 * @param userId
	 * @param communityId
	 * @return
	 * @throws CommunityMarkException
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	CommunityMark checkMarked(long userId, long communityId) throws CommunityMarkException;
}