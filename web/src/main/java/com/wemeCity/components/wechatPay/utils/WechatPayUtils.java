package com.wemeCity.components.wechatPay.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.HttpsUtils;
import com.wemeCity.common.utils.StringUtils;

public class WechatPayUtils {

	private static Logger logger = LoggerFactory.getLogger(WechatPayUtils.class);

	/** 参数名为key的跳过签名 */
	static final String PARAM_KEY = "key";

	/** 参数名为sign的跳过签名 */
	static final String PARAM_SIGN = "sign";

	/**
	 * 生成微信统一下单xml字符串
	 *
	 * @param params 参数清单
	 * @param key 商户平台设置的密钥key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @history 2017年10月5日 新建
	 * @auther xiaowen
	 */
	public static String createUnifiedorderStr(TreeMap<String, String> params, String key) {
		if (CollectionUtils.isEmpty(params)) {
			return null;
		}
		// 签名
		String sign = sign(params, key);
		logger.debug("wechat pay sign={}", sign);
		params.put("sign", sign);
		// 拼接
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		params.forEach((k, v) -> {
			xml.append("<").append(k).append(">").append("<![CDATA[").append(v).append("]]>").append("</").append(k).append(">");
		});
		xml.append("</xml>");
		logger.debug("wechat unifiedorder str={}", xml);
		return xml.toString();
	}

	/**
	 * 生成签名
	 *
	 * @param params 参数清单
	 * @param key 商户平台设置的密钥key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @history 2017年10月5日 新建
	 * @auther xiaowen
	 */
	public static String sign(TreeMap<String, String> params, String key) {
		try {
			// 拼接签名参数
			StringBuilder sb = new StringBuilder();
			params.forEach((k, v) -> {
				if (v == null || PARAM_KEY.equals(k) || PARAM_SIGN.equals(k)) {
					return;
				}
				sb.append("&").append(k).append("=").append(v);
			});
			sb.delete(0, 1);
			// 添加key
			sb.append("&key=" + key);
			logger.debug("signStr={}", sb.toString());
			// 签名
			return StringUtils.md5(sb.toString()).toUpperCase();
		} catch (Exception e) {
			logger.error("生成签名字符串失败，params={}, key={}", GsonUtils.toSimpleJson(params), key);
			return "";
		}
	}

	/**
	 * xml转TreeMap
	 *
	 * @param xml
	 * @return
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	public static TreeMap<String, String> xmlToTreeMap(String xml) {
		if (StringUtils.isEmpty(xml)) {
			return null;
		}
		TreeMap<String, String> result = new TreeMap<>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document doc = documentBuilder.parse(stream);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for (int idx = 0; idx < nodeList.getLength(); ++idx) {
				Node node = nodeList.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) node;
					result.put(element.getNodeName(), element.getTextContent());
				}
			}
			stream.close();
		} catch (Exception e) {
			logger.error("xml转换map时错误。xml={}, e=", xml, e);
			return null;
		}
		return result;
	}

	/**
	 * 创建支付参数
	 *
	 * @param prepayId
	 * @param key
	 * @return
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	public static TreeMap<String, String> createWechatPayParams(String prepayId, String key, String appid) {
		TreeMap<String, String> payDTO = new TreeMap<>();
		payDTO.put("appId", appid);
		payDTO.put("timeStamp", System.currentTimeMillis()/1000 + "");
		payDTO.put("nonceStr", UUID.randomUUID().toString().replace("-", ""));
		payDTO.put("package", "prepay_id="+prepayId);
		payDTO.put("signType", "MD5");
		payDTO.put("paySign", sign(payDTO, key));
		logger.warn("支付签名："+GsonUtils.toSimpleJson(payDTO));
		return payDTO;
	}

	public static void main(String[] args) throws Exception {
		TreeMap<String, String> m = new TreeMap<>();
		m.put("appid", "wx2421b1c4370ec43b");
		m.put("mch_id", "10000100");
		m.put("nonce_str", "1add1a30ac87aa2db72f57a2375d8fec");
		m.put("sign_type", "MD5");
		m.put("body", "JSAPI支付测试");
		m.put("out_trade_no", "1415659990");
		m.put("total_fee", "1");
		m.put("spbill_create_ip", "14.23.150.211");
		m.put("notify_url", "https://i.wemecity.net/payCallback/wechat");
		m.put("trade_type", "JSAPI");
		m.put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
		String body = HttpsUtils.post("https://api.mch.weixin.qq.com/pay/unifiedorder", null, null, new StringEntity(createUnifiedorderStr(m, "abc"), "UTF-8"));
		System.out.println(body);
	}
}
