
package com.wemeCity.web.catering.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wemeCity.web.catering.dto.CateringGoodsSalesVolume;
import com.wemeCity.web.catering.model.CateringOrderDetail;

/**
 * CateringOrderDetailMapper数据库访问类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Repository
public interface CateringOrderDetailMapper {

	/**
	 * 新增cateringOrderDetail
	 *
	 * @param cateringOrderDetail
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringOrderDetail(CateringOrderDetail cateringOrderDetail);

	/**
	 * 删除cateringOrderDetail
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringOrderDetail(long id);

	/**
	 * 修改cateringOrderDetail
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringOrderDetail(Map<String, Object> condition);

	/**
	 * 查询cateringOrderDetail
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringOrderDetail readCateringOrderDetail(long id);

	/**
	 * 查询cateringOrderDetail集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringOrderDetail> queryCateringOrderDetailList(Map<String, Object> condition);

	/**
	 * 查询cateringOrderDetail集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringOrderDetailCount(Map<String, Object> condition);

	/**
	 * 查询商品销量
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	List<CateringGoodsSalesVolume> queryCateringGoodsSalesVolume(Map<String, Object> condition);
}