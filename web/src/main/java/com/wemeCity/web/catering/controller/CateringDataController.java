package com.wemeCity.web.catering.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.web.catering.dto.CateringAllData;
import com.wemeCity.web.catering.dto.CateringGoodsSalesVolume;
import com.wemeCity.web.catering.dto.CateringMonthData;
import com.wemeCity.web.catering.service.CateringOrderDetailService;
import com.wemeCity.web.catering.service.CateringOrderService;

@Controller
@RequestMapping("/catering/data")
public class CateringDataController extends BaseController {

	@Autowired
	private CateringOrderService cateringOrderService;

	@Autowired
	private CateringOrderDetailService cateringOrderDetailService;

	@ResponseBody
	@GetMapping("/queryOperationAnalysis/{restaurantId}")
	public BaseDTO queryOperationAnalysis(@PathVariable long restaurantId) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("restaurantId", restaurantId);
		CateringAllData allData = cateringOrderService.queryAllData(condition);
		CateringMonthData monthData = cateringOrderService.queryMonthData(condition);

		LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		LocalDateTime timeStart = LocalDateTime.of(firstDayOfMonth, LocalTime.of(0, 0, 0));
		LocalDateTime timeEnd = LocalDateTime.of(lastDayOfMonth, LocalTime.of(23, 59, 59));
		condition.put("timeStart", timeStart);
		condition.put("timeEnd", timeEnd);
		List<CateringGoodsSalesVolume> lstSalesVolume = cateringOrderDetailService.queryCateringGoodsSalesVolume(condition);
		Map<String, Object> result = new HashMap<>(5);
		result.put("allData", allData);
		result.put("monthData", monthData);
		result.put("lstSalesVolume", lstSalesVolume);
		return new BaseDTO(RequestResultEnum.SUCCESS, result);
	}
}
