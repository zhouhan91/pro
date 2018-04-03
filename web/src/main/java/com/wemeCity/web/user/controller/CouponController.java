package com.wemeCity.web.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.CouponService;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

	@Autowired
	private CouponService couponService;

	@Autowired
	private UserSessionService userSessionService;

	@ResponseBody
	@GetMapping("/queryUsableCoupons")
	public BaseDTO queryUsableCoupons(String userKey, String busiCode) throws Exception {
		if (StringUtils.isEmpty(userKey) || StringUtils.isEmpty(busiCode)) {
			logger.warn("获取优惠券失败，非空参数校验失败！userKey={}, busiCode={}", userKey, busiCode);
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		// 获取userSession
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("获取优惠券失败，用户信息未找到或者登录已失效！userKey={}", userKey);
			return new BaseDTO(RequestResultEnum.SESSION_TIME_OUT, null);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, couponService.queryUsableCoupons(userSession.getUserId(), busiCode));
	}
}
