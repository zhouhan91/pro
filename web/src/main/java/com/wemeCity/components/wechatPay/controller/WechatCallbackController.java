package com.wemeCity.components.wechatPay.controller;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.components.wechatPay.model.WechatCallback;
import com.wemeCity.components.wechatPay.model.WechatConfig;
import com.wemeCity.components.wechatPay.service.WechatCallbackService;
import com.wemeCity.components.wechatPay.utils.WechatPayUtils;
import com.wemeCity.web.catering.service.CateringOrderService;
import com.wemeCity.web.community.model.CommunityOrder;
import com.wemeCity.web.community.service.CommunityOrderService;

@Controller
@RequestMapping("/payCallback")
public class WechatCallbackController extends BaseController {

	@Autowired
	private WechatConfig wechatConfig;

	@Autowired
	private CommunityOrderService communityOrderService;

	@Autowired
	private CateringOrderService cateringOrderService;

	@Autowired
	private WechatCallbackService wechatCallbackService;

	@ResponseBody
	@RequestMapping("/wechat")
	public String callback(HttpServletRequest request) throws Exception {
		logger.warn("微信回调进入！");
		try {
			// 获取回调结果xml
			String requestBody = this.getXmlFromRequest(request);
			logger.warn("微信回调进入！requestBody={}", requestBody);
			if (StringUtils.isEmpty(requestBody)) {
				return fail("参数为空");
			}
			// 将回调结果转换成TreeMap
			TreeMap<String, String> result = WechatPayUtils.xmlToTreeMap(requestBody);
			if (result == null || CollectionUtils.isEmpty(result)) {
				return fail("参数为空");
			}
			// 记录回调
			WechatCallback callback = new WechatCallback(result, requestBody);
			wechatCallbackService.insertWechatCallback(callback);
			// 核心参数非空验证
			if (!"SUCCESS".equals(callback.getReturnCode()) || !"SUCCESS".equals(callback.getResultCode())) {
				return fail("支付失败！");
			}
			// 验证签名
			String sign = WechatPayUtils.sign(result, wechatConfig.getPaySecret());
			if (StringUtils.isEmpty(sign) || !sign.equals(result.get("sign"))) {
				return fail("签名验证失败");
			}
			// 分发业务调用
			if (callback.getOutTradeNo().startsWith("co-")) {
				// 读取订单
				CommunityOrder order = communityOrderService.readCommunityOrderByCode(callback.getOrderCode());
				// 回调
				BaseDTO busiResult = communityOrderService.callback(order, callback);
				if (RequestResultEnum.SUCCESS.getKey().equals(busiResult.getResultCode())) {
					return success();
				}
				return fail(busiResult.getResultDesc());
			} else if (callback.getOutTradeNo().startsWith("ct-")) {
				BaseDTO busiResult = cateringOrderService.callback(callback);
				if (RequestResultEnum.SUCCESS.getKey().equals(busiResult.getResultCode())) {
					return success();
				}
			}
		} catch (Exception e) {
			return fail("未知错误");
		}
		return fail("未知错误");
	}

	/**
	 * 返回成功
	 *
	 * @return
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	private String success() {
		logger.warn("微信回调成功！");
		return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[处理成功]]></return_msg></xml>";
	}

	/**
	 * 返回失败
	 *
	 * @param msg
	 * @return
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	private String fail(String msg) {
		String returnMsg = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[" + msg + "]]></return_msg></xml>";
		logger.warn("微信回调失败：returnMsg={}", returnMsg);
		return returnMsg;
	}

	/**
	 * 获取xml
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	private String getXmlFromRequest(HttpServletRequest request) throws Exception {
		ServletInputStream ins = null;
		try {
			int contentLength = request.getContentLength();
			if (contentLength < 0) {
				return null;
			}
			byte buffer[] = new byte[contentLength];
			for (int i = 0; i < contentLength;) {
				int readlen;
				ins = request.getInputStream();
				readlen = ins.read(buffer, i, contentLength - i);
				if (readlen == -1) {
					break;
				}
				i += readlen;
			}
			String charEncoding = request.getCharacterEncoding();
			if (charEncoding == null) {
				charEncoding = "UTF-8";
			}
			String resultXML = new String(buffer, charEncoding);
			if (contentLength > 3072) {
				logger.warn("request body is too long, resultXML={}", resultXML);
			}
			logger.debug("微信回调参数：result={}", resultXML);
			return resultXML;
		} catch (IOException e) {
			logger.error("微信支付回调获取xml错误");
			return null;
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
	}

}
