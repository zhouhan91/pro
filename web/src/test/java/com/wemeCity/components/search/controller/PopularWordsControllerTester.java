package com.wemeCity.components.search.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wemeCity.common.controller.BaseControllerTester;

@RunWith(SpringJUnit4ClassRunner.class)
public class PopularWordsControllerTester extends BaseControllerTester {

	@Test
	public void testQueryPopularWords() throws Exception{
		testRestFul("/popularWords/queryPopularWords/communityIndex", "get");
	}
}
