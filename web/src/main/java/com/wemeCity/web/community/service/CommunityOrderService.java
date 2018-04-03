package com.wemeCity.web.community.service;

import java.util.List;
import java.util.Map;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.components.wechatPay.model.WechatCallback;
import com.wemeCity.web.community.dto.CommunityOrderDTO;
import com.wemeCity.web.community.exception.CommunityOrderException;
import com.wemeCity.web.community.model.Community;
import com.wemeCity.web.community.model.CommunityOrder;
import com.wemeCity.web.community.model.Room;
import com.wemeCity.web.user.model.Coupon;
import com.wemeCity.web.user.model.UserSession;

/**
 * CommunityOrderService Service接口
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
public interface CommunityOrderService {

	/**
	 * 新增communityOrder
	 *
	 * @param communityOrder
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	void insertCommunityOrder(CommunityOrder communityOrder) throws CommunityOrderException;

	/**
	 * 删除communityOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	int deleteCommunityOrder(long id) throws CommunityOrderException;

	/**
	 * 修改communityOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	int updateCommunityOrder(Map<String, Object> condition) throws CommunityOrderException;

	/**
	 * 查询communityOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	CommunityOrder readCommunityOrder(long id) throws CommunityOrderException;

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	List<CommunityOrder> queryCommunityOrderList(Map<String, Object> condition);

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	long queryCommunityOrderCount(Map<String, Object> condition) throws CommunityOrderException;

	/**
	 * 微信下单
	 *
	 * @param orderDTO 订单信息
	 * @param userSession 用户信息
	 * @param community 社区信息
	 * @param room 房型信息
	 * @param coupon 优惠券信息
	 * @return
	 * @throws CommunityOrderException
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	BaseDTO pay(CommunityOrderDTO orderDTO, UserSession userSession, Community community, Room room, Coupon coupon) throws CommunityOrderException;

	/**
	 * 根据编码读取订单
	 *
	 * @param code
	 * @return
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	CommunityOrder readCommunityOrderByCode(String code);

	/**
	 * 微信支付回调
	 *
	 * @param wechatCallback
	 * @return
	 * @throws CommunityOrderException
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	BaseDTO callback(CommunityOrder order, WechatCallback wechatCallback) throws CommunityOrderException;

}