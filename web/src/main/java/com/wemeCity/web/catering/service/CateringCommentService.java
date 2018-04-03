
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.web.catering.exception.CateringCommentException;
import com.wemeCity.web.catering.model.CateringComment;
import com.wemeCity.web.user.model.UserSession;

/**
 * CateringCommentService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringCommentService {

	/**
	 * 新增cateringComment
	 *
	 * @param cateringComment
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringComment(CateringComment cateringComment) throws CateringCommentException;

	/**
	 * 删除cateringComment
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringComment(long id) throws CateringCommentException;

	/**
	 * 修改cateringComment
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringComment(Map<String, Object> condition) throws CateringCommentException;

	/**
	 * 查询cateringComment
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringComment readCateringComment(long id) throws CateringCommentException;

	/**
	 * 查询cateringComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringComment> queryCateringCommentList(Map<String, Object> condition) ;

	/**
	 * 查询cateringComment集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringCommentCount(Map<String, Object> condition) throws CateringCommentException;
	
	/**
	 * 获取店铺评论
	 *
	 * @param restaurantId
	 * @param pageNum
	 * @return
	 * @history 2017年12月6日 新建
	 * @auther xiaowen
	 */
	List<CateringComment> queryRestaurantCommentInfoList(long restaurantId, int pageNum);
	
	/**
	 * 发表评论
	 *
	 * @param comment
	 * @param userSession
	 * @return
	 * @throws CateringCommentException
	 * @history 2018年1月31日 新建
	 * @auther xiaowen
	 */
	BaseDTO publish(CateringComment comment, UserSession userSession) throws CateringCommentException;

}