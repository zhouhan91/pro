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
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.web.catering.dto.RestaurantLocationSaveDTO;
import com.wemeCity.web.catering.model.CateringRestaurantLocation;
import com.wemeCity.web.catering.service.CateringRestaurantLocationService;

@Controller
@RequestMapping("/catering/location")
public class CateringRestaurantLocationController extends BaseController {

	@Autowired
	private CateringRestaurantLocationService cateringRestaurantLocationService;

	/**
	 * 获取最多30条统一配送地址
	 *
	 * @param restaurantId
	 * @return
	 * @history 2017年12月25日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryRestaurantLocationList/{restaurantId}")
	public BaseDTO queryRestaurantLocationList(@PathVariable long restaurantId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("restaurantId", restaurantId);
		condition.put("isDeleted", Constants.NO);
		Page<CateringRestaurantLocation> page = PageHelper.startPage(1, 30).doSelectPage(() -> cateringRestaurantLocationService.queryCateringRestaurantLocationList(condition));
		return new PageDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@PostMapping("/saveRestaurantLocationList")
	public BaseDTO saveRestaurantLocationList(@RequestBody RestaurantLocationSaveDTO dto) {
		try {
			if (dto == null || dto.getManagerId() <= 0 || dto.getRestaurantId() <= 0 || CollectionUtils.isEmpty(dto.getLstLocation())) {
				logger.warn("保存店铺配送信息失败：非空参数校验失败！dto={}", GsonUtils.toSimpleJson(dto));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			return cateringRestaurantLocationService.saveRestaurantLocationList(dto);
		} catch (Exception e) {
			logger.error("保存店铺配送信息失败：服务器内部错误！dto={}", GsonUtils.toSimpleJson(dto), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
