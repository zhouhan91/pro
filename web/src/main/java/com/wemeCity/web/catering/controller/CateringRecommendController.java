package com.wemeCity.web.catering.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.web.catering.model.CateringRecommend;
import com.wemeCity.web.catering.service.CateringRecommendService;

@Controller
@RequestMapping("/catering/recommend")
public class CateringRecommendController extends BaseController {

	@Autowired
	private CateringRecommendService cateringRecommendService;

	@ResponseBody
	@GetMapping("/queryCateringRecommendList/{cityName}")
	public BaseDTO queryCateringRecommendList(@PathVariable String cityName) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("cityName", cityName);
		condition.put("isDeleted", Constants.NO);
		condition.put("status", Constants.YES);
		ConditionUtils.addStr(condition, "cityNameLike", cityName);
		Page<CateringRecommend> page = PageHelper.startPage(1, 10).doSelectPage(() -> cateringRecommendService.queryCateringRecommendList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
}
