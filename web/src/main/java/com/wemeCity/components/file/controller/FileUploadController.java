package com.wemeCity.components.file.controller;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wemeCity.common.controller.BaseController;
import com.wemeCity.common.dto.BaseDTO;
import com.wemeCity.common.enums.RequestResultEnum;
import com.wemeCity.common.service.impl.CommonThreadPool;
import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.StringUtils;
import com.wemeCity.components.file.model.FileUpload;
import com.wemeCity.components.file.service.impl.FileUploadThread;

@Controller
@RequestMapping("/components")
public class FileUploadController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	@Qualifier("yamlproperties")
	private Properties properties;

	@ResponseBody
	@PostMapping("/fileUpload")
	public BaseDTO fileUpload(@RequestParam MultipartFile file, @RequestParam String busiCode, HttpSession session) {
		BaseDTO result = null;
		if (file == null || StringUtils.isEmpty(busiCode)) {
			result = new BaseDTO(RequestResultEnum.NOT_NULL_PARAM_ERROR.getKey(), RequestResultEnum.NOT_NULL_PARAM_ERROR.getDescription(), busiCode);
			logger.info("fileUpload not-null params validate fail: result={}", GsonUtils.toSimpleJson(result));
			return result;
		}
		String fileName = file.getOriginalFilename();
		String path = properties.getProperty("components.fileUpload.path." + busiCode);
		String uploadPath = session.getServletContext().getRealPath("upload");
		uploadPath += path;

		UUID uuid = UUID.randomUUID();
		String prefix = uuid.toString().replace("-", "");
		String last = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		fileName = prefix + last;
		File target = new File(uploadPath, fileName);
		try {
			if (!target.exists()) {
				target.createNewFile();
				file.transferTo(target);
			}
		} catch (Exception e) {
			result = new BaseDTO(RequestResultEnum.FILE_WRITE_FAIL.getKey(), RequestResultEnum.FILE_WRITE_FAIL.getDescription(), busiCode);
			logger.info("fileUpload fail:not login, result={}", GsonUtils.toSimpleJson(result), e);
			return result;
		}
		String link = "/upload" + path + "/" + fileName;
		// save thread
		FileUpload fileUpload = new FileUpload();
		fileUpload.setBusiCode(busiCode);
		fileUpload.setFileName(file.getOriginalFilename());
		fileUpload.setSaveName(fileName);
		fileUpload.setPath(link);
		CommonThreadPool.getInstance().execute(new FileUploadThread(fileUpload));
		result = new BaseDTO(RequestResultEnum.SUCCESS.getKey(), RequestResultEnum.SUCCESS.getDescription(), link);
		return result;
	}
}
