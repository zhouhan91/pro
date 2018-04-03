package com.wemeCity.web.catering.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.catering.model.CateringManager;
import com.wemeCity.web.catering.model.CateringRestaurant;
import com.wemeCity.web.catering.service.CateringManagerService;
import com.wemeCity.web.catering.service.CateringRestaurantService;

@Controller
@RequestMapping("/catering/manager")
public class CateringManagerController extends BaseController {

	@Autowired
	private CateringManagerService cateringManagerService;

	@Autowired
	private CateringRestaurantService cateringRestaurantService;

	@ResponseBody
	@PostMapping("/register")
	public BaseDTO register(@RequestBody CateringManager manager) {
		try {
			if (manager == null || StringUtils.isEmpty(manager.getRealName()) || StringUtils.isEmpty(manager.getUserName()) || StringUtils.isEmpty(manager.getPassword()) || StringUtils.isEmpty(manager.getPhone()) || StringUtils.isEmpty(manager.getCountryCode()) || StringUtils.isEmpty(manager.getCountryName()) || StringUtils.isEmpty(manager.getCityCode()) || StringUtils.isEmpty(manager.getCityName())  || StringUtils.isEmpty(manager.getAddress())) {
				logger.warn("注册失败：非空参数校验失败！manager={}", GsonUtils.toSimpleJson(manager));
				return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
			}
			// 检查密码长度
			if (manager.getPassword().length() < 6) {
				logger.warn("注册失败：密码长度小于6位！manager={}", GsonUtils.toSimpleJson(manager));
				return new BaseDTO(RequestResultEnum.PASSWORD_TOO_SHORT, null);
			}
			// 校验用户名
			Map<String, Object> condition = new HashMap<>(5);
			condition.put("userName", manager.getUserName());
			long count = cateringManagerService.queryCateringManagerCount(condition);
			if (count > 0) {
				logger.warn("注册失败：用户名已存在！manager={}", GsonUtils.toSimpleJson(manager));
				return new BaseDTO(RequestResultEnum.USER_NAME_REPEAT, null);
			}
			return cateringManagerService.register(manager);
		} catch (Exception e) {
			logger.error("注册失败：服务器内部错误！manager={}", GsonUtils.toSimpleJson(manager), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@PostMapping("/login")
	public BaseDTO login(@RequestBody LoginDTO loginDTO) {
		try {
			CateringManager manager = cateringManagerService.readManagerByUserName(loginDTO.getUserName());
			if (manager == null) {
				logger.warn("登录失败：用户不存在！loginDTO={}", GsonUtils.toSimpleJson(loginDTO));
				return new BaseDTO(RequestResultEnum.CATERING_MANAGER_NOT_FOUND, null);
			}
			if (!manager.getPassword().equals(StringUtils.md5(loginDTO.getPassword()))) {
				logger.warn("登录失败：用户名或密码错误！loginDTO={}", GsonUtils.toSimpleJson(loginDTO));
				return new BaseDTO(RequestResultEnum.WRONG_CATERING_MANAGER_PASSWORD, null);
			}
			manager.setPassword("");
			CateringRestaurant restaurant = cateringRestaurantService.readRestaurantByManagerId(manager.getId());
			restaurant.setManager(manager);
			return new BaseDTO(RequestResultEnum.SUCCESS, restaurant);
		} catch (Exception e) {
			logger.error("登录失败：服务器内部错误！loginDTO={}", GsonUtils.toSimpleJson(loginDTO), e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}

class LoginDTO {
	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
