
package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.web.catering.dto.CateringGoodsSalesVolume;
import com.wemeCity.web.catering.exception.CateringOrderDetailException;
import com.wemeCity.web.catering.model.CateringOrderDetail;

/**
 * CateringOrderDetailService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringOrderDetailService {

	/**
	 * 新增cateringOrderDetail
	 *
	 * @param cateringOrderDetail
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringOrderDetail(CateringOrderDetail cateringOrderDetail) throws CateringOrderDetailException;

	/**
	 * 删除cateringOrderDetail
	 *
	 * @param id 主键
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringOrderDetail(long id) throws CateringOrderDetailException;

	/**
	 * 修改cateringOrderDetail
	 *
	 * @param condition
	 * @return 
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringOrderDetail(Map<String, Object> condition) throws CateringOrderDetailException;

	/**
	 * 查询cateringOrderDetail
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringOrderDetail readCateringOrderDetail(long id) throws CateringOrderDetailException;

	/**
	 * 查询cateringOrderDetail集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringOrderDetail> queryCateringOrderDetailList(Map<String, Object> condition) ;

	/**
	 * 查询cateringOrderDetail集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringOrderDetailCount(Map<String, Object> condition) throws CateringOrderDetailException;
	
	/**
	 * 根据订单id获取订单明细
	 *
	 * @param orderId
	 * @return
	 * @throws CateringOrderDetailException
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	List<CateringOrderDetail> queryOrderDetailList(long orderId) throws CateringOrderDetailException;

	
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