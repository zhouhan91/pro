
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringCommentImg;

/**
 * CateringCommentImgMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringCommentImgMapper {

	/**
	 * 新增cateringCommentImg
	 *
	 * @param cateringCommentImg
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringCommentImg(CateringCommentImg cateringCommentImg);

	/**
	 * 删除cateringCommentImg
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringCommentImg(long id);

	/**
	 * 修改cateringCommentImg
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringCommentImg(Map<String, Object> condition);

	/**
	 * 查询cateringCommentImg
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringCommentImg readCateringCommentImg(long id);

	/**
	 * 查询cateringCommentImg集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringCommentImg> queryCateringCommentImgList(Map<String, Object> condition);

	/**
	 * 查询cateringCommentImg集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringCommentImgCount(Map<String, Object> condition);

}