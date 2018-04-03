package com.wemeCity.web.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.web.community.enums.CommunityImgBusiCodeEnum;
import com.wemeCity.web.community.exception.CommunityImgException;
import com.wemeCity.web.community.model.CommunityImg;

/**
 * CommunityImgService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public interface CommunityImgService {

	/**
	 * 新增communityImg
	 *
	 * @param communityImg
	 * @return 新增的对象
	 * @author 向小文 2017-9-13 新建
	 */
	void insertCommunityImg(CommunityImg communityImg) throws CommunityImgException;

	/**
	 * 删除communityImg
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	int deleteCommunityImg(long id) throws CommunityImgException;

	/**
	 * 修改communityImg
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-13 新建
	 */
	int updateCommunityImg(Map<String, Object> condition) throws CommunityImgException;

	/**
	 * 查询communityImg
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-13 新建
	 */
	CommunityImg readCommunityImg(long id) throws CommunityImgException;

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
	long queryCommunityImgCount(Map<String, Object> condition) throws CommunityImgException;

	/**
	 * 根据业务id和业务编码获取图片信息
	 *
	 * @param busiCode 参见CommunityImgBusiCodeEnum
	 * @param busiId
	 * @return
	 * @throws CommunityImgException
	 * @Author Xiang xiaowen 2017年9月19日 新建
	 */
	List<CommunityImg> queryCommunityImgList(CommunityImgBusiCodeEnum busiCode, long busiId) throws CommunityImgException;

}