
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.model.CateringGoods;

/**
 * CateringGoodsMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringGoodsMapper {

	/**
	 * 新增cateringGoods
	 *
	 * @param cateringGoods
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringGoods(CateringGoods cateringGoods);

	/**
	 * 删除cateringGoods
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringGoods(long id);

	/**
	 * 修改cateringGoods
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringGoods(Map<String, Object> condition);

	/**
	 * 查询cateringGoods
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringGoods readCateringGoods(long id);

	/**
	 * 查询cateringGoods集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringGoods> queryCateringGoodsList(Map<String, Object> condition);

	/**
	 * 查询cateringGoods集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringGoodsCount(Map<String, Object> condition);

}