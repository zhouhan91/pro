
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringRecommend;

/**
 * CateringRecommendMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringRecommendMapper {

	/**
	 * 新增cateringRecommend
	 *
	 * @param cateringRecommend
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringRecommend(CateringRecommend cateringRecommend);

	/**
	 * 删除cateringRecommend
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringRecommend(long id);

	/**
	 * 修改cateringRecommend
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringRecommend(Map<String, Object> condition);

	/**
	 * 查询cateringRecommend
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringRecommend readCateringRecommend(long id);

	/**
	 * 查询cateringRecommend集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringRecommend> queryCateringRecommendList(Map<String, Object> condition);

	/**
	 * 查询cateringRecommend集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringRecommendCount(Map<String, Object> condition);

}