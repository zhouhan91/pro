package com.wemeCity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.wemeCity.common.utils.BigDecimalUtils;

@SuppressWarnings("deprecation")
public class Test {

	public static void main(String[] args) throws Exception {
		// for (int i = 0; i < 100; i++) {
		//
		// new Thread(
		// new Runnable() {
		// public void run() {
		// try {
		// String str="{\"smsCode\":\"996664\",\"phone\":\"18520883292\",\"passwd\":\"passwd\"}";
		// String url = "http://192.168.0.250:8102/member/user/register";
		// http(url, str);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }).start();
		// }

		// String[] a=",1,2,3,4,4,5,5,".substring(1, ",1,2,3,4,4,5,5,".length()).split(",");
		// for(String s:a){
		// System.out.println(s);
		// }

		// Random random=new Random();
		// for(int i=0;i<100;i++){
		// int n=random.nextInt(100);
		// System.out.println(n%2);
		// }
		// System.out.println((random.nextInt(899)+100)*0.01);

		// String str =
		// "<xml><appid><![CDATA[wxfdeff9a45b0a0b7b]]></appid><attach><![CDATA[co-1510674752183-29402]]></attach><bank_type><![CDATA[CMBC_DEBIT]]></bank_type><cash_fee><![CDATA[10200]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[N]]></is_subscribe><mch_id><![CDATA[1490521032]]></mch_id><nonce_str><![CDATA[3a852e9535414269b35f0da27d5016cd]]></nonce_str><openid><![CDATA[oGMYR0Yuw0XHjEyM1pTbK480ILRY]]></openid><out_trade_no><![CDATA[co-1510674752183-29402-6285]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[BE673CCDA7910611DAE8D1AC5D4BEFDC]]></sign><time_end><![CDATA[20171114235241]]></time_end><total_fee>10200</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4200000033201711144720134629]]></transaction_id></xml>";
		// String url = "http://localhost:8080/web/payCallback/wechat";
		// http(url, str);

		// String code="co-1510674752183-29402-6285";
		// System.out.println(code.substring(0, code.lastIndexOf("-")));

		// double a=1.2433232;
		// a=-a;
		// System.out.println(a);
		// BigDecimal b=new BigDecimal("80");
		// System.out.println(BigDecimalUtils.divide(b, new BigDecimal(3), 2, BigDecimal.ROUND_FLOOR));
		// Random random=new Random();
		// double floatValue = (random.nextInt(899) + 100) * 0.01;
		// int temp = random.nextInt(100);
		// if (temp % 2 == 1) {
		// floatValue = -floatValue;
		// }
		// System.out.println(floatValue);
		// BigDecimal x=new BigDecimal(30);
		// BigDecimal v = BigDecimalUtils.divide(BigDecimalUtils.multiply(x, new BigDecimal("2")), new BigDecimal("3"), 2, BigDecimal.ROUND_FLOOR);
		// System.out.println(v);
		// DecimalFormat decimalFormat=new DecimalFormat(".00");
		// v = BigDecimalUtils.add(v, new BigDecimal(decimalFormat.format(floatValue)));
		// System.out.println(v);
		// System.out.println(new String(Base64Utils.decodeFromString("eyJ1c2VySWQiOjExOSwib3BlbklkIjoiYWJjZCIsIm9yZGVyQ29kZSI6IjE1MDk1MTYyNDg5MjMtMzA5OTQifQ==")));
		// System.out.println(GsonUtils.toSimpleJson(new String[]{"a","c","b","efege"}));
//
//		HttpResponse response = HttpUtils.doGet("http://op.juhe.cn", "/onebox/exchange/query?key=c086ade240642630cdc7bea43acea052", "", null, null);
//		System.out.println(EntityUtils.toString(response.getEntity()));

//		LocalDate date1=LocalDate.of(2017, 12, 22);
//		LocalDate date2=LocalDate.of(2017, 12, 22);
//		System.out.println(date1.until(date2, ChronoUnit.DAYS));
		
//		CommunityQueryDTO queryDTO=new CommunityQueryDTO();
//		queryDTO.setKeyWords("雷恩");
//		http("https://i.wemecity.net/community/readCityCommunity/1", "");
		
//		String xx=HttpUtils.getbody(HttpUtils.doGet("", "", "get", null, null), "UTF-8");
		
//		String xx="{\"status\":\"0\",\"msg\":\"ok\",\"result\":{\"number\":\"71210053488456\",\"type\":\"htky\",\"list\":[{\"time\":\"2018-1-27 16:55:14\",\"status\":\"[漯河市] 到漯河市【漯河转运中心】                                                                    \"},{\"time\":\"2018-1-26 3:16:29\",\"status\":\"[金华市] 金华市【义乌转运中心】，正发往【漯河转运中心】                                                                    \"},{\"time\":\"2018-1-26 2:13:15\",\"status\":\"[金华市] 到金华市【义乌转运中心】                                                                    \"},{\"time\":\"2018-1-25 22:14:28\",\"status\":\"[金华市] 到金华市【义乌东畈分部集货点】                                                                    \"},{\"time\":\"2018-1-25 21:10:15\",\"status\":\"[金华市] 金华市【义乌永顺分部】，【林斌/13625799261】已揽收                                                                    \"}],\"deliverystatus\":\"1\",\"issign\":\"0\"}}";
		
//		DecimalFormat fnum = new DecimalFormat("##0.##");
//		System.out.println(fnum.format(BigDecimalUtils.multiply(new BigDecimal("0.8530"), new BigDecimal(100))));
		
//		System.out.println("abc,".substring(0,"abc,".length()-1));
		
	}

	@SuppressWarnings("resource")
	static void http(String url, String body) throws Exception {
		// 创建默认的httpClient实例.
		HttpClient client = new DefaultHttpClient();
		// 创建httppost通过post方式访问
		HttpPost httppost = new HttpPost(url);
		
		// 作为参数发送到controller
		StringEntity entity = new StringEntity(body, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httppost.setEntity(entity);
		HttpResponse response = client.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		String result = EntityUtils.toString(resEntity, "UTF-8");
		System.out.println(result);
	}

}
