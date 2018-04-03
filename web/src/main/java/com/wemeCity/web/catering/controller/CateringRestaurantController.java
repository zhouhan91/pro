package com.wemeCity.web.catering.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.dto.RestaurantQueryDTO;
import com.wemeCity.web.catering.dto.RestaurantUpdateDTO;
import com.wemeCity.web.catering.model.CateringGoods;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringCommentService;
import com.wemeCity.web.catering.service.CateringDiscountService;
import com.wemeCity.web.catering.service.CateringGoodsService;
import com.wemeCity.web.catering.service.CateringOrderService;
import com.wemeCity.web.catering.service.CateringRestaurantService;
import com.wemeCity.web.catering.utils.CateringConstants;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/catering/restaurant")
public class CateringRestaurantController extends BaseController {

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@Autowired
	private CateringDiscountService cateringDiscountService;

	@Autowired
	private CateringGoodsService cateringGoodsService;

	@Autowired
	private CateringCommentService cateringCommentService;

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private CateringOrderService cateringOrderService;

	/**
	 * 获取首页推荐店铺
	 *
	 * @param cityName
	 * @return
	 * @throws Exception
	 * @history 2017年12月5日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryRecommendRestaurantList/{pageNum}")
	public BaseDTO queryRecommendRestaurantList(@PathVariable int pageNum, String cityName) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("isDeleted", Constants.NO);
		condition.put("status", CateringConstants.RESTAURANT_STATUS_AUDITED);
		condition.put("recommendFlag", Constants.YES);
//		ConditionUtils.addStr(condition, "cityNameLike", cityName);
		condition.put("sortColumn", "recommend_sort_num");
		condition.put("sortType", "asc");
		Page<CateringRestaurant> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringRestaurantService.queryCateringRestaurantList(condition));
		if (!CollectionUtils.isEmpty(page)) {
			condition.clear();
			condition.put("isDeleted", Constants.NO);
			condition.put("status", Constants.YES);
			for (CateringRestaurant restaurant : page) {
				condition.put("restaurantId", restaurant.getId());
				restaurant.setLstCateringDiscount(cateringDiscountService.queryCateringDiscountList(condition));
			}
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}

	/**
	 * 获取店铺列表
	 *
	 * @return
	 * @throws Exception
	 * @history 2017年12月5日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/queryCateringRestaurantList/{pageNum}")
	public BaseDTO queryCateringRestaurantList(@PathVariable int pageNum, @RequestBody RestaurantQueryDTO queryDTO) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		ConditionUtils.addStr(condition, "parentCategoryCode", queryDTO.getParentCategoryCode());
		ConditionUtils.addStr(condition, "categoryCode", queryDTO.getCategoryCode());
		ConditionUtils.addStr(condition, "cityNameLike", queryDTO.getCityName());
		ConditionUtils.addStr(condition, "districtNameLike", queryDTO.getDistrictName());
		if (Constants.YES.equals(queryDTO.getLocationFlag())) {
			ConditionUtils.addFloat(condition, "myLongitude", queryDTO.getLongitude());
			ConditionUtils.addFloat(condition, "myLatitude", queryDTO.getLatitude());
			ConditionUtils.addStr(condition, "sortColumn", "interest_level");
			ConditionUtils.addStr(condition, "sortType", "desc");
		} else {
			ConditionUtils.addStr(condition, "sortColumn", "interest_level");
			ConditionUtils.addStr(condition, "sortType", "desc");
		}
		Page<CateringRestaurant> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringRestaurantService.queryCateringRestaurantList(condition));
		if (!CollectionUtils.isEmpty(page)) {
			condition.clear();
			condition.put("isDeleted", Constants.NO);
			condition.put("status", Constants.YES);
			for (CateringRestaurant restaurant : page) {
				condition.put("restaurantId", restaurant.getId());
				restaurant.setLstCateringDiscount(cateringDiscountService.queryCateringDiscountList(condition));
			}
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@GetMapping("/readCateringRestaurantInfo/{id}")
	public BaseDTO readCateringRestaurantInfo(@PathVariable long id) throws Exception {
		// 读取主数据
		CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(id);
		if (restaurant == null) {
			logger.warn("读取店铺信息失败：店铺不存在！");
			return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
		}
		Map<String, Object> condition = new HashMap<>();
		// 获取店铺推荐商品
		condition.put("restaurantId", restaurant.getId());
		condition.put("recommendFlag", Constants.YES);
		condition.put("status", Constants.YES);
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "recommend_sort_num");
		condition.put("sortType", "asc");
		Page<CateringGoods> goodsPage = PageHelper.startPage(1, 20).doSelectPage(() -> cateringGoodsService.queryCateringGoodsList(condition));
		restaurant.setLstCateringGoods(goodsPage);
		// 获取用户最新评论
		restaurant.setLstComment(cateringCommentService.queryRestaurantCommentInfoList(restaurant.getId(), 1));
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@GetMapping("/readCateringRestaurant/{id}")
	public BaseDTO readCateringRestaurant(@PathVariable long id) throws Exception {
		return new BaseDTO(RequestResultEnum.SUCCESS, cateringRestaurantService.readCateringRestaurant(id));
	}

	@ResponseBody
	@PostMapping("/updateRestaurantInfo")
	public BaseDTO updateRestaurantInfo(@RequestBody RestaurantUpdateDTO updateDTO) {
		try {
			if (updateDTO == null || updateDTO.getId() <= 0 || updateDTO.getManagerId() <= 0) {
				logger.warn("修改店铺信息失败：非空参数校验失败！updateDTO={}", GsonUtils.toSimpleJson(updateDTO));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			// 读取信息
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(updateDTO.getId());
			if (restaurant == null) {
				logger.warn("修改店铺信息失败：店铺不存在！updateDTO={}", GsonUtils.toSimpleJson(updateDTO));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			if (restaurant.getManagerId() != updateDTO.getManagerId()) {
				logger.warn("修改店铺信息失败：用户不匹配，不允许修改！updateDTO={}", GsonUtils.toSimpleJson(updateDTO));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH,  null);
			}
			return cateringRestaurantService.updateRestaurantInfo(updateDTO);
		} catch (Exception e) {
			logger.error("修改店铺信息失败：服务器内部错误！updateDTO={}", GsonUtils.toSimpleJson(updateDTO), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/checkNewMember/{userKey}/{restaurantId}")
	public BaseDTO checkNewMember(@PathVariable String userKey, @PathVariable long restaurantId) {
		try {
			UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
			if (userSession == null) {
				logger.warn("检查是否新会员失败：用户不存在或登录已失效！userKey={}", userKey);
				return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, cateringOrderService.checkNewMember(userSession.getUserId(), restaurantId));
		} catch (Exception e) {
			logger.warn("检查是否新会员失败：服务器内部错误！userKey={}, restaurantId={}", userKey, restaurantId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
