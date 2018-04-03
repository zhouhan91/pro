package com.wemeCity.web.community.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wemeCity.common.controller.BaseControllerTester;


@RunWith(SpringJUnit4ClassRunner.class)
public class CommunityControllerTester extends BaseControllerTester {

	@Test
	@Ignore
	public void testQueryCityCommunityIndexList() throws Exception{
		testRestFul("/community/queryCityCommunityIndexList", "get");
	}
}
