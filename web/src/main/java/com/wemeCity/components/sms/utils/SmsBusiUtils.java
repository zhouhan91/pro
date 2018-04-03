package com.wemeCity.components.sms.utils;

import java.util.HashMap;
import java.util.Map;

import com.wemeCity.common.enums.EHCacheNameEnum;
import com.wemeCity.components.sms.enums.SmsBusiCodeEnum;
import com.wemeCity.components.sms.model.SmsBusi;

public class SmsBusiUtils {

	private static final Map<String, SmsBusi> busi=new HashMap<String, SmsBusi>(5);
	
	static{
		busi.put(SmsBusiCodeEnum.REGISTER.getKey(), new SmsBusi(SmsBusiCodeEnum.REGISTER.getKey(), EHCacheNameEnum.REGISTER_CODE.getKey(), EHCacheNameEnum.REGISTER_SMS.getKey()));
		busi.put(SmsBusiCodeEnum.RETRIEVE_PASSWORD.getKey(), new SmsBusi(SmsBusiCodeEnum.RETRIEVE_PASSWORD.getKey(), EHCacheNameEnum.RETRIEVE_PASSWORD_CODE.getKey(), EHCacheNameEnum.RETRIEVE_PASSWORD_SMS.getKey()));
		busi.put(SmsBusiCodeEnum.AUTHENTICATE.getKey(), new SmsBusi(SmsBusiCodeEnum.AUTHENTICATE.getKey(), EHCacheNameEnum.AUTHENTICATE_CODE.getKey(), EHCacheNameEnum.AUTHENTICATE_SMS.getKey()));
		busi.put(SmsBusiCodeEnum.PAY_PASSWORD.getKey(), new SmsBusi(SmsBusiCodeEnum.PAY_PASSWORD.getKey(), EHCacheNameEnum.PAY_PASSWORD_CODE.getKey(), EHCacheNameEnum.PAY_PASSWORD_SMS.getKey()));
	}
	
	public static SmsBusi get(String busiCode){
		return busi.get(busiCode);
	}
}
