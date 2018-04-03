
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;
import com.wemeCity.web.catering.exception.CateringCommentImgException;
import com.wemeCity.web.catering.model.CateringCommentImg;

/**
 * CateringCommentImgService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringCommentImgService {

	/**
	 * 新增cateringCommentImg
	 *
	 * @param cateringCommentImg
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringCommentImg(CateringCommentImg cateringCommentImg) throws CateringCommentImgException;

	/**
	 * 删除cateringCommentImg
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringCommentImg(long id) throws CateringCommentImgException;

	/**
	 * 修改cateringCommentImg
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringCommentImg(Map<String, Object> condition) throws CateringCommentImgException;

	/**
	 * 查询cateringCommentImg
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringCommentImg readCateringCommentImg(long id) throws CateringCommentImgException;

	/**
	 * 查询cateringCommentImg集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringCommentImg> queryCateringCommentImgList(Map<String, Object> condition) ;

	/**
	 * 查询cateringCommentImg集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringCommentImgCount(Map<String, Object> condition) throws CateringCommentImgException;
	
	/**
	 * 查询评论图片
	 *
	 * @param commentId
	 * @return
	 * @history 2017年12月6日 新建
	 * @auther xiaowen
	 */
	List<CateringCommentImg> queryCommentImg(long commentId);

}