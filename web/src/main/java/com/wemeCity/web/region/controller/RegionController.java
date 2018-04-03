package com.wemeCity.web.region.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.utils.Constants;
import com.wemeCity.web.region.model.City;
import com.wemeCity.web.region.model.Country;
import com.wemeCity.web.region.model.District;
import com.wemeCity.web.region.service.CityService;
import com.wemeCity.web.region.service.CountryService;
import com.wemeCity.web.region.service.DistrictService;

@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private DistrictService districtService;

	/**
	 * 获取国家列表，最多返回20个
	 *
	 * @return
	 * @throws Exception
	 * @history 2017年9月18日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryCountryList")
	public BaseDTO queryCountryList() throws Exception {
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("isDeleted", Constants.NO);
		final int pageSize = 20;
		Page<Country> page = PageHelper.startPage(1, pageSize).doSelectPage(() -> countryService.queryCountryList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
	
	/**
	 * 根据id读取国家信息
	 *
	 * @param countryId 国家id
	 * @return
	 * @throws Exception
	 * @history 2017年9月18日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readCountry/{countryId}")
	public BaseDTO readCountry(@PathVariable long countryId) throws Exception{
		return new BaseDTO(RequestResultEnum.SUCCESS, countryService.readCountry(countryId));
	}

	
	/**
	 * 根据国家id获取城市列表
	 *
	 * @param countryId 国家id
	 * @return
	 * @throws Exception
	 * @history 2017年9月18日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryCityListByCountryId/{countryId}")
	public BaseDTO queryCityListByCountryId(@PathVariable long countryId) throws Exception {
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("isDeleted", Constants.NO);
		condition.put("countryId", countryId);
		final int pageSize = 50;
		Page<City> page = PageHelper.startPage(1, pageSize).doSelectPage(() -> cityService.queryCityList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
	
	/**
	 * 根据id读取城市信息
	 *
	 * @param cityId 城市id
	 * @return
	 * @throws Exception
	 * @history 2017年9月18日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readCity/{cityId}")
	public BaseDTO readCity(@PathVariable long cityId) throws Exception{
		return new BaseDTO(RequestResultEnum.SUCCESS, cityService.readCity(cityId));
	}
	
	
	/**
	 * 根据城市获取区列表，最多显示50条
	 *
	 * @param cityId
	 * @return
	 * @throws Exception
	 * @history 2017年9月18日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryDistrictListByCityId/{cityId}")
	public BaseDTO queryDistrictListByCityId(@PathVariable long cityId) throws Exception{
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("isDeleted", Constants.NO);
		condition.put("cityId", cityId);
		final int pageSize = 50;
		Page<District> page = PageHelper.startPage(1, pageSize).doSelectPage(() -> districtService.queryDistrictList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
	
	/**
	 * 根据区id读取区信息
	 *
	 * @param districtId
	 * @return
	 * @throws Exception
	 * @history 2017年9月18日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/readDistrict/{districtId}")
	public BaseDTO readDistrict(@PathVariable long districtId) throws Exception{
		return new BaseDTO(RequestResultEnum.SUCCESS, districtService.readDistrict(districtId));
	}
}
