package com.wemeCity.components.search.controller;

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
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.components.search.model.PopularWords;
import com.wemeCity.components.search.service.PopularWordsService;

@Controller
@RequestMapping("/popularWords")
public class PopularWordsController extends BaseController {

	@Autowired
	private PopularWordsService popularWordsService;

	/**
	 * 根据业务编码获取热门搜索词
	 *
	 * @param code
	 * @return
	 * @throws Exception
	 * @history 2017年9月16日 新建
	 * @auther xiaowen
	 */
	@ResponseBody
	@GetMapping("/queryPopularWords/{code}")
	public BaseDTO queryPopularWords(@PathVariable String code) throws Exception {
		// 参数校验
		if (StringUtils.isEmpty(code)) {
			return new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR.getKey(), "业务编码不允许为空！", null);
		}
		Map<String, Object> condition = new HashMap<>(5);
		condition.put("code", code);
		condition.put("isDeleted", Constants.NO);
		condition.put("sortColumn", "sort_num");
		condition.put("sortType", "asc");
		Page<PopularWords> page = PageHelper.startPage(1, 20).doSelectPage(() -> popularWordsService.queryPopularWordsList(condition));
		return new BaseDTO(RequestResultEnum.SUCCESS, page);
	}
}
