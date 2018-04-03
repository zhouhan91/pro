package com.wemeCity.web.catering.controller;

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
import com.wemeCity.web.catering.model.CateringCategory;
import com.wemeCity.web.catering.service.CateringCategoryService;

@Controller
@RequestMapping("/catering/category")
public class CateringCategoryController extends BaseController {

	@Autowired
	private CateringCategoryService cateringCategoryService;

	@ResponseBody
	@GetMapping("/queryBaseCategoryList")
	public BaseDTO queryBaseCategoryList() throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parentId", 0);
		condition.put("isDeleted", Constants.NO);
		Page<CateringCategory> page = PageHelper.startPage(1, 10).doSelectPage(() -> cateringCategoryService.queryCateringCategoryList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}

	@ResponseBody
	@GetMapping("/querySubCategoryList/{parentId}")
	public BaseDTO querySubCategoryList(@PathVariable String parentId) throws Exception {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parentId", parentId);
		condition.put("isDeleted", Constants.NO);
		Page<CateringCategory> page = PageHelper.startPage(1, 10).doSelectPage(() -> cateringCategoryService.queryCateringCategoryList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
	
	
}
