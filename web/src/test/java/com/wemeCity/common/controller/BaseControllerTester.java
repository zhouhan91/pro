package com.wemeCity.common.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations="classpath:spring/*.xml")
public class BaseControllerTester {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	protected void testBody(String url, String body) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(url);
		builder.accept(MediaType.ALL);
		builder.content(body);
		builder.contentType(MediaType.APPLICATION_JSON_UTF8);
		ResultActions resultActions = mockMvc.perform(builder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andDo(MockMvcResultHandlers.print());
	}

	protected void testRestFul(String url, String method) throws Exception {
		MockHttpServletRequestBuilder builder = "get".equalsIgnoreCase(method) ? MockMvcRequestBuilders.get(url) : MockMvcRequestBuilders.post(url);
		builder.accept(MediaType.ALL);
		ResultActions resultActions = mockMvc.perform(builder);
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andDo(MockMvcResultHandlers.print());
	}

}
