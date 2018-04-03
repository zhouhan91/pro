package com.wemeCity.web.catering.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PayCallback;
import com.wemeCity.web.catering.dto.CateringAllData;
import com.wemeCity.web.catering.dto.CateringMonthData;
import com.wemeCity.web.catering.dto.CateringPayDTO;
import com.wemeCity.web.catering.dto.TodayStatisticsInfo;
import com.wemeCity.web.catering.exception.CateringOrderException;
import com.wemeCity.web.catering.model.CateringOrder;
import com.wemeCity.web.user.model.UserSession;

/**
 * CateringOrderService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public interface CateringOrderService {

	/**
	 * 新增cateringOrder
	 *
	 * @param cateringOrder
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	void insertCateringOrder(CateringOrder cateringOrder) throws CateringOrderException;

	/**
	 * 删除cateringOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	int deleteCateringOrder(long id) throws CateringOrderException;

	/**
	 * 修改cateringOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	int updateCateringOrder(Map<String, Object> condition) throws CateringOrderException;

	/**
	 * 查询cateringOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	CateringOrder readCateringOrder(long id) throws CateringOrderException;

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	List<CateringOrder> queryCateringOrderList(Map<String, Object> condition);

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	long queryCateringOrderCount(Map<String, Object> condition) throws CateringOrderException;

	/**
	 * 查询用户在某家店铺订单数
	 *
	 * @param userId
	 * @param restaurantId
	 * @return
	 * @history 2017年12月9日 新建
	 * @auther xiaowen
	 */
	long queryRestaurantOrderCountByUser(long userId, long restaurantId);

	/**
	 * 好吃下单
	 *
	 * @param payDTO
	 * @param userSession
	 * @return
	 * @throws CateringOrderException
	 * @history 2017年12月12日 新建
	 * @auther xiaowen
	 */
	BaseDTO pay(CateringPayDTO payDTO, UserSession userSession) throws CateringOrderException;

	/**
	 * 统计今天的数据
	 *
	 * @param condition
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	TodayStatisticsInfo queryTodayStatisticsInfo(Map<String, Object> condition) throws CateringOrderException;
	
	/**
	 * 支付回调
	 *
	 * @param callback
	 * @return
	 * @history 2018年2月2日 新建
	 * @auther xiaowen
	 */
	BaseDTO callback(PayCallback callback);
	
	/**
	 * 检查是否新会员
	 *
	 * @param userId
	 * @param restaurantId
	 * @return
	 * @history 2018年2月10日 新建
	 * @auther xiaowen
	 */
	String checkNewMember(long userId, long restaurantId);
	
	/**
	 * 统计所有数据
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	CateringAllData queryAllData(Map<String, Object> condition);
	
	/**
	 * 统计本月数据
	 *
	 * @param condition
	 * @return
	 * @history 2018年2月12日 新建
	 * @auther xiaowen
	 */
	CateringMonthData queryMonthData(Map<String, Object> condition);
}