package com.wemeCity.web.catering.service.impl;

import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PayCallback;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.components.exchangeRate.model.ExchangeRate;
import com.wemeCity.components.exchangeRate.service.ExchangeRateService;
import com.wemeCity.components.wechatPay.dto.PayDTO;
import com.wemeCity.components.wechatPay.model.WechatConfig;
import com.wemeCity.components.wechatPay.service.WechatUnifiedOrderService;
import com.wemeCity.web.catering.dto.CateringAllData;
import com.wemeCity.web.catering.dto.CateringMonthData;
import com.wemeCity.web.catering.dto.CateringPayDTO;
import com.wemeCity.web.catering.dto.TodayStatisticsInfo;
import com.wemeCity.web.catering.exception.CateringOrderException;
import com.wemeCity.web.catering.mapper.CateringOrderMapper;
import com.wemeCity.web.catering.model.*;
import com.wemeCity.web.catering.service.*;
import com.wemeCity.web.catering.utils.CateringConstants;
import com.wemeCity.web.user.model.User;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * CateringOrderServiceImpl AppService类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
@Service
public class CateringOrderServiceImpl implements CateringOrderService {

	private Logger logger = LoggerFactory.getLogger(CateringOrderServiceImpl.class);

	@Autowired
	private WechatConfig wechatConfig;

	@Autowired
	private CateringOrderDetailService cateringOrderDetailService;

	@Autowired
	private UserService userService;

	@Autowired
	private CateringGoodsService cateringGoodsService;

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@Autowired
	private CateringRestaurantLocationService cateringRestaurantLocationService;

	@Autowired
	private CateringContactsService cateringContactsService;

	@Autowired
	private CateringDiscountService cateringDiscountService;

	@Autowired
	private WechatUnifiedOrderService wechatUnifiedOrderService;

	@Autowired
	private ExchangeRateService exchangeRateService;

	/** 数据访问接口 */
	@Autowired
	private CateringOrderMapper cateringOrderMapper;

	/**
	 * 新增cateringOrder
	 *
	 * @param cateringOrder
	 * @return 新增的对象
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public void insertCateringOrder(CateringOrder cateringOrder) throws CateringOrderException {
		try {
			cateringOrderMapper.insertCateringOrder(cateringOrder);
		} catch (Exception e) {
			logger.error("新增CateringOrder时报错", e);
			throw new CateringOrderException("新增CateringOrder时报错", e);
		}
	}

	/**
	 * 删除cateringOrder
	 *
	 * @param id 主键
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int deleteCateringOrder(long id) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.deleteCateringOrder(id);
		} catch (Exception e) {
			logger.error("删除CateringOrder时报错", e);
			throw new CateringOrderException("删除CateringOrder时报错", e);
		}
	}

	/**
	 * 修改cateringOrder
	 *
	 * @param condition
	 * @return
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public int updateCateringOrder(Map<String, Object> condition) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.updateCateringOrder(condition);
		} catch (Exception e) {
			logger.error("修改CateringOrder时报错", e);
			throw new CateringOrderException("修改CateringOrder时报错", e);
		}
	}

	/**
	 * 查询cateringOrder
	 *
	 * @param id 主键
	 * @return 根据主键查找的内容
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public CateringOrder readCateringOrder(long id) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.readCateringOrder(id);
		} catch (Exception e) {
			logger.error("查询CateringOrder时报错", e);
			throw new CateringOrderException("查询CateringOrder时报错", e);
		}
	}

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public List<CateringOrder> queryCateringOrderList(Map<String, Object> condition) {
		try {
			return this.cateringOrderMapper.queryCateringOrderList(condition);
		} catch (Exception e) {
			logger.error("查询CateringOrder时报错", e);
			return null;
		}
	}

	/**
	 * 查询cateringOrder集合
	 *
	 * @param condition 查询条件
	 * @return 符合查询条件的记录数
	 * @author 向小文 2017-12-5 新建
	 */
	@Override
	public long queryCateringOrderCount(Map<String, Object> condition) throws CateringOrderException {
		try {
			return this.cateringOrderMapper.queryCateringOrderCount(condition);
		} catch (Exception e) {
			logger.error("查询CateringOrder时报错", e);
			throw new CateringOrderException("查询CateringOrder时报错", e);
		}
	}

	@Override
	public BaseDTO callback(PayCallback callback) {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("code", callback.getOrderCode());
			condition.put("isDeleted", Constants.NO);
			List<CateringOrder> lstOrders = this.cateringOrderMapper.queryCateringOrderList(condition);
			if (CollectionUtils.isEmpty(lstOrders)) {
				logger.warn("支付回调失败：订单不存在！callback={}", GsonUtils.toSimpleJson(callback));
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_NOT_FOUND, null);
			}
			CateringOrder order = lstOrders.get(0);
			if (Constants.PAY_STATUS_PAID.equals(order.getPayStatus())) {
				logger.warn("支付回调成功：重复回调，无需处理！callback={}, order={}", GsonUtils.toSimpleJson(callback), GsonUtils.toSimpleJson(order));
				return new BaseDTO(RequestResultEnum.SUCCESS, null);
			}
			if (!Constants.PAY_STATUS_NEW.equals(order.getPayStatus())) {
				logger.warn("支付回调失败：状态异常！callback={}", GsonUtils.toSimpleJson(callback));
				return new BaseDTO(RequestResultEnum.WRONG_CATERING_ORDER_STATUS, null);
			}
			condition.clear();
			condition.put("id", order.getId());
			condition.put("payStatus", Constants.PAY_STATUS_PAID);
			condition.put("status", CateringConstants.ORDER_STATUS_PAID);
			condition.put("modifyBy", order.getUserId());
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderMapper.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("支付回调失败：服务器内部错误！callback={}", GsonUtils.toSimpleJson(callback), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@Override
	public BaseDTO pay(CateringPayDTO payDTO, UserSession userSession) throws CateringOrderException {
		try {
			// 生成订单
			BaseDTO genOrderDTO = this.genCateringOrder(payDTO, userSession);
			if (!RequestResultEnum.SUCCESS.getKey().equals(genOrderDTO.getResultCode())) {
				return genOrderDTO;
			}
			CateringOrder order = genOrderDTO.getResultData(CateringOrder.class);
			// 支付
			Map<String, String> payParams = this.genPayParams(order, userSession);
			return new BaseDTO(RequestResultEnum.SUCCESS, payParams);
		} catch (Exception e) {
			logger.error("好吃下单支付失败：服务器内部错误！payDTO={}", GsonUtils.toSimpleJson(payDTO), e);
			throw new CateringOrderException("好吃下单支付失败：服务器内部错误", e);
		}
	}

	private Map<String, String> genPayParams(CateringOrder order, UserSession userSession) throws CateringOrderException {
		try {
			if (Constants.PAY_TYPE_WECHAT.equals(order.getPayType())) {
				PayDTO payDTO = new PayDTO();
				payDTO.setOrderCode(order.getCode());
				payDTO.setOrderSource(order.getOrderSource());
				payDTO.setAmount(order.getCashAmountRmb());
				return wechatUnifiedOrderService.unifiedorder(payDTO, userSession);
			}
			return null;
		} catch (Exception e) {
			logger.error("好吃下单生成支付参数失败：order={}, userSession={}", GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(userSession));
			throw new CateringOrderException("好吃下单生成支付参数失败：服务器内部错误", e);
		}
	}

	/**
	 * 生成订单
	 *
	 * @param payDTO
	 * @return
	 * @throws CateringOrderException
	 * @history 2017年12月9日 新建
	 * @auther xiaowen
	 */
	private BaseDTO genCateringOrder(CateringPayDTO payDTO, UserSession userSession) throws CateringOrderException {
		try {
			User user = userService.readUser(userSession.getUserId());
			if (user == null) {
				logger.warn("生成好吃订单失败：用户不存在！payDTO={}", GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			String[] arrGoodsId = payDTO.getGoodsIdStr().split(",");
			String[] arrGoodsCount = payDTO.getCountStr().split(",");
			if (arrGoodsCount.length != arrGoodsId.length) {
				logger.warn("生成好吃订单失败：非法的商品数量！payDTO={}", GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.INVALID_GOODS_COUNT, null);
			}
			CateringOrder order = new CateringOrder();
			// 创建订单明细
			BaseDTO createDetailDTO = this.createCateringOrderDetail(order, user, arrGoodsId, arrGoodsCount);
			if (!RequestResultEnum.SUCCESS.getKey().equals(createDetailDTO.getResultCode())) {
				return createDetailDTO;
			}
			// 创建订单并插入
			BaseDTO createOrderDTO = this.createOrder(order, user, payDTO);
			if (!RequestResultEnum.SUCCESS.getKey().equals(createOrderDTO.getResultCode())) {
				return createOrderDTO;
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, order);
		} catch (Exception e) {
			logger.error("生成好吃订单失败：服务器内部错误！payDTO={}", GsonUtils.toSimpleJson(payDTO), e);
			throw new CateringOrderException("下单支付失败：服务器内部错误", e);
		}
	}

	/**
	 * 创建订单明细
	 *
	 * @param order
	 * @param user
	 * @param arrGoodsId
	 * @param arrGoodsCount
	 * @return
	 * @throws CateringOrderException
	 * @history 2017年12月9日 新建
	 * @auther xiaowen
	 */
	private BaseDTO createCateringOrderDetail(CateringOrder order, User user, String[] arrGoodsId, String[] arrGoodsCount) throws CateringOrderException {
		try {
			List<CateringOrderDetail> lstDetail = new ArrayList<>();
			for (int i = 0; i < arrGoodsId.length; i++) {
				long goodsId = Long.valueOf(arrGoodsId[i]);
				int count = Integer.valueOf(arrGoodsCount[i]);
				// 检查商品与数量是否合法
				if (goodsId <= 0 || count <= 0) {
					logger.warn("创建好吃订单明细失败：非法的商品数量！user={},arrGoodsId={},arrGoodsCount={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(arrGoodsId), GsonUtils.toSimpleJson(arrGoodsCount));
					return new BaseDTO(RequestResultEnum.INVALID_GOODS_COUNT, null);
				}
				CateringOrderDetail detail = new CateringOrderDetail();
				CateringGoods goods = cateringGoodsService.readCateringGoods(Long.valueOf(arrGoodsId[i]));
				if (goods == null) {
					logger.warn("创建好吃订单明细失败：商品不存在！user={},arrGoodsId={},arrGoodsCount={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(arrGoodsId), GsonUtils.toSimpleJson(arrGoodsCount));
					return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
				}
				detail.setUserId(user.getId());
				detail.setGoodsId(goods.getId());
				detail.setGoodsName(goods.getName());
				detail.setPrice(goods.getPrice());
				detail.setDiscountPrice(goods.getDiscountPrice());
				detail.setCount(count);
				detail.setAmount(BigDecimalUtils.multiply(goods.getDiscountPrice(), new BigDecimal(count + "")));
				detail.setIsDeleted(Constants.NO);
				detail.setCreateBy(user.getId());
				detail.setCreateTime(ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
				lstDetail.add(detail);
				// 计算订单汇总信息
				order.setPrice(BigDecimalUtils.add(order.getPrice(), BigDecimalUtils.multiply(goods.getPrice(), new BigDecimal(count + ""))));
				order.setDiscountPrice(BigDecimalUtils.add(order.getDiscountPrice(), detail.getAmount()));
			}
			order.setLstDetail(lstDetail);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("创建好吃订单明细失败：服务器内部错误！user={},arrGoodsId={},arrGoodsCount={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(arrGoodsId), GsonUtils.toSimpleJson(arrGoodsCount), e);
			throw new CateringOrderException("创建好吃订单明细失败：服务器内部错误！", e);
		}
	}

	/**
	 * 创建订单
	 *
	 * @param order
	 * @param user
	 * @param payDTO
	 * @return
	 * @throws CateringOrderException
	 * @history 2017年12月9日 新建
	 * @auther xiaowen
	 */
	private BaseDTO createOrder(CateringOrder order, User user, CateringPayDTO payDTO) throws CateringOrderException {
		try {
			// 店铺
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(payDTO.getRestaurantId());
			if (restaurant == null) {
				logger.warn("创建好吃订单失败：店铺不存在！user={},order={},payDTO={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			order.setCode("ct-" + System.currentTimeMillis() + "-" + (new Random().nextInt(79999) + 10000));
			order.setUserId(user.getId());
			order.setRestaurantId(restaurant.getId());
			order.setPayType(payDTO.getPayType());
			order.setDistributionAmount(restaurant.getDistributionAmount());
			// 获取联系人
			CateringContacts contacts = cateringContactsService.readCateringContacts(payDTO.getContactsId());
			if (contacts == null) {
				logger.warn("创建好吃订单失败：联系人不存在！user={},order={},payDTO={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.CONTACTS_NOT_FOUND, null);
			}
			// 计算优惠
			List<CateringDiscount> lstCateringDiscount = cateringDiscountService.queryRestaurantDisCount(restaurant.getId());
			if (!CollectionUtils.isEmpty(lstCateringDiscount)) {
				BigDecimal couponAmount = BigDecimal.ZERO;
				String couponName = "";
				DecimalFormat formater = new DecimalFormat("##0.##");
				for (CateringDiscount discount : lstCateringDiscount) {
					if (CateringConstants.DISCOUNT_TYPE_MANJIAN.equals(discount.getType())) {
						// 满减
						if (BigDecimalUtils.compareTo(order.getDiscountPrice(), discount.getTarget()) >= 0) {
							couponAmount = BigDecimalUtils.add(couponAmount, discount.getDiscount());
							couponName += "满" + discount.getTarget() + "减" + discount.getDiscount() + ",";
						}
					} else if (CateringConstants.DISCOUNT_TYPE_QUANCHANGZHEKOU.equals(discount.getType())) {
						// 全场折扣
						BigDecimal realPay = BigDecimalUtils.multiply(order.getDiscountPrice(), discount.getDiscount());
						couponAmount = BigDecimalUtils.add(couponAmount, BigDecimalUtils.subtract(order.getDiscountPrice(), realPay));
						couponName += "全场" + formater.format(BigDecimalUtils.multiply(discount.getDiscount(), new BigDecimal("100"))) + "折,";
					} else if (CateringConstants.DISCOUNT_TYPE_XINREN.equals(discount.getType())) {
						// 新人直减
						String newMemberFlag = this.checkNewMember(user.getId(), restaurant.getId());
						if (Constants.YES.equals(newMemberFlag)) {
							couponAmount = BigDecimalUtils.add(couponAmount, discount.getDiscount());
							couponName += "新人立减" + formater.format(discount.getDiscount()) + ",";
						}
					}
				}
				order.setCouponAmount(couponAmount);
				if(!StringUtils.isEmpty(couponName))
					order.setCouponName(couponName.substring(0, couponName.length() - 1));
			} else {
				order.setCouponAmount(BigDecimal.ZERO);
				order.setCouponName("");
			}
			order.setDistributionAmount(restaurant.getDistributionAmount());
			BigDecimal cashAmount = BigDecimalUtils.subtract(order.getDiscountPrice(), order.getCouponAmount());
			if (BigDecimalUtils.compareTo(cashAmount, BigDecimal.ZERO) <= 0) {
				cashAmount = BigDecimal.ZERO;
			}
			order.setCashAmount(BigDecimalUtils.add(cashAmount, restaurant.getDistributionAmount()));
			// 获取汇率
			BaseDTO exchangeRateDTO = exchangeRateService.getExchangeRate();
			ExchangeRate rate = exchangeRateDTO.getResultData(ExchangeRate.class);
			order.setExchangeRate(rate.getEuros());
			order.setCashAmountRmb(BigDecimalUtils.multiply(order.getCashAmount(), rate.getEuros(), 2, BigDecimal.ROUND_HALF_UP));
			// 填充基本信息
			order.setCommentFlag(Constants.NO);
			order.setCityCode(contacts.getCityCode());
			order.setCityName(contacts.getCityName());
			order.setDistrictCode(contacts.getDistrictCode());
			order.setDistrictName(contacts.getDistrictName());
			order.setAddress(contacts.getAddress());
			order.setName(contacts.getName());
			order.setPhone(contacts.getPhone());
			order.setOrderSource(payDTO.getOrderSource());
			if (Constants.PAY_TYPE_OFFLINE.equals(payDTO.getPayType())) {
				order.setPayStatus(Constants.PAY_STATUS_PAID);
				order.setStatus(CateringConstants.ORDER_STATUS_PAID);
			} else {
				order.setPayStatus(Constants.PAY_STATUS_NEW);
				order.setStatus(CateringConstants.ORDER_STATUS_NEW);
			}
			order.setIsDeleted(Constants.NO);
			order.setCreateBy(user.getId());

			//处理时间，设置为法国时区
			ZoneId france = ZoneId.of(ZoneId.SHORT_IDS.get("ECT"));
			LocalDateTime franceDateTime = ZonedDateTime.now(france).toLocalDateTime();
			order.setCreateTime(franceDateTime);
			// 统一配送点
			if (payDTO.getLocationId() > 0) {
				CateringRestaurantLocation location = cateringRestaurantLocationService.readCateringRestaurantLocation(payDTO.getLocationId());
				if (location == null) {
					logger.warn("创建好吃订单失败：配送点不存在！user={},order={},payDTO={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(payDTO));
					return new BaseDTO(RequestResultEnum.LOCATION_NOT_FOUND, null);
				}
				// 如果统一配送，则覆盖配送地址
				order.setDistributionLocation(location.getAddress());
				order.setAddress(location.getAddress());
			}
			// 隔日配送时间
			if(!StringUtils.isEmpty(payDTO.getDistributionNotes())){
				StringBuffer sb = new StringBuffer(franceDateTime.toLocalDate().plusDays(1).toString());
				sb.append(" "+payDTO.getDistributionNotes());
				order.setDistributionNotes(sb.toString());
			}
			if(!StringUtils.isEmpty(payDTO.getRemark())){
				order.setRemark(payDTO.getRemark());
			}

			// 插入数据库
			this.cateringOrderMapper.insertCateringOrder(order);
			// 插入明细
			List<CateringOrderDetail> lstDetail = order.getLstDetail();
			if (!CollectionUtils.isEmpty(lstDetail)) {
				for (CateringOrderDetail detail : lstDetail) {
					detail.setOrderId(order.getId());
					cateringOrderDetailService.insertCateringOrderDetail(detail);
				}
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("创建好吃订单失败：服务器内部错误！user={},order={},payDTO={}", GsonUtils.toSimpleJson(user), GsonUtils.toSimpleJson(order), GsonUtils.toSimpleJson(payDTO), e);
			throw new CateringOrderException("创建好吃订单失败：服务器内部错误！", e);
		}
	}

	@Override
	public long queryRestaurantOrderCountByUser(long userId, long restaurantId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userId);
		condition.put("isDeleted", Constants.NO);
		condition.put("restaurantId", restaurantId);
		return cateringOrderMapper.queryCateringOrderCount(condition);
	}

	@Override
	public TodayStatisticsInfo queryTodayStatisticsInfo(Map<String, Object> condition) throws CateringOrderException {
		try {
			return cateringOrderMapper.queryTodayStatisticsInfo(condition);
		} catch (Exception e) {
			logger.error("统计当天订单数据失败：服务器内部错误！condition={}", GsonUtils.toSimpleJson(condition), e);
			throw new CateringOrderException("统计当天订单数据失败：服务器内部错误！", e);
		}
	}

	@Override
	public String checkNewMember(long userId, long restaurantId) {
		Map<String, Object> condition = new HashMap<>(8);
		condition.put("userId", userId);
		condition.put("restaurantId", restaurantId);
		condition.put("payStatus", Constants.PAY_STATUS_PAID);
		condition.put("isDeleted", Constants.NO);
		long count = cateringOrderMapper.queryCateringOrderCount(condition);
		return count > 0 ? Constants.NO : Constants.YES;
	}

	@Override
    public CateringAllData queryAllData(Map<String, Object> condition) {
	    return cateringOrderMapper.queryAllData(condition);
    }

	@Override
    public CateringMonthData queryMonthData(Map<String, Object> condition) {
	    return cateringOrderMapper.queryMonthData(condition);
    }
}