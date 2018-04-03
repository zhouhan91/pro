package com.wemeCity.components.sms.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.SpringWiredBean;
import com.wemeCity.components.sms.model.Sms;

public class SmsSendThread implements Runnable {

	private Logger logger = LoggerFactory.getLogger(SmsSendThread.class);

	private Sms sms;

	public SmsSendThread() {
	}

	public SmsSendThread(Sms sms) {
		this.sms = sms;
	}

	@Override
	public void run() {
		try {
			logger.debug("send sms params: sms={}", GsonUtils.toSimpleJson(sms));
			SpringWiredBean springWiredBean = SpringWiredBean.getInstance();
			Properties properties = (Properties) springWiredBean.getBeanById("yamlproperties");
			String url = properties.getProperty("system.sms.url");
			String ac = properties.getProperty("system.sms.ac");
			String authkey = properties.getProperty("system.sms.authkey");
			String cgid = properties.getProperty("system.sms.cgid");
			StringBuffer sb = new StringBuffer();
			sb.append(url).append("&ac=").append(ac).append("&authkey=").append(authkey).append("&cgid=").append(cgid);
			sb.append("&m=").append(sms.getReciver()).append("&c=").append(URLEncoder.encode(sms.getContent(), "UTF-8"));
			SimpleClientHttpRequestFactory client = new SimpleClientHttpRequestFactory();
			ClientHttpRequest request = client.createRequest(new URI(sb.toString()), HttpMethod.POST);
			ClientHttpResponse response = request.execute();
			InputStream is = response.getBody(); //获得返回数据,注意这里是个流
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			while((str = br.readLine())!=null){
				System.out.println(str);//获得页面内容或返回内容
			}
			br.close();
			isr.close();
			is.close();
			logger.debug("send sms success: url={}", sb.toString());
		} catch (Exception e) {
			logger.debug("sms send fail: sms={}", GsonUtils.toSimpleJson(sms), e);
		}
	}

}
