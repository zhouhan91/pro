package com.wemeCity.web.catering.controller;

import java.time.LocalDateTime;
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
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.dto.CourierSaveDTO;
import com.wemeCity.web.catering.model.CateringCourier;
import com.wemeCity.web.catering.service.CateringCourierService;

@Controller
@RequestMapping("/catering/courier")
public class CateringCourierController extends BaseController {

	@Autowired
	private CateringCourierService cateringCourierService;

	@ResponseBody
	@GetMapping("/queryCateringCourierList/{restaurantId}/{pageNum}")
	public BaseDTO queryCateringCourierList(@PathVariable long restaurantId, @PathVariable int pageNum) {
		if (restaurantId <= 0) {
			logger.warn("获取店铺配送员信息失败：非空参数校验失败！restaurantId={}", restaurantId);
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("restaurantId", restaurantId);
		condition.put("isDeleted", Constants.NO);
		Page<CateringCourier> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> cateringCourierService.queryCateringCourierList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@GetMapping("/queryAllCateringCourierList/{restaurantId}")
	public BaseDTO queryAllCateringCourierList(@PathVariable long restaurantId) {
		if (restaurantId <= 0) {
			logger.warn("获取店铺配送员信息失败：非空参数校验失败！restaurantId={}", restaurantId);
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("restaurantId", restaurantId);
		condition.put("isDeleted", Constants.NO);
		return new BaseDTO(RequestResultEnum.SUCCESS, cateringCourierService.queryCateringCourierList(condition));
	}

	@ResponseBody
	@PostMapping("/saveCateringCourierList")
	public BaseDTO saveCateringCourierList(@RequestBody CourierSaveDTO dto) {
		try {
			if (dto == null || dto.getManagerId() <= 0 || dto.getRestaurantId() <= 0 || CollectionUtils.isEmpty(dto.getLstCourier())) {
				logger.warn("保存配送员信息失败：非空参数校验失败！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			return cateringCourierService.saveCateringCourierList(dto);
		} catch (Exception e) {
			logger.error("保存配送员信息失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/deleteCateringCourier/{id}/{managerId}")
	public BaseDTO deleteCateringCourier(@PathVariable long id, @PathVariable long managerId) {
		try {
			if (id <= 0 || managerId <= 0) {
				logger.warn("删除配送员信息失败：非空参数校验失败！id={}, managerId={}", id, managerId);
			}
			Map<String, Object> condition = new HashMap<>(8);
			condition.put("id", id);
			condition.put("isDeleted", Constants.YES);
			condition.put("modifyBy", managerId);
			condition.put("modifyTime", LocalDateTime.now());
			cateringCourierService.updateCateringCourier(condition);
			return new BaseDTO(RequestResultEnum.SUCCESS, null);
		} catch (Exception e) {
			logger.error("删除配送员信息失败：服务器内部错误！id={}, managerId={}", id, managerId, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
