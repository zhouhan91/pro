package com.wemeCity.web.user.controller;

import java.time.LocalDateTime;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.HttpsUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.web.user.dto.WechatLoginSuccessDTO;
import com.wemeCity.web.user.model.LoginRecord;
import com.wemeCity.web.user.model.User;
import com.wemeCity.web.user.model.UserSession;
import com.wemeCity.web.user.service.LoginRecordService;
import com.wemeCity.web.user.service.UserService;
import com.wemeCity.web.user.service.UserSessionService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private LoginRecordService loginRecordService;

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("yamlproperties")
	private Properties properties;

	@ResponseBody
	@PostMapping("/login")
	public BaseDTO login(String jsCode) throws Exception {
		if (StringUtils.isEmpty(jsCode)) {
			logger.warn("jsCode is null!");
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		// 向微信获取相关信息
		WechatLoginSuccessDTO loginDTO = getSessionKeyFromWechat(jsCode);
		if (loginDTO == null) {
			logger.warn("登录失败，根据jsCode从微信获取用户信息失败！jsCode={}", jsCode);
			return new BaseDTO(RequestResultEnum.GET_SESSION_KEY_FAIL, null);
		}
		// 登录动作
		return userService.login(loginDTO);
	}

	/**
	 * 根据jsCode从微信获取用户信息
	 *
	 * @param jsCode 前端小程序调用wx.login获取的jsCode
	 * @return
	 * @throws Exception
	 * @history 2017年9月28日 新建
	 * @auther xiaowen
	 */
	private WechatLoginSuccessDTO getSessionKeyFromWechat(String jsCode) throws Exception {
		String url = properties.getProperty("system.wechat.sessionKeyUrl");
		String appId = properties.getProperty("system.wechat.programAppId");
		String secret = properties.getProperty("system.wechat.programSecret");
		url += "?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
		String result = HttpsUtils.post(url, null, null, null);
		if (!StringUtils.isEmpty(result) && result.contains("openid")) {
			Gson gson = GsonUtils.getGson();
			return gson.fromJson(result, WechatLoginSuccessDTO.class);
		}
		logger.warn("根据jsCode从微信获取用户信息失败, jsCode={}, result={}", jsCode, result);
		return null;
	}

	@ResponseBody
	@PostMapping("/checkSession")
	public BaseDTO checkSession(String userKey) throws Exception {
		if (StringUtils.isEmpty(userKey)) {
			logger.warn("检查登录状态失败，非空参数!");
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		return userSessionService.checkSession(userKey);
	}

	@ResponseBody
	@PostMapping("/updateUserInfo")
	public BaseDTO updateUserInfo(@RequestBody User user) throws Exception {
		// 参数校验
		if (user == null || StringUtils.isEmpty(user.getUserKey())) {
			logger.warn("修改用户信息失败，参数不全！user={}", GsonUtils.toSimpleJson(user));
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, null);
		}
		// 检查登录是否有效
		BaseDTO result = userSessionService.checkSession(user.getUserKey());
		// 如果登录有效，则启动修改用户信息程序
		if (RequestResultEnum.SUCCESS.getKey().equals(result.getResultCode())) {
			return userService.updateUserInfo(user);
		}
		return result;
	}

	@ResponseBody
	@PostMapping("/reportUserInfo")
	public BaseDTO reportUserInfo(@RequestBody LoginRecord record) throws Exception {
		// 根据userkey获取用户信息
		UserSession userSession = userSessionService.readUserSessionByUserKey(record.getUserKey());
		if (userSession != null) {
			record.setUserId(userSession.getUserId());
			record.setUnionid(userSession.getUnionId());
		}
		record.setCreateTime(LocalDateTime.now());
		loginRecordService.insertLoginRecord(record);
		return new BaseDTO(RequestResultEnum.SUCCESS, null);
	}

	@ResponseBody
	@GetMapping("/readUserInfo/{userKey}")
	public BaseDTO readUserInfo(@PathVariable String userKey) throws Exception {
		// 根据userkey获取用户信息
		UserSession userSession = userSessionService.readUserSessionByUserKey(userKey);
		if (userSession == null) {
			logger.warn("读取用户信息失败：用户不存在或登录已失效！, userKey={}", userKey);
			return new BaseDTO(RequestResultEnum.USER_SESSION_NOT_FOUND, null);
		}
		return new BaseDTO(RequestResultEnum.SUCCESS, userService.readUser(userSession.getUserId()));
	}

}
