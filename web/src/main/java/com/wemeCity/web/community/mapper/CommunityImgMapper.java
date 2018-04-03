
package com.wemeCity.web.community.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.community.model.CommunityImg;

/**
 * CommunityImgMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
@Repository
public interface CommunityImgMapper {

	/**
	 * 新增communityImg
	 *
	 * @param communityImg
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCommunityImg(CommunityImg communityImg);

	/**
	 * 删除communityImg
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCommunityImg(long id);

	/**
	 * 修改communityImg
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCommunityImg(Map<String, Object> condition);

	/**
	 * 查询communityImg
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	CommunityImg readCommunityImg(long id);

	/**
	 * 查询communityImg集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-13 新建
	 */
	List<CommunityImg> queryCommunityImgList(Map<String, Object> condition);

	/**
	 * 查询communityImg集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-13 新建
	 */
	long queryCommunityImgCount(Map<String, Object> condition);

}