package com.wemeCity.components.maps.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.HttpUtils;
import com.wemeCity.components.maps.dto.GoogleMapGeoCodeAddressComponents;
import com.wemeCity.components.maps.dto.GoogleMapGeoCodeDTO;

@Controller
@RequestMapping("/components/googleMap")
public class GoogleMapController extends BaseController {

	/** Google地图api key */
	private static final String GOOGLE_MAP_KEY = "AIzaSyAcxsYkxvmMqVlTNR8MQ-YW9s52JI6MEZ4";

	@ResponseBody
	@GetMapping("/queryCityByLatLng/{longitude}/{latitude}")
	public BaseDTO queryCityByLatLng(@PathVariable double longitude, @PathVariable double latitude) {
		try {
			String googleMapUrl = "https://maps.google.cn/maps/api/geocode/json?language=en&key=" + GOOGLE_MAP_KEY + "&latlng=" + latitude + "," + longitude;
			String responseBody = HttpUtils.getbody(HttpUtils.doGet(googleMapUrl, "", "get", null, null), "UTF-8");
			logger.warn("根据经纬度获取位置信息：responBody={}", responseBody);
			Gson gson = GsonUtils.getGson();
			GoogleMapGeoCodeDTO geoCode = gson.fromJson(responseBody, GoogleMapGeoCodeDTO.class);
			List<GoogleMapGeoCodeAddressComponents> lstAddressComponents = geoCode.getResults().get(1).getAddress_components();
			for (GoogleMapGeoCodeAddressComponents addressComponents : lstAddressComponents) {
				List<String> types = addressComponents.getTypes();
				if (!CollectionUtils.isEmpty(types) && types.contains("locality") && types.contains("political")) {
					logger.warn(addressComponents.getLong_name());
				}
			}
			return new BaseDTO(RequestResultEnum.SUCCESS, geoCode);
		} catch (Exception e) {
			logger.warn("根据经纬度获取google地图城市失败：服务器内部错误！longitude={}, latitude={}", longitude, latitude, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/geoCode/latlng/{longitude}/{latitude}")
	public BaseDTO geoCodeLatlng(@PathVariable double longitude, @PathVariable double latitude) {
		try {
			String googleMapUrl = "https://maps.google.cn/maps/api/geocode/json?key=" + GOOGLE_MAP_KEY + "&latlng=" + latitude + "," + longitude;
			String responseBody = HttpUtils.getbody(HttpUtils.doGet(googleMapUrl, "", "get", null, null), "UTF-8");
			logger.warn("根据经纬度获取位置信息：responseBody={}", responseBody);
			return new BaseDTO(RequestResultEnum.SUCCESS, responseBody);
		} catch (Exception e) {
			logger.warn("根据经纬度获取google地图城市失败：服务器内部错误！longitude={}, latitude={}", longitude, latitude, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}

	@ResponseBody
	@GetMapping("/geoCode/address/{address}")
	public BaseDTO geoCode(@PathVariable String address) {
		try {
			String googleMapUrl = "https://maps.google.cn/maps/api/geocode/json?address=" + address + "&key=" + GOOGLE_MAP_KEY;
			String responseBody = HttpUtils.getbody(HttpUtils.doGet(googleMapUrl, "", "get", null, null), "UTF-8");
			logger.warn("反向地址查询地址：{}"+googleMapUrl);
			logger.warn("根据地址反向位置结果：responbody={}", responseBody);
			return new BaseDTO(RequestResultEnum.SUCCESS, responseBody);
		} catch (Exception e) {
			logger.error("根据地质反向坐标出错：服务器内部错误！address={}", address, e);
			return new BaseDTO(RequestResultEnum.FAILURE, null);
		}
	}
}
