package com.wemeCity.web.catering.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.wemeCity.web.catering.service.CateringOrderDetailService;
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
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.BigDecimalUtils;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.catering.model.CateringGoods;
import com.wemeCity.web.catering.model.CateringGoodsCategory;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringGoodsCategoryService;
import com.wemeCity.web.catering.service.CateringGoodsService;
import com.wemeCity.web.catering.service.CateringRestaurantService;

@Controller
@RequestMapping("/catering/goods")
public class CateringGoodsController extends BaseController {

	@Autowired
	private CateringGoodsService cateringGoodsService;

	@Autowired
	private CateringGoodsCategoryService cateringGoodsCategoryService;

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@Autowired
	private CateringOrderDetailService cateringOrderDetailService;

	/**
	 * 根据商品分类获取商品信息，每页显示30行
	 *
	 * @param pageNum
	 * @return
	 * @history 2017年12月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryGoodsList/{goodsCategoryId}/{status}/{pageNum}")
	public BaseDTO queryGoodsList(@PathVariable long goodsCategoryId, @PathVariable String status, @PathVariable int pageNum) {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("categoryId", goodsCategoryId);
			if (!"all".equals(status)) {
				condition.put("status", status);
			}
			condition.put("isDeleted", Constants.NO);
			condition.put("sortColumn", "sort_num");
			condition.put("sortType", "asc");
			Page<CateringGoods> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringGoodsService.queryCateringGoodsList(condition));
			for(CateringGoods goods: page.getResult()){
				Map<String, Object> dateSegment = new HashMap<>();
				dateSegment.put("goodsId",goods.getId());
				dateSegment.put("dateStart", ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime().minusMonths(1).toString());
				dateSegment.put("dateEnd",ZonedDateTime.now(ZoneId.of("Europe/Paris")).toLocalDateTime().toString());
				goods.setCurrentMonthSalesVolume((int) cateringOrderDetailService.queryCateringOrderDetailCount(dateSegment));
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("获取商品列表失败：服务器内部错误！goodsCategoryId={}", goodsCategoryId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 读取商品详情
	 *
	 * @param id
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readGoodsInfo/{id}")
	public BaseDTO readGoodsInfo(@PathVariable long id) {
		try {
			CateringGoods goods = cateringGoodsService.readCateringGoods(id);
			if (goods == null) {
				logger.warn("读取商品信息失败：商品不存在！id={}", id);
				return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, goods);
		} catch (Exception e) {
			logger.error("读取商品信息失败：服务器内部错误！id={}", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 新增商品
	 *
	 * @param goods
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/insertGoods")
	public BaseDTO insertGoods(@RequestBody CateringGoods goods) {
		try {
			if (goods == null || goods.getRestaurantId() <= 0 || goods.getCategoryId() <= 0 || StringUtils.isEmpty(goods.getName()) || BigDecimalUtils.compareTo(goods.getPrice(), BigDecimal.ZERO) <= 0 || BigDecimalUtils.compareTo(goods.getDiscountPrice(), BigDecimal.ZERO) < 0 || StringUtils.isEmpty(goods.getCoverPicture()) || StringUtils.isEmpty(goods.getRecommendFlag()) || goods.getStock() < 0 || goods.getCreateBy() <= 0) {
				logger.warn("新增商品失败：非空参数校验失败！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			// 校验店铺
			CateringRestaurant restaurant = cateringRestaurantService.readCateringRestaurant(goods.getRestaurantId());
			if (restaurant == null || Constants.YES.equals(restaurant.getIsDeleted())) {
				logger.warn("新增商品失败：店铺不存在！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			if (restaurant.getManagerId() != goods.getCreateBy()) {
				logger.warn("新增商品失败：身份验证失败！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			// 校验商品分类
			CateringGoodsCategory category = cateringGoodsCategoryService.readCateringGoodsCategory(goods.getCategoryId());
			if (category == null || Constants.YES.equals(category.getIsDeleted())) {
				logger.warn("新增商品失败：商品分类不存在！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.GOODS_CATEGORY_NOT_FOUND, null);
			}
			if (category.getRestaurantId() != goods.getRestaurantId()) {
				logger.warn("新增商品失败：商品分类不属于该店铺！goods={}, category={}", GsonUtils.toSimpleJson(goods), GsonUtils.toSimpleJson(category));
				return new BaseDTO(RequestResultEnum.GOODS_CATEGORY_NOT_MATCH, null);
			}
			goods.setStatus(Constants.NO);
			goods.setIsDeleted(Constants.NO);
			goods.setCreateTime(LocalDateTime.now());
			cateringGoodsService.insertCateringGoods(goods);
			return new BaseDTO(RequestResultEnum.SUCCESS, goods);
		} catch (Exception e) {
			logger.error("新增商品失败：服务器内部错误！goods={}", GsonUtils.toSimpleJson(goods), e);
			return new BaseDTO(RequestResultEnum.SUCCESS, goods);
		}
	}

	/**
	 * 删除商品
	 *
	 * @param goodsId 商品id
	 * @param managerId 当前登录用户id
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/deleteGoods/{goodsId}/{managerId}")
	public BaseDTO deleteGoods(@PathVariable long goodsId, @PathVariable long managerId) {
		try {
			if (goodsId <= 0 || managerId <= 0) {
				logger.warn("删除商品失败：非空参数校验失败！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringGoods goods = cateringGoodsService.readCateringGoods(goodsId);
			if (goods == null || Constants.YES.equals(goods)) {
				logger.warn("删除商品失败：商品不存在！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
			}
			if (goods.getCreateBy() != managerId) {
				logger.warn("删除商品失败：用户不匹配！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", goodsId);
			condition.put("isDeleted", Constants.YES);
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", LocalDateTime.now());
			cateringGoodsService.updateCateringGoods(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("删除商品失败：服务器内部错误！goodsId={}, managerId={}", goodsId, managerId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 修改商品信息
	 *
	 * @param goods 修改的商品信息
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/updateGoodsInfo")
	public BaseDTO updateGoodsInfo(@RequestBody CateringGoods goods) {
		try {
			if (goods == null || goods.getId() <= 0 || goods.getModifyBy() <= 0) {
				logger.warn("修改商品信息失败：非空参数校验失败！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringGoods old = cateringGoodsService.readCateringGoods(goods.getId());
			if (old == null || Constants.YES.equals(old.getIsDeleted())) {
				logger.warn("修改商品信息失败：商品不存在！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
			}
			if (old.getCreateBy() != goods.getModifyBy()) {
				logger.warn("修改商品信息失败：用户不匹配！goods={}", GsonUtils.toSimpleJson(goods));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(20);
			ConditionUtils.addLong(condition, "id", goods.getId());
			ConditionUtils.addStr(condition, "name", goods.getName());
			ConditionUtils.addDecimal(condition, "price", goods.getPrice());
			ConditionUtils.addDecimal(condition, "discountPrice", goods.getDiscountPrice());
			ConditionUtils.addStr(condition, "coverPicture", goods.getCoverPicture());
			ConditionUtils.addStr(condition, "recommendFlag", goods.getRecommendFlag());
			ConditionUtils.addInteger(condition, "recommendSortNum", goods.getRecommendSortNum());
			ConditionUtils.addObject(condition, "stock", goods.getStock());
			ConditionUtils.addObject(condition, "modifyTime", LocalDateTime.now());
			cateringGoodsService.updateCateringGoods(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("修改商品信息失败：服务器内部错误！goods={}", GsonUtils.toSimpleJson(goods), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 上架商品
	 *
	 * @param goodsId 商品id
	 * @param managerId 当前登录用户id
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/putOnLine/{goodsId}/{managerId}")
	public BaseDTO putOnLine(@PathVariable long goodsId, @PathVariable long managerId) {
		try {
			if (goodsId <= 0 || managerId <= 0) {
				logger.warn("上架商品失败：非空参数校验失败！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringGoods goods = cateringGoodsService.readCateringGoods(goodsId);
			if (goods == null || Constants.YES.equals(goods.getIsDeleted())) {
				logger.warn("上架商品失败：商品不存在！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
			}
			if (goods.getCreateBy() != managerId) {
				logger.warn("上架商品失败：用户不匹配！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", goodsId);
			condition.put("status", Constants.YES);
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", LocalDateTime.now());
			cateringGoodsService.updateCateringGoods(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("上架商品失败：服务器内部错误！goodsId={}, managerId={}", goodsId, managerId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 下架商品
	 *
	 * @param goodsId 商品id
	 * @param managerId 当前登录用户id
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/putOffLine/{goodsId}/{managerId}")
	public BaseDTO putOffLine(@PathVariable long goodsId, @PathVariable long managerId) {
		try {
			if (goodsId <= 0 || managerId <= 0) {
				logger.warn("下架商品失败：非空参数校验失败！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringGoods goods = cateringGoodsService.readCateringGoods(goodsId);
			if (goods == null || Constants.YES.equals(goods.getIsDeleted())) {
				logger.warn("下架商品失败：商品不存在！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
			}
			if (goods.getCreateBy() != managerId) {
				logger.warn("下架商品失败：用户不匹配！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", goodsId);
			condition.put("status", Constants.NO);
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", LocalDateTime.now());
			cateringGoodsService.updateCateringGoods(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("下架商品失败：服务器内部错误！goodsId={}, managerId={}", goodsId, managerId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 估清
	 *
	 * @param goodsId 商品id
	 * @param managerId 当前登录用户id
	 * @return
	 * @history 2017年12月23日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/clearStock/{goodsId}/{managerId}")
	public BaseDTO clearStock(@PathVariable long goodsId, @PathVariable long managerId) {
		try {
			if (goodsId <= 0 || managerId <= 0) {
				logger.warn("估清商品失败：非空参数校验失败！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringGoods goods = cateringGoodsService.readCateringGoods(goodsId);
			if (goods == null || Constants.YES.equals(goods.getIsDeleted())) {
				logger.warn("估清商品失败：商品不存在！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.GOODS_NOT_FOUND, null);
			}
			if (goods.getCreateBy() != managerId) {
				logger.warn("估清商品失败：用户不匹配！goodsId={}, managerId={}", goodsId, managerId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("id", goodsId);
			condition.put("stock", 0);
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", LocalDateTime.now());
			cateringGoodsService.updateCateringGoods(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("估清商品失败：服务器内部错误！goodsId={}, managerId={}", goodsId, managerId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
