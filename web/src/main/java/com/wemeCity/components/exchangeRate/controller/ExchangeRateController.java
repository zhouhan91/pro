package com.wemeCity.components.exchangeRate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.components.exchangeRate.service.ExchangeRateService;

@Controller
@RequestMapping("/components/exchangeRate")
public class ExchangeRateController extends BaseController {

	@Autowired
	private ExchangeRateService exchangeRateService;

	@ResponseBody
	@GetMapping("/getExchangeRate")
	public BaseDTO getExchangeRate() {
		try {
			return exchangeRateService.getExchangeRate();
		} catch (Exception e) {
			logger.error("获取汇率出错：服务器内部错误！");
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
