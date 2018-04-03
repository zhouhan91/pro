package com.wemeCity.common.dto;

import org.springframework.beans.BeanUtils;

import com.github.pagehelper.Page;
import com.wemeCity.common.enums.RequestResultEnum;

/**
 * 分页结果集
 * 
 * @author Xiang xiaowen
 * @since JDK8
 * @history 2017年6月7日 新建
 */
public class PageDTO extends BaseDTO {

	private int pageNum;

	private int pageSize;

	private int pages;

	public PageDTO() {
	}

	public PageDTO(String resultCode, String resultDesc, Object resultData) {
		super(resultCode, resultDesc, resultData);
	}

	public PageDTO(RequestResultEnum requestResult, Object result) {
		super(requestResult, result);
	}

	@SuppressWarnings("rawtypes")
	public PageDTO(RequestResultEnum requestResultEnum, Page page) {
		super(requestResultEnum, page.getResult());
		BeanUtils.copyProperties(page, this);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
