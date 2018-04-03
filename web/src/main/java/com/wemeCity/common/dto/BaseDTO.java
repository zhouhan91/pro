package com.wemeCity.common.dto;

import com.google.gson.Gson;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.GsonUtils;

public class BaseDTO {

	/** 接口结果返回编码 */
	private String resultCode;

	/** 接口结果返回描述 */
	private String resultDesc;

	/** 返回结果 */
	private Object resultData;

	public BaseDTO() {
	}

	public BaseDTO(String resultCode, String resultDesc, Object resultData) {
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
		this.resultData = resultData;
	}

	public BaseDTO(RequestResultEnum requestResultEnum, Object result) {
		this(requestResultEnum.getKey(), requestResultEnum.getDescription(), result);
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	
	public <T> T getResultData(Class<T> clazz) {
		if (!RequestResultEnum.SUCCESS.getKey().equals(this.resultCode)) {
			return null;
		}
		Gson gson = GsonUtils.getGson();
		return gson.fromJson(gson.toJson(this.resultData), clazz);
	}

}
