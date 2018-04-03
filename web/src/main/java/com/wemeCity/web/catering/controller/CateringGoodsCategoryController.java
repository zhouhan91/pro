package com.wemeCity.web.catering.controller;

import java.time.LocalDateTime;
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
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.catering.model.CateringGoodsCategory;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringGoodsCategoryService;
import com.wemeCity.web.catering.service.CateringRestaurantService;

@Controller
@RequestMapping("/catering/goodsCategory")
public class CateringGoodsCategoryController extends BaseController {

	@Autowired
	private CateringGoodsCategoryService cateringGoodsCategoryService;

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	/**
	 * 获取店铺商品分类信息，最多返回30条分类
	 *
	 * @param restaurantId
	 * @return
	 * @history 2017年12月8日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryGoodsCategoryList/{restaurantId}")
	public BaseDTO queryGoodsCategoryList(@PathVariable long restaurantId) {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("restaurantId", restaurantId);
			// 目前只有一级
			condition.put("parentId", 0);
			condition.put("status", Constants.YES);
			condition.put("isDeleted", Constants.NO);
			Page<CateringGoodsCategory> page = PageHelper.startPage(1, 30).doSelectPage(() -> cateringGoodsCategoryService.queryCateringGoodsCategoryList(condition));
			return new BaseDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("查询店铺商品分类失败：服务器内部错误！restaurantId={}", restaurantId);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/insertGoodsCategory")
	public BaseDTO insertGoodsCategory(@RequestBody CateringGoodsCategory category) {
		try {
			if (category == null || StringUtils.isEmpty(category.getName()) || category.getRestaurantId() <= 0 || category.getSortNum() <= 0 || category.getCreateBy() <= 0) {
				logger.warn("新增商品分类失败：非空参数校验失败！category={}", GsonUtils.toSimpleJson(category));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringRestaurant cateringRestaurant = cateringRestaurantService.readCateringRestaurant(category.getRestaurantId());
			if (cateringRestaurant == null || Constants.YES.equals(cateringRestaurant.getIsDeleted())) {
				logger.warn("新增商品分类失败：店铺不存在！category={}", GsonUtils.toSimpleJson(category));
				return new BaseDTO(RequestResultEnum.RESTAURANT_NOT_FOUND, null);
			}
			if (cateringRestaurant.getManagerId() != category.getCreateBy()) {
				logger.warn("新增商品分类失败：用户不匹配！category={}", GsonUtils.toSimpleJson(category));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			category.setStatus(Constants.YES);
			category.setIsDeleted(Constants.NO);
			category.setCreateTime(LocalDateTime.now());
			cateringGoodsCategoryService.insertCateringGoodsCategory(category);
			return new BaseDTO(RequestResultEnum.SUCCESS, category);
		} catch (Exception e) {
			logger.error("新增商品分类失败：服务器内部错误！category={}", GsonUtils.toSimpleJson(category), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/deleteGoodsCategory/{goodsCategoryId}/{managerId}")
	public BaseDTO deleteGoodsCategory(@PathVariable long goodsCategoryId, @PathVariable long managerId) {
		try {
			CateringGoodsCategory category = cateringGoodsCategoryService.readCateringGoodsCategory(goodsCategoryId);
			if (category == null || Constants.YES.equals(category.getIsDeleted())) {
				logger.warn("删除商品分类失败：分类不存在！goodsCategoryId={}, managerId={}", goodsCategoryId, managerId);
				return new BaseDTO(RequestResultEnum.GOODS_CATEGORY_NOT_FOUND, null);
			}
			if (category.getCreateBy() != managerId) {
				logger.warn("删除商品分类失败：用户不匹配！goodsCategoryId={}, managerId={}", goodsCategoryId, managerId);
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>();
			condition.put("id", goodsCategoryId);
			condition.put("isDeleted", Constants.YES);
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", LocalDateTime.now());
			cateringGoodsCategoryService.updateCateringGoodsCategory(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("删除商品分类失败：服务器内部错误！goodsCategoryId={}, managerId={}", goodsCategoryId, managerId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/updateGoodsCategory")
	public BaseDTO updateGoodsCategory(@RequestBody CateringGoodsCategory category) {
		try {
			if (category == null || category.getId() <= 0 || category.getModifyBy() <= 0) {
				logger.warn("修改商品分类失败：非空参数校验失败！category={}", GsonUtils.toSimpleJson(category));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			CateringGoodsCategory oldCategory = cateringGoodsCategoryService.readCateringGoodsCategory(category.getId());
			if (oldCategory == null || Constants.YES.equals(oldCategory.getIsDeleted())) {
				logger.warn("修改商品分类失败：分类不存在！category={}", GsonUtils.toSimpleJson(category));
				return new BaseDTO(RequestResultEnum.GOODS_CATEGORY_NOT_FOUND, null);
			}
			if (oldCategory.getCreateBy() != category.getModifyBy()) {
				logger.warn("修改商品分类失败：用户不匹配！category={}, oldCategory={}", GsonUtils.toSimpleJson(category), GsonUtils.toSimpleJson(oldCategory));
				return new BaseDTO(RequestResultEnum.RESTAURANT_MANAGER_NOT_MATCH, null);
			}
			Map<String, Object> condition = new HashMap<>(10);
			ConditionUtils.addLong(condition, "id", category.getId());
			ConditionUtils.addLong(condition, "modifyBy", category.getModifyBy());
			ConditionUtils.addStr(condition, "name", category.getName());
			ConditionUtils.addInteger(condition, "sortNum", category.getSortNum());
			ConditionUtils.addObject(condition, "modifyTime", LocalDateTime.now());
			cateringGoodsCategoryService.updateCateringGoodsCategory(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("修改商品分类失败：服务器内部错误！category={}", GsonUtils.toSimpleJson(category), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
	
	@ResponseBody
	@GetMapping("/readGoodsCategoryInfo/{id}")
	public BaseDTO readGoodsCategoryInfo(@PathVariable long id){
		try {
			return new BaseDTO(RequestResultEnum.SUCCESS, cateringGoodsCategoryService.readCateringGoodsCategory(id));
        } catch (Exception e) {
        	logger.error("读取商品分类失败：服务器内部错误！id={}", id, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
        }
	}
}
