package com.wemeCity.components.image.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wemeCity.common.enums.EHCacheNameEnum;
import com.wemeCity.common.utils.EHCacheUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.components.image.utils.ImageCodeBusiUtils;
import com.wemeCity.components.image.utils.SecurityImage;

@Controller
@RequestMapping("/imageCode")
public class ImageCodeController {

	private Logger logger = LoggerFactory.getLogger(ImageCodeController.class);

	public static final int defaultCodeLength = 4;

	@GetMapping("/getSimpleCode/{busiCode}")
	public void getSimpleCode(@PathVariable String busiCode, HttpSession session, HttpServletResponse response) throws Exception {
		logger.debug("getSimpleCode params: busiCode={}", busiCode);
		EHCacheNameEnum ehCacheNameEnum = ImageCodeBusiUtils.get(busiCode);
		if (ehCacheNameEnum == null) {
			logger.error("getSimpleCode wrong busiCode:{}", busiCode);
			PrintWriter write = response.getWriter();
			write.write("wrong busiCode!");
			write.close();
		} else {
			String sessionId = session.getId();
			String value = StringUtils.generateNumberRandomStr(defaultCodeLength);
			logger.debug("request simple image code:{}", value);
			EHCacheUtils.put(ehCacheNameEnum.getKey(), sessionId, value);
			BufferedImage image = SecurityImage.createImage(value);
			response.setContentType("image/png");
			OutputStream stream = response.getOutputStream();
			ImageIO.write(image, "png", stream);
		}
	}
	
	@GetMapping("/getCode/{busiCode}/{length}")
	public void getCode(@PathVariable String busiCode, @PathVariable int length, HttpSession session, HttpServletResponse response) throws Exception {
		logger.debug("getCode params: busiCode={}", busiCode);
		EHCacheNameEnum ehCacheNameEnum = ImageCodeBusiUtils.get(busiCode);
		if (ehCacheNameEnum == null) {
			logger.error("getCode wrong busiCode:{}", busiCode);
			PrintWriter write = response.getWriter();
			write.write("wrong busiCode!");
			write.close();
		} else {
			String sessionId = session.getId();
			String value = StringUtils.generateNumberRandomStr(length);
			logger.debug("request image code:{}", value);
			EHCacheUtils.put(ehCacheNameEnum.getKey(), sessionId, value);
			BufferedImage image = SecurityImage.createImage(value);
			response.setContentType("image/png");
			OutputStream stream = response.getOutputStream();
			ImageIO.write(image, "png", stream);
		}
	}

}
