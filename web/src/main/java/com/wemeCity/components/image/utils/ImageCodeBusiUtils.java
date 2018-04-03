package com.wemeCity.components.image.utils;

import java.util.HashMap;
import java.util.Map;

import com.wemeCity.common.enums.EHCacheNameEnum;

public class ImageCodeBusiUtils {

	public final static Map<String, EHCacheNameEnum> busi=new HashMap<>();
	
	static{
		busi.put("register", EHCacheNameEnum.REGISTER_CODE);
		busi.put("retrievePassword", EHCacheNameEnum.RETRIEVE_PASSWORD_CODE);
		busi.put("authenticate", EHCacheNameEnum.AUTHENTICATE_CODE);
		busi.put("payPassword", EHCacheNameEnum.PAY_PASSWORD_CODE);
	}
	
	public static EHCacheNameEnum get(String busiCode){
		return busi.get(busiCode);
	}
}
