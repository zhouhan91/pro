package com.wemeCity.web.community.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.community.dto.CommunityOrderDTO;
import com.wemeCity.web.community.dto.CommunityOrderQueryDTO;
import com.wemeCity.web.community.dto.CommunityQueryDTO;
import com.wemeCity.web.community.enums.CommunityImgBusiCodeEnum;
import com.wemeCity.web.community.enums.CommunityStatusEnum;
import com.wemeCity.web.community.model.CityCommunity;
import com.wemeCity.web.community.model.Community;
import com.wemeCity.web.community.model.CommunityMark;
import com.wemeCity.web.community.model.CommunityOrder;
import com.wemeCity.web.community.model.Room;
import com.wemeCity.web.community.service.CityCommunityService;
import com.wemeCity.web.community.service.CommunityImgService;
import com.wemeCity.web.community.service.CommunityMarkService;
import com.wemeCity.web.community.service.CommunityOrderService;
import com.wemeCity.web.community.service.CommunityService;
import com.wemeCity.web.community.service.FacilitiesService;
import com.wemeCity.web.community.service.RoomService;
import com.wemeCity.web.user.model.Coupon;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.CouponService;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

	@Autowired
	private CityCommunityService cityCommunityService;

	@Autowired
	private CommunityService communityService;

	@Autowired
	private CommunityImgService communityImgService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private FacilitiesService facilitiesService;

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private CommunityMarkService communityMarkService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private CommunityOrderService communityOrderService;

	/**
	 * 获取租房首页房源数
	 *
	 * @return
	 * @throws Exception
	 * @history 2017年9月16日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryCityCommunityIndexList")
	public BaseDTO queryCityCommunityIndexList() throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "sort_num");
		condition.put("sortType", "asc");
		Page<CityCommunity> page = PageHelper.startPage(1, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cityCommunityService.queryCityCommunityList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 房源列表获取该城市统计信息
	 *
	 * @param cityCommunityId
	 * @return
	 * @throws Exception
	 * @history 2017年9月17日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readCityCommunity/{cityCommunityId}")
	public BaseDTO readCityCommunity(@PathVariable long cityCommunityId) throws Exception {
		if (cityCommunityId <= 0) {
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR.getKey(), "cityCommunityId 必须大于0", null);
		}
		CityCommunity cityCommunity = cityCommunityService.readCityCommunity(cityCommunityId);
		return new BaseDTO(RequestResultEnum.SUCCESS, cityCommunity);
	}

	/**
	 * 查询房源列表
	 *
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年9月19日 新建
	 */
	@ResponseBody
	@PostMapping("/queryCommunityList/{pageNum}")
	public BaseDTO queryCommunityList(@PathVariable int pageNum, @RequestBody CommunityQueryDTO queryDTO) throws Exception {
		logger.warn("查询参数为：queryDTO={}", GsonUtils.toSimpleJson(queryDTO));
		Map<String, Object> condition = new HashMap<>(10);
		// 城市
		ConditionUtils.addStr(condition, "cityCode", queryDTO.getCityCode());
		// 区县
		ConditionUtils.addStr(condition, "districtCode", queryDTO.getDistrictCode());
		// 房源类型(中介、个人)
		ConditionUtils.addStr(condition, "sourceTypeLike", queryDTO.getSourceType());
		// 房屋类型(公寓、民宿等)
		ConditionUtils.addStr(condition, "typeLike", queryDTO.getType());
		// 租赁模式(日/月)
		ConditionUtils.addStr(condition, "leaseModelLike", queryDTO.getLeaseModel());
		// 房型(单人卧室、多人卧室、整租套房)
		ConditionUtils.addStr(condition, "roomTypeLike", queryDTO.getRoomType());
		// 卫浴类别(独立卫浴、公共卫浴)
		ConditionUtils.addStr(condition, "bathroomTypeLike", queryDTO.getBathroomType());
		// 关键词
		ConditionUtils.addStr(condition, "keyWords", queryDTO.getKeyWords());
		// 排序字段
		if (StringUtils.isEmpty(queryDTO.getSortColumn())) {
			ConditionUtils.addStr(condition, "sortColumn", "sort_num");
			ConditionUtils.addStr(condition, "sortType", "desc");
		}else{
			ConditionUtils.addStr(condition, "sortColumn", queryDTO.getSortColumn());
			ConditionUtils.addStr(condition, "sortType", queryDTO.getSortType());
		}
		// 状态
		condition.put("isDeleted", Constants.NO);
		condition.put("status", CommunityStatusEnum.ON_LINE.getKey());
		// 分页查询
		Page<Community> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> communityService.queryCommunityList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 读取房源详情
	 *
	 * @param communityId
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年9月19日 新建
	 */
	@ResponseBody
	@GetMapping("/readCommunityById/{communityId}")
	public BaseDTO readCommunityById(@PathVariable long communityId) throws Exception {
		Object communityIncache = EHCacheUtils.get("communityInfo", communityId + "");
		if (communityIncache == null) {
			Community community = communityService.readCommunity(communityId);
			if (community == null) {
				logger.warn("房源未找到！communityId={}", communityId);
				return new BaseDTO(RequestResultEnum.COMMUNITY_NOT_FOUND, null);
			}
			// 加载公寓图片
			community.setImages(communityImgService.queryCommunityImgList(CommunityImgBusiCodeEnum.COMMUNITY, communityId));
			// 加载房型
			community.setRooms(roomService.queryRoomListByCommunityId(communityId));
			// 加载公共设施
			community.setCommonFacilities(facilitiesService.queryFacilitiesList(community.getCommonFacilitiesStr()));
			// 加载房间设施
			community.setPrivateFacilities(facilitiesService.queryFacilitiesList(community.getPrivateFacilitiesStr()));
			// 加载房租包含
			community.setRentFacilities(facilitiesService.queryFacilitiesList(community.getRentFacilitiesStr()));
			// 返回
			logger.debug("读取社区信息成功，community={}", community);
			return new BaseDTO(RequestResultEnum.SUCCESS, community);
		}
		logger.debug("缓存中读取社区信息成功，communityIncache={}", communityIncache);
		return new BaseDTO(RequestResultEnum.SUCCESS, communityIncache);
	}

	/**
	 * 收藏
	 *
	 * @param userKey
	 * @param communityId
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年9月28日 新建
	 */
	@ResponseBody
	@PostMapping("/mark")
	public BaseDTO mark(String userKey, long communityId) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("收藏公寓失败：用户信息未找到或登录已失效! userKey={}, communityId={}", userKey, communityId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		return communityMarkService.mark(userSession.getUserId(), communityId);
	}

	/**
	 * 取消收藏
	 *
	 * @param userKey
	 * @param communityId
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年9月28日 新建
	 */
	@ResponseBody
	@PostMapping("/cancelMark")
	public BaseDTO cancelMark(String userKey, long communityId) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("取消收藏公寓失败：用户信息未找到或登录已失效! userKey={}, communityId={}", userKey, communityId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		return communityMarkService.cancelMark(userSession.getUserId(), communityId);
	}

	/**
	 * 检查是否已收藏
	 *
	 * @param userKey
	 * @param communityId
	 * @return
	 * @throws Exception
	 * @Author Xiang xiaowen 2017年9月28日 新建
	 */
	@ResponseBody
	@PostMapping("/checkMarked")
	public BaseDTO checkMarked(String userKey, long communityId) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("检查是否收藏公寓失败：用户信息未找到或登录已失效! userKey={}, communityId={}", userKey, communityId);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		CommunityMark communityMark = communityMarkService.checkMarked(userSession.getUserId(), communityId);
		if (communityMark != null) {
			return new BaseDTO(RequestResultEnum.SUCCESS, Constants.YES);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, Constants.NO);
	}

	/**
	 * 查询收藏的房源列表
	 *
	 * @param pageNum
	 * @param userKey
	 * @return
	 * @throws Exception
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryMarkedList/{pageNum}")
	public BaseDTO queryMarkedList(@PathVariable int pageNum, String userKey) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("查询收藏的社区失败：用户信息未找到或登录已失效! userKey={}", userKey);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("userId", userSession.getUserId());
		condition.put("isDeleted", Constants.NO);
		Page<CommunityMark> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> communityMarkService.queryCommunityMarkList(condition));
		for (CommunityMark communityMark : page) {
			Object communityIncache = EHCacheUtils.get("community", communityMark.getCommunityId() + "");
			if (communityIncache != null) {
				Community community = (Community) communityIncache;
				communityMark.setCommunity(community);
			} else {
				communityMark.setCommunity(communityService.readCommunity(communityMark.getCommunityId()));
			}
		}
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 立即支付
	 *
	 * @param orderDTO
	 * @return
	 * @throws Exception
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/pay")
	public BaseDTO pay(@RequestBody CommunityOrderDTO orderDTO) throws Exception {
		// 检查参数
		if (orderDTO == null || StringUtils.isEmpty(orderDTO.getUserKey()) || orderDTO.getCommunityId() <= 0 || orderDTO.getRoomId() <= 0 || orderDTO.getInDate() == null || StringUtils.isEmpty(orderDTO.getRealName()) || StringUtils.isEmpty(orderDTO.getPhone()) || StringUtils.isEmpty(orderDTO.getEmail()) || StringUtils.isEmpty(orderDTO.getPayType())) {
			logger.warn("请求支付失败，非空参数校验失败！orderDTO={}", GsonUtils.toSimpleJson(orderDTO));
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		if (!Constants.PAY_TYPE_WECHAT.equals(orderDTO.getPayType()) && !Constants.PAY_TYPE_ALIPAY.equals(orderDTO.getPayType())) {
			logger.warn("请求支付失败：支付方式错误！orderDTO={}", GsonUtils.toSimpleJson(orderDTO));
			return new BaseDTO(RequestResultEnum.WRONG_PAY_TYPE, null);
		}
		if (!Constants.ORDER_SOURCE_APP.equals(orderDTO.getOrderSource()) && !Constants.ORDER_SOURCE_PROGRAM.equals(orderDTO.getOrderSource())) {
			logger.warn("请求支付失败：订单来源错误！orderDTO={}", GsonUtils.toSimpleJson(orderDTO));
			return new BaseDTO(RequestResultEnum.ORDER_SOURCE_ERROR, null);
		}
		UserSession userSession = userSessionService.readUserSessionByUserKey(orderDTO.getUserKey());
		if (userSession == null) {
			logger.warn("请求支付失败：用户信息不存在或登录已失效！");
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		Community community = communityService.readCommunity(orderDTO.getCommunityId());
		if (community == null) {
			logger.warn("请求支付失败：community未找到！orderDTO={}", GsonUtils.toSimpleJson(orderDTO));
			return new BaseDTO(RequestResultEnum.COMMUNITY_NOT_FOUND, null);
		}
		Room room = roomService.readRoom(orderDTO.getRoomId());
		if (room == null) {
			logger.warn("请求支付失败：room未找到！orderDTO={}", GsonUtils.toSimpleJson(orderDTO));
			return new BaseDTO(RequestResultEnum.ROOM_NOT_FOUND, null);
		}
		Coupon coupon = null;
		if (orderDTO.getCouponId() > 0) {
			coupon = couponService.readCoupon(orderDTO.getCouponId());
			if (coupon == null) {
				logger.warn("请求支付失败：coupon未找到！orderDTO={}", GsonUtils.toSimpleJson(orderDTO));
				return new BaseDTO(RequestResultEnum.COUPON_NOT_FOUND, null);
			}
			if (coupon.getUserId() != userSession.getUserId()) {
				logger.warn("请求支付失败：优惠券用户不匹配,orderDTO={}, coupon={}", GsonUtils.toSimpleJson(orderDTO), GsonUtils.toSimpleJson(coupon));
				return new BaseDTO(RequestResultEnum.COUPON_NOT_MATCH, null);
			}
		}
		return communityOrderService.pay(orderDTO, userSession, community, room, coupon);
	}

	/**
	 * 获取用户订单列表
	 *
	 * @param pageNum
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/queryCommunityOrderList/{pageNum}")
	public BaseDTO queryCommunityOrderList(@PathVariable int pageNum, @RequestBody CommunityOrderQueryDTO queryDTO) throws Exception {
		UserSession userSession = userSessionService.readUserSessionByUserKey(queryDTO.getUserKey());
		if (userSession == null) {
			logger.warn("查询租房订单失败：用户信息未找到或登录已失效! queryDTO={}", GsonUtils.toSimpleJson(queryDTO));
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		Map<String, Object> condition = new HashMap<>();
		ConditionUtils.addLong(condition, "userId", userSession.getUserId());
		ConditionUtils.addStr(condition, "isDeleted", Constants.NO);
		ConditionUtils.addStr(condition, "status", queryDTO.getStatus());
		condition.put("sortColumn", "id");
		condition.put("sortType", "desc");
		Page<CommunityOrder> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> communityOrderService.queryCommunityOrderList(condition));
		for (CommunityOrder order : page) {
			Object communityIncache = EHCacheUtils.get("community", order.getCommunityId() + "");
			if (communityIncache != null) {
				Community community = (Community) communityIncache;
				order.setCommunity(community);
			} else {
				order.setCommunity(communityService.readCommunity(order.getCommunityId()));
			}
		}
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 根据id读取订单详情
	 *
	 * @param orderId
	 * @return
	 * @throws Exception
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readCommunityOrderById/{orderId}")
	public BaseDTO readCommunityOrderById(@PathVariable long orderId) throws Exception {
		CommunityOrder order = communityOrderService.readCommunityOrder(orderId);
		if (order == null || Constants.YES.equals(order.getIsDeleted())) {
			order = null;
		}
		if (order != null) {
			Object communityIncache = EHCacheUtils.get("community", order.getCommunityId() + "");
			if (communityIncache != null) {
				Community community = (Community) communityIncache;
				order.setCommunity(community);
			} else {
				order.setCommunity(communityService.readCommunity(order.getCommunityId()));
			}
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, order);
	}

	/**
	 * 根据编码读取订单详情
	 *
	 * @param orderCode
	 * @return
	 * @throws Exception
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readCommunityOrderByCode/{orderCode}")
	public BaseDTO readCommunityOrderByCode(@PathVariable String orderCode) throws Exception {
		CommunityOrder order = communityOrderService.readCommunityOrderByCode(orderCode);
		if (order != null) {
			Object communityIncache = EHCacheUtils.get("community", order.getCommunityId() + "");
			if (communityIncache != null) {
				Community community = (Community) communityIncache;
				order.setCommunity(community);
			} else {
				order.setCommunity(communityService.readCommunity(order.getCommunityId()));
			}
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, order);
	}
}
