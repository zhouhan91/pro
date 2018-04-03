package com.wemeCity.components.sms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.dto.PageDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.ConditionUtils;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.components.sms.dto.SmsDTO;
import com.wemeCity.components.sms.model.Sms;
import com.wemeCity.components.sms.model.SmsBusi;
import com.wemeCity.components.sms.service.SmsService;
import com.wemeCity.components.sms.utils.SmsBusiUtils;

@Controller
@RequestMapping("/sms")
public class SmsController {

	private Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	private SmsService smsService;

	@ResponseBody
	@PostMapping("/sendValidateCodeSMS")
	public BaseDTO sendValidateCodeSMS(@RequestBody SmsDTO smsDTO, HttpSession session) throws Exception {
		BaseDTO result = null;
		logger.debug("sendValidateCodeSMS params:smsDTO={}", GsonUtils.toSimpleJson(smsDTO));
		if (smsDTO == null || StringUtils.isEmpty(smsDTO.getBusiCode()) || StringUtils.isEmpty(smsDTO.getPhoneNumber()) || StringUtils.isEmpty(smsDTO.getImageCode())) {
			result = new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR.getKey(), RequestResultEnum.NOT_NULL_PARAM_ERROR.getDescription(), smsDTO);
			logger.warn("sendValidateCodeSMS params not-null validate fail:smsDTO={}", GsonUtils.toSimpleJson(result));
			return result;
		}
		// busiCode 验证
		SmsBusi smsBusi = SmsBusiUtils.get(smsDTO.getBusiCode());
		if (smsBusi == null) {
			result = new BaseDTO(RequestResultEnum.WRONG_SMS_BUSI_CODE.getKey(), RequestResultEnum.WRONG_SMS_BUSI_CODE.getDescription(), null);
			logger.info("send validate code SMS fail:wrong sms busiCode,busiCode={}");
			return result;
		}
		// 验证图片验证码
		Object cacheValue = EHCacheUtils.get(smsBusi.getSecurityCodeCache(), session.getId());
		if (!smsDTO.getImageCode().equalsIgnoreCase(cacheValue.toString())) {
			result = new BaseDTO(RequestResultEnum.WRONG_SMS_IMAGE_CODE.getKey(), RequestResultEnum.WRONG_SMS_IMAGE_CODE.getDescription(), null);
			logger.info("send validate code SMS fail:wrong image code, imageCode={},cacheValue={}", smsDTO.getImageCode(), cacheValue);
			return result;
		}
		String randomNumber = StringUtils.generateNumberRandomStr(4);
		EHCacheUtils.put(smsBusi.getSmsCache(), smsDTO.getPhoneNumber(), randomNumber);
		// 发送短信
		String content = "验证码" + randomNumber + "，请确保是本人操作且为本人手机，否则请忽略此短信。";
		smsService.sendSms(smsBusi.getBusiCode(), content, smsDTO.getPhoneNumber());
		result = new BaseDTO(RequestResultEnum.SUCCESS.getKey(), "发送成功！", null);
		logger.debug("sendValidateCodeSMS result:result={}, smsCode={}", GsonUtils.toSimpleJson(result), randomNumber);
		return result;
	}

	@GetMapping("/list")
	public String list() throws Exception {
		return "admin/smsList";
	}

	@ResponseBody
	@PostMapping("/load/{pageNum}")
	public BaseDTO load(@RequestBody Sms sms, @PathVariable int pageNum) throws Exception {
		logger.debug("sms load params: sms={}, pageNum={}", GsonUtils.toSimpleJson(sms), pageNum);
		BaseDTO result = null;
		if (pageNum <= 0) {
			result = new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR, pageNum);
			logger.warn("sms load fail: sms={}, result={}", GsonUtils.toSimpleJson(sms), pageNum);
			return result;
		}
		Map<String, Object> condition = new HashMap<>(3);
		condition.put("isDeleted", Constants.NO);
		ConditionUtils.addStr(condition, "receiverLike", sms.getReciver());
		Page<Sms> page = PageHelper.startPage(pageNum, Constants.DEFAULT_PAGE_SIZE).doSelectPage(() -> smsService.querySmsList(condition));
		result = new PageDTO(RequestResultEnum.SUCCESS, page);
		logger.debug("sms load success: result={}", GsonUtils.toSimpleJson(result));
		return result;
	}
}
