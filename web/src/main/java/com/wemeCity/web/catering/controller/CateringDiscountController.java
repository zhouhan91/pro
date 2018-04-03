package com.wemeCity.web.catering.controller;

import java.util.HashMap;
import java.util.List;
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
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.model.CateringDiscount;
import com.wemeCity.web.catering.service.CateringDiscountService;

@Controller
@RequestMapping("/catering/discount")
public class CateringDiscountController extends BaseController {

	@Autowired
	private CateringDiscountService cateringDiscountService;

	/**
	 * 根据店铺获取优惠列表信息
	 *
	 * @param restaurantId
	 * @return
	 * @history 2017年12月24日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryDiscountList/{restaurantId}")
	public BaseDTO queryDiscountList(@PathVariable long restaurantId) {
		try {
			Map<String, Object> condition = new HashMap<>();
			condition.put("restaurantId", restaurantId);
			condition.put("isDeleted", Constants.NO);
			Page<CateringDiscount> page = PageHelper.startPage(1, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringDiscountService.queryCateringDiscountList(condition));
			return new BaseDTO(RequestResultEnum.SUCCESS, page);
		} catch (Exception e) {
			logger.error("读取店铺优惠列表失败：服务器内部错误！restaurantId={}", restaurantId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	/**
	 * 修改店铺优惠
	 *
	 * @param lstCateringDiscount
	 * @return
	 * @history 2017年12月24日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@PostMapping("/updateDiscountInfo")
	public BaseDTO updateDiscountInfo(@RequestBody List<CateringDiscount> lstCateringDiscount) {
		try {
			if (CollectionUtils.isEmpty(lstCateringDiscount)) {
				logger.warn("修改店铺优惠失败：非空参数校验失败！lstCateringDiscount={}", GsonUtils.toSimpleJson(lstCateringDiscount));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			return cateringDiscountService.updateDiscountInfo(lstCateringDiscount);
		} catch (Exception e) {
			logger.error("修改店铺优惠失败：服务器内部错误！lstCateringDiscount={}", GsonUtils.toSimpleJson(lstCateringDiscount), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

}
