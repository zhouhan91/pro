package com.wemeCity.web.community.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.components.exchangeRate.model.ExchangeRate;
import com.wemeCity.components.exchangeRate.service.ExchangeRateService;
import com.wemeCity.components.wechatPay.dto.PayDTO;
import com.wemeCity.components.wechatPay.model.WechatCallback;
import com.wemeCity.components.wechatPay.service.WechatUnifiedOrderService;
import com.wemeCity.web.community.dto.CommunityOrderDTO;
import com.wemeCity.web.community.enums.CommunityOrderPayStatusEnum;
import com.wemeCity.web.community.enums.CommunityOrderStatusEnum;
import com.wemeCity.web.community.exception.CommunityOrderException;
import com.wemeCity.web.community.mapper.CommunityOrderMapper;
import com.wemeCity.web.community.model.Community;
import com.wemeCity.web.community.model.CommunityOrder;
import com.wemeCity.web.community.model.Room;
import com.wemeCity.web.community.service.CommunityOrderService;
import com.wemeCity.web.community.utils.CommunityConstants;
import com.wemeCity.web.user.model.Coupon;
import com.wemeCity.web.user.model.UserSession;

/**
 * CommunityOrderServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
@Service
public class CommunityOrderServiceImpl implements CommunityOrderService {

	private Logger logger = LoggerFactory.getLogger(CommunityOrderServiceImpl.class);

	@Autowired
	private WechatUnifiedOrderService wechatUnifiedOrderService;

	@Autowired
	private ExchangeRateService exchangeRateService;

	/** 数据访问接口 */
	@Autowired
	private CommunityOrderMapper communityOrderMapper;

	/**
	 * 新增communityOrder
	 *
	 * @param communityOrder
	 * @return 新增的对象
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public void insertCommunityOrder(CommunityOrder communityOrder) throws CommunityOrderException {
		try {
			communityOrderMapper.insertCommunityOrder(communityOrder);
		} catch (Exception e) {
			logger.error("新增CommunityOrder时报错", e);
			throw new CommunityOrderException("新增CommunityOrder时报错", e);
		}
	}

	/**
	 * 删除communityOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public int deleteCommunityOrder(long id) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.deleteCommunityOrder(id);
		} catch (Exception e) {
			logger.error("删除CommunityOrder时报错", e);
			throw new CommunityOrderException("删除CommunityOrder时报错", e);
		}
	}

	/**
	 * 修改communityOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public int updateCommunityOrder(Map<String, Object> condition) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.updateCommunityOrder(condition);
		} catch (Exception e) {
			logger.error("修改CommunityOrder时报错", e);
			throw new CommunityOrderException("修改CommunityOrder时报错", e);
		}
	}

	/**
	 * 查询communityOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public CommunityOrder readCommunityOrder(long id) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.readCommunityOrder(id);
		} catch (Exception e) {
			logger.error("查询CommunityOrder时报错", e);
			throw new CommunityOrderException("查询CommunityOrder时报错", e);
		}
	}

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public List<CommunityOrder> queryCommunityOrderList(Map<String, Object> condition) {
		try {
			return this.communityOrderMapper.queryCommunityOrderList(condition);
		} catch (Exception e) {
			logger.error("查询CommunityOrder时报错", e);
			return null;
		}
	}

	/**
	 * 查询communityOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-9-28 新建
	 */
	@Override
	public long queryCommunityOrderCount(Map<String, Object> condition) throws CommunityOrderException {
		try {
			return this.communityOrderMapper.queryCommunityOrderCount(condition);
		} catch (Exception e) {
			logger.error("查询CommunityOrder时报错", e);
			throw new CommunityOrderException("查询CommunityOrder时报错", e);
		}
	}

	@Override
	public BaseDTO pay(CommunityOrderDTO orderDTO, UserSession userSession, Community community, Room room, Coupon coupon) throws CommunityOrderException {
		try {
			Map<String, String> payParams = new HashMap<>(20);
			// 创建订单
			CommunityOrder order = buildeOrder(orderDTO, userSession, community, room, coupon);
			if (BigDecimalUtils.compareTo(order.getRealPayRmb(), BigDecimal.ZERO) <= 0) {
				payParams.put("payFlag", Constants.NO);
			} else {
				// 调用微信统一下单接口并构造微信支付前端参数
				PayDTO payDTO = new PayDTO();
				payDTO.setAmount(order.getRealPayRmb());
				payDTO.setOrderCode(order.getCode());
				payDTO.setOrderSource(order.getOrderSource());
				payParams.putAll(wechatUnifiedOrderService.unifiedorder(payDTO, userSession));
				payParams.put("payFlag", Constants.YES);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, payParams);
		} catch (Exception e) {
			logger.error("下单支付报错! [orderDTO={}\r\n, userSession={}\r\n, community={}\r\n, room={}\r\n, coupon={}\r\n]", new Object[] { GsonUtils.toSimpleJson(orderDTO), GsonUtils.toSimpleJson(userSession), GsonUtils.toSimpleJson(community), GsonUtils.toSimpleJson(room), GsonUtils.toSimpleJson(coupon) }, e);
			throw new CommunityOrderException("下单支付报错!", e);
		}
	}

	/**
	 * 构造订单对象
	 *
	 * @param orderDTO
	 * @param userSession
	 * @param community
	 * @param room
	 * @param coupon
	 * @return
	 * @history 2017年10月3日 新建
	 * @auther xiaowen
	 */
	private CommunityOrder buildeOrder(CommunityOrderDTO orderDTO, UserSession userSession, Community community, Room room, Coupon coupon) throws CommunityOrderException {
		try {

			CommunityOrder order = new CommunityOrder();
			order.setCode("co-" + System.currentTimeMillis() + "-" + (new Random().nextInt(79999) + 10000));
			order.setUserId(userSession.getUserId());
			order.setCommunityId(community.getId());
			order.setCommunityName(community.getName());
			order.setCommunityType(community.getType());
			order.setCommunityImg(community.getCoverPicture());
			order.setCityId(community.getCityId());
			order.setCityName(community.getCityName());
			order.setDistrictId(community.getDistrictId());
			order.setDistrictName(community.getDistrictName());
			order.setAddress(community.getAddress());
			order.setRoomId(room.getId());
			order.setRoomName(room.getName());
			order.setRoomType(room.getType());
			order.setLeaseModelKey(room.getLeaseModelKey());
			order.setInDate(orderDTO.getInDate());
			order.setRealName(orderDTO.getRealName());
			order.setPhone(orderDTO.getPhone());
			order.setEmail(orderDTO.getEmail());
			order.setSchool(orderDTO.getSchool());
			order.setWechat(orderDTO.getWechat());
			// order.setTip(room.getTipPrice());
			order.setPrice(room.getPrice());
			order.setDiscountPrice(room.getDiscountPrice());
			order.setFirstRentMonth(room.getFirstRentMonth());
			order.setTipPrice(room.getTipPrice());
			order.setDepositPrice(room.getDepositPrice());
			order.setFirstDepositMonth(room.getFirstDepositMonth());
			order.setFirstAmount(room.getFirstAmount());
			order.setPayFlag(room.getPayFlag());
			order.setTipFlag(room.getTipFlag());
			if (CommunityConstants.ROOM_LEASE_MODEL_YUEZU.equals(room.getLeaseModelKey())) {
				// 月租计算退房日期
				order.setOutDate(orderDTO.getInDate().plusMonths(orderDTO.getLeaseMonth()));
				order.setStatus(CommunityOrderStatusEnum.NEW.getKey());
				order.setPayStatus(CommunityOrderPayStatusEnum.NEW.getKey());
				/** 月租计算价格开始 */
				if (Constants.NO.equals(room.getPayFlag())) {
					// 如果不需要支付，则付0元，订单成功
					order.setAmount(BigDecimal.ZERO);
					order.setStatus(CommunityOrderStatusEnum.PAID.getKey());
					order.setPayStatus(CommunityOrderPayStatusEnum.PAID.getKey());
				} else if (Constants.YES.equals(room.getTipFlag())) {
					// 如果仅需要支付定金，取后台设置的房型定金
					order.setAmount(room.getTipPrice());
				} else {
					// 取后台设置的押几个月付几个月房租已经计算好的价格来
					order.setAmount(room.getFirstAmount());
				}
				/** 计算价格结束 */
			} else {
				order.setOutDate(orderDTO.getOutDate());
				order.setStatus(CommunityOrderStatusEnum.NEW.getKey());
				order.setPayStatus(CommunityOrderPayStatusEnum.NEW.getKey());
				// 短租价格天数*单价
				long days = orderDTO.getInDate().until(orderDTO.getOutDate(), ChronoUnit.DAYS);
				if (days <= 0) {
					days = 1;
				}
				order.setAmount(BigDecimalUtils.multiply(order.getDiscountPrice(), new BigDecimal(days)));
			}
			// 获取汇率
			BaseDTO exchangeRateDTO = exchangeRateService.getExchangeRate();
			ExchangeRate rate = exchangeRateDTO.getResultData(ExchangeRate.class);
			order.setExchangeRate(rate.getEuros());
			order.setCoupon(coupon == null ? BigDecimal.ZERO : coupon.getAmount());
			order.setRealPay(BigDecimalUtils.subtract(order.getAmount(), order.getCoupon()));
			order.setRealPayRmb(BigDecimalUtils.multiply(order.getRealPay(), rate.getEuros(), 2, BigDecimal.ROUND_HALF_UP));
			order.setPayType(orderDTO.getPayType());
			order.setOrderSource(orderDTO.getOrderSource());
			order.setIsDeleted(Constants.NO);
			order.setCreateBy(userSession.getUserId());
			order.setCreateTime(LocalDateTime.now());
			communityOrderMapper.insertCommunityOrder(order);
			return order;
		} catch (Exception e) {
			logger.error("创建订单失败：服务器内部错误！orderDTO={}, userSession={}, community={}, room={}, coupon={}", GsonUtils.toSimpleJson(orderDTO), GsonUtils.toSimpleJson(userSession), GsonUtils.toSimpleJson(community), GsonUtils.toSimpleJson(room), GsonUtils.toSimpleJson(coupon), e);
			throw new CommunityOrderException("创建订单失败：服务器内部错误！", e);
		}
	}

	@Override
	public CommunityOrder readCommunityOrderByCode(String code) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("code", code);
		condition.put("isDeleted", Constants.NO);
		List<CommunityOrder> lst = this.communityOrderMapper.queryCommunityOrderList(condition);
		if (CollectionUtils.isEmpty(lst)) {
			return null;
		}
		return lst.get(0);
	}

	@Override
	public BaseDTO callback(CommunityOrder order, WechatCallback wechatCallback) throws CommunityOrderException {
		try {
			/** 检查订单 */
			if (order == null) {
				logger.warn("支付回调失败：租房订单未找到，wechatCallback={}", GsonUtils.toSimpleJson(wechatCallback));
				return new BaseDTO(RequestResultEnum.FAILURE.getKey(), "订单未找到", null);
			}
			// 未支付或者微信处理失败直接返回处理成功
			if (!"SUCCESS".equals(wechatCallback.getReturnCode()) || !"SUCCESS".equals(wechatCallback.getResultCode())) {
				logger.warn("支付回调失败：微信处理失败或用户支付失败！wechatCallback={}, order={}", GsonUtils.toSimpleJson(wechatCallback), GsonUtils.toSimpleJson(order));
				return new BaseDTO(RequestResultEnum.SUCCESS, null);
			}
			// 已支付的订单再次回调直接返回处理成功
			if (CommunityOrderPayStatusEnum.PAID.getKey().equals(order.getPayStatus())) {
				logger.debug("订单已支付，无需处理！wechatCallback={}, order={}", GsonUtils.toSimpleJson(wechatCallback), GsonUtils.toSimpleJson(order));
				return new BaseDTO(RequestResultEnum.SUCCESS, null);
			}
			// 检查订单金额
			if (order.getRealPay().floatValue() * 100 != wechatCallback.getTotalFee()) {
				logger.warn("支付回调失败：订单支付金额不匹配, wechatCallback={}, order={}", GsonUtils.toSimpleJson(wechatCallback), GsonUtils.toSimpleJson(order));
				return new BaseDTO(RequestResultEnum.FAILURE.getKey(), "订单支付金额不匹配", null);
			}
			// 支付成功后修改支付状态
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", order.getId());
			condition.put("payStatus", CommunityOrderPayStatusEnum.PAID.getKey());
			condition.put("status", CommunityOrderStatusEnum.PAID.getKey());
			condition.put("modifyTime", LocalDateTime.now());
			this.communityOrderMapper.updateCommunityOrder(condition);
			logger.warn("微信支付回调成功：order={}, wechatCallback={}", GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(wechatCallback));
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("支付回调失败：服务器内部错误！order={}, wechatCallback={}", GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(wechatCallback));
			throw new CommunityOrderException("支付回调失败：服务器内部错误！", e);
		}
	}
}