package com.wemeCity.web.catering.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wemeCity.web.catering.model.CateringOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.catering.dto.CateringOrderCancelDTO;
import com.wemeCity.web.catering.dto.CateringOrderDistributeDTO;
import com.wemeCity.web.catering.dto.CateringOrderQueryDTO;
import com.wemeCity.web.catering.dto.CateringOrderSettlingDTO;
import com.wemeCity.web.catering.dto.CateringPayDTO;
import com.wemeCity.web.catering.model.CateringCourier;
import com.wemeCity.web.catering.model.CateringOrder;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringCourierService;
import com.wemeCity.web.catering.service.CateringOrderDetailService;
import com.wemeCity.web.catering.service.CateringOrderService;
import com.wemeCity.web.catering.service.CateringRestaurantService;
import com.wemeCity.web.catering.utils.CateringConstants;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/catering/order")
public class CateringOrderController extends BaseController {

	@Autowired
	private CateringOrderService cateringOrderService;

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private CateringOrderDetailService cateringOrderDetailService;

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@Autowired
	private CateringCourierService cateringCourierService;

	@ResponseBody
	@PostMapping("/pay")
	public BaseDTO pay(@RequestBody CateringPayDTO payDTO) {
		try {
			if (payDTO == null || payDTO.getContactsId() <= 0 || payDTO.getRestaurantId() <= 0 || StringUtils.isEmpty(payDTO.getUserKey()) || StringUtils.isEmpty(payDTO.getPayType()) || StringUtils.isEmpty(payDTO.getGoodsIdStr()) || StringUtils.isEmpty(payDTO.getCountStr())) {
				logger.warn("好吃下单失败：参数校验失败！payDTO={}", GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			if (!Constants.PAY_TYPE_ALIPAY.equals(payDTO.getPayType()) && !Constants.PAY_TYPE_WECHAT.equals(payDTO.getPayType()) && !Constants.PAY_TYPE_OFFLINE.equals(payDTO.getPayType())) {
				logger.warn("好吃下单失败：非法的支付方式！payDTO={}", GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.INVALID_PAY_TYPE, null);
			}
			UserSession userSession = userSessionService.readUserSessionByUserKey(payDTO.getUserKey());
			if (userSession == null) {
				logger.warn("好吃下单失败：用户不存在或登录已失效！payDTO={}", GsonUtils.toSimpleJson(payDTO));
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			return cateringOrderService.pay(payDTO, userSession);
		} catch (Exception e) {
			logger.error("好吃下单失败：服务器内部错误！", e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 获取商户订单统计信息
	 *
	 * @param restaurantId
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryStatisticsInfo/{restaurantId}")
	public BaseDTO queryStatisticsInfo(@PathVariable long restaurantId) {
		try {
			Map<String, Long> result = new HashMap<>(5);
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("isDeleted", Constants.NO);
			condition.put("status", CateringConstants.ORDER_STATUS_PAID);
			condition.put("restaurantId", restaurantId);
			// 待确认
			result.put("paid", cateringOrderService.queryCateringOrderCount(condition));
			// 待配送
			condition.put("status", CateringConstants.ORDER_STATUS_CONFIRMED);
			result.put("confirmed", cateringOrderService.queryCateringOrderCount(condition));
			// 待结算
			condition.put("status", CateringConstants.ORDER_STATUS_DISTRIBUTED);
			result.put("distributed", cateringOrderService.queryCateringOrderCount(condition));
			return new BaseDTO(RequestResultEnum.SUCCESS, result);
		} catch (Exception e) {
			logger.error("获取订单统计数据失败：服务器内部错误！restaurantId={}", restaurantId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 查询订单列表
	 *
	 * @param queryDTO
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/queryOrderList")
	public BaseDTO queryOrderList(@RequestBody CateringOrderQueryDTO queryDTO) {
		try {
			if (queryDTO == null || queryDTO.getRestaurantId() <= 0 || queryDTO.getPageNum() < 1 || StringUtils.isEmpty(queryDTO.getStatus())) {
				logger.warn("查询订单失败：非空参数校验失败！queryDTO={}", GsonUtils.toSimpleJson(queryDTO));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			Map<String, Object> condition = new HashMap<>(8);
			condition.put("restaurantId", queryDTO.getRestaurantId());
			condition.put("status", queryDTO.getStatus());
			if (queryDTO.getCourierId() > 0) {
				condition.put("courierId", queryDTO.getCourierId());
			}
			Page<CateringOrder> page = PageHelper.startPage(queryDTO.getPageNum(), Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringOrderService.queryCateringOrderList(condition));
			if (!CollectionUtils.isEmpty(page)) {
				for (CateringOrder order : page) {
					order.setLstDetail(cateringOrderDetailService.queryOrderDetailList(order.getId()));
				}
			}
			return new PageDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("查询订单失败：服务器内部错误！queryDTO={}", GsonUtils.toSimpleJson(queryDTO), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 管理端取消订单
	 *
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/cancel")
	public BaseDTO cancel(@RequestBody CateringOrderCancelDTO dto) {
		try {
			if (dto == null || dto.getManagerId() <= 0 || dto.getOrderId() <= 0) {
				logger.warn("取消订单失败：非空参数校验失败！dto={}", GsonUtils.toSimpleJson(dto));
			}
			CateringOrder order = cateringOrderService.readCateringOrder(dto.getOrderId());
			if (order == null || Constants.YES.equals(order.getIsDeleted())) {
				logger.warn("取消订单失败：订单不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_NOT_FOUND, null);
			}
//			if (!CateringConstants.ORDER_STATUS_PAID.equals(order.getStatus()) && !CateringConstants.ORDER_STATUS_CONFIRMED.equals(order.getStatus())) {
//				logger.warn("取消订单失败：只允许取消已支付和待配送订单！dto={}", GsonUtils.toSimpleJson(dto));
//				return new BaseDTO(RequestResultEnum.WRONG_CATERING_ORDER_STATUS.getKey(), "只允许取消已支付和待配送订单！", null);
//			}
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(order.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("取消订单失败：店铺不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
//			logger.info("登录商户ID [{}]",dto.getManagerId());
//			logger.info("订单饭店ID [{}]",restaurant.getManagerId());
			if (dto.getManagerId() != restaurant.getManagerId()) {
				logger.warn("取消订单失败：用户不匹配！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", order.getId());
			condition.put("cancelReason", dto.getCancelReason());
			condition.put("cancelRemark", dto.getCancelRemark());
			condition.put("status", CateringConstants.ORDER_STATUS_CANCEL);
			condition.put("modifyBy", dto.getManagerId());
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("取消订单失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 管理端确认订单
	 *
	 * @param managerId
	 * @param orderId
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/confirm/{managerId}/{orderId}")
	public BaseDTO confirm(@PathVariable long managerId, @PathVariable long orderId) {
		try {
			CateringOrder order = cateringOrderService.readCateringOrder(orderId);
			if (order == null || Constants.YES.equals(order.getIsDeleted())) {
				logger.warn("确认订单失败：订单不存在！managerId={}, orderId={}", managerId, orderId);
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_NOT_FOUND, null);
			}
			if (!CateringConstants.ORDER_STATUS_PAID.equals(order.getStatus())) {
				logger.warn("确认订单失败：只有已支付的订单允许确认！managerId={}, orderId={}", managerId, orderId);
				return new BaseDTO(RequestResultEnum.WRONG_CATERING_ORDER_STATUS.getKey(), "只允许确认新订单！", null);
			}
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(order.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("确认订单失败：店铺不存在！managerId={}, orderId={}", managerId, orderId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			if (managerId != restaurant.getManagerId()) {
				logger.warn("确认订单失败：用户不匹配！managerId={}, orderId={}", managerId, orderId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", order.getId());
			condition.put("status", CateringConstants.ORDER_STATUS_CONFIRMED);
			condition.put("confirmTime", LocalDateTime.now());
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("确认订单失败：服务器内部错误！managerId={}, orderId={}", managerId, orderId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 管理端配送订单
	 *
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/distribute")
	public BaseDTO distribute(@RequestBody CateringOrderDistributeDTO dto) {
		try {
			if (dto == null || dto.getManagerId() <= 0 || dto.getOrderId() <= 0) {
				logger.warn("配送订单失败：非空参数校验失败！dto={}", GsonUtils.toSimpleJson(dto));
			}
			CateringOrder order = cateringOrderService.readCateringOrder(dto.getOrderId());
			if (order == null || Constants.YES.equals(order.getIsDeleted())) {
				logger.warn("配送订单失败：订单不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_NOT_FOUND, null);
			}
			if (!CateringConstants.ORDER_STATUS_CONFIRMED.equals(order.getStatus())) {
				logger.warn("配送订单失败：只有已支付的订单允许确认！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.WRONG_CATERING_ORDER_STATUS.getKey(), "只允许配送已确认订单！", null);
			}
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(order.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("配送订单失败：店铺不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			if (dto.getManagerId() != restaurant.getManagerId()) {
				logger.warn("配送订单失败：用户不匹配！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			CateringCourier courier = cateringCourierService.readCateringCourier(dto.getCourierId());
			if (courier == null || Constants.YES.equals(courier.getIsDeleted()) || courier.getRestaurantId() != restaurant.getId()) {
				logger.warn("配送订单失败：配送员不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.CATERING_COURIER_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", order.getId());
			condition.put("courierId", courier.getId());
			condition.put("courierName", courier.getName());
			condition.put("courierPhone", courier.getPhone());
			condition.put("distributionTime", LocalDateTime.now());
			condition.put("status", CateringConstants.ORDER_STATUS_DISTRIBUTED);
			condition.put("modifyBy", dto.getManagerId());
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("确认订单失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 管理端结算订单
	 *
	 * @return
	 * @history 2017年12月28日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/settling")
	public BaseDTO settling(@RequestBody CateringOrderSettlingDTO dto) {
		try {
			if (dto == null || dto.getManagerId() <= 0 || dto.getOrderId() <= 0) {
				logger.warn("结算订单失败：非空参数校验失败！dto={}", GsonUtils.toSimpleJson(dto));
			}
			CateringOrder order = cateringOrderService.readCateringOrder(dto.getOrderId());
			if (order == null || Constants.YES.equals(order.getIsDeleted())) {
				logger.warn("结算订单失败：订单不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.CATERING_ORDER_NOT_FOUND, null);
			}
			if (!CateringConstants.ORDER_STATUS_DISTRIBUTED.equals(order.getStatus())) {
				logger.warn("结算订单失败：只有已配送的订单允许结算！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.WRONG_CATERING_ORDER_STATUS.getKey(), "只允许结算已配送订单！", null);
			}
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(order.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("结算订单失败：店铺不存在！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			if (dto.getManagerId() != restaurant.getManagerId()) {
				logger.warn("结算订单失败：用户不匹配！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", order.getId());
			condition.put("settlementAmount", dto.getSettlementAmount());
			condition.put("settlementRemark", dto.getSettlementRemark());
			condition.put("settlementTime", LocalDateTime.now());
			condition.put("status", CateringConstants.ORDER_STATUS_COMPLETE);
			condition.put("modifyBy", dto.getManagerId());
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("结算订单失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/queryTodayStatisticsInfo/{restaurantId}")
	public BaseDTO queryTodayStatisticsInfo(@PathVariable long restaurantId) {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("restaurantId", restaurantId);
			condition.put("today", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDate());
			condition.put("yesterday", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDate().minusDays(1));
			condition.put("payStatus", Constants.PAY_STATUS_PAID);
			return new BaseDTO(RequestResultEnum.SUCCESS, cateringOrderService.queryTodayStatisticsInfo(condition));
		} catch (Exception e) {
			logger.error("统计当天订单数据失败：服务器内部错误！restaurantId={}", restaurantId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/queryMyOrderList/{userKey}/{pageNum}")
	public BaseDTO queryMyOrderList(@PathVariable String userKey, @PathVariable int pageNum) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
			if (userSession == null) {
				logger.warn("查询我的订单失败：用户不存在或登录已失效！userKey={}", userKey);
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("userId", userSession.getUserId());
			condition.put("payStatus", Constants.PAY_STATUS_PAID);
			condition.put("isDeleted", Constants.NO);
			condition.put("sortColumn","create_time");
			condition.put("sortType","DESC");
			Page<CateringOrder> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringOrderService.queryCateringOrderList(condition));
			for (CateringOrder order : page) {
				order.setLstDetail(cateringOrderDetailService.queryOrderDetailList(order.getId()));
				order.setRestaurant(cateringRestaurantService.getRestaurantInfo(order.getRestaurantId()));
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("查询我的订单失败：服务器内部错误！userKey={}", userKey, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/cancelMyOrder/{userKey}/{orderId}")
	public BaseDTO cancelMyOrder(@PathVariable String userKey, @PathVariable long orderId) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
			if (userSession == null) {
				logger.warn("取消我的订单失败：用户不存在或登录已失效！userKey={}, orderId={}", userKey, orderId);
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			CateringOrder order = cateringOrderService.readCateringOrder(orderId);
			if (!CateringConstants.ORDER_STATUS_PAID.equals(order.getStatus()) && !CateringConstants.ORDER_STATUS_NEW.equals(order.getStatus())) {
				logger.warn("取消失败：未支付或待确认的订单才允许取消！userKey={}, orderId={}", userKey, orderId);
				return new BaseDTO(RequestResultEnum.WRONG_CATERING_ORDER_STATUS.getKey(), "取消失败：未支付或待确认的订单才允许取消！", null);
			}
			Map<String, Object> condition = new HashMap<>(8);
			condition.put("id", orderId);
			condition.put("status", CateringConstants.ORDER_STATUS_CANCEL);
			condition.put("modifyBy", userSession.getUserId());
			condition.put("modifyTime", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime());
			cateringOrderService.updateCateringOrder(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("取消我的订单失败：服务器内部错误！userKey={}, orderId={}", userKey, orderId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
