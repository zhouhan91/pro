package com.wemeCity.components.file.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wemeCity.common.utils.GsonUtils;
import com.wemeCity.common.utils.SpringWiredBean;
import com.wemeCity.components.file.model.FileUpload;
import com.wemeCity.components.file.service.FileUploadService;

public class FileUploadThread implements Runnable {

	private Logger logger = LoggerFactory.getLogger(FileUploadThread.class);

	private FileUpload fileUpload;

	public FileUploadThread() {
	}

	public FileUploadThread(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	@Override
	public void run() {
		logger.debug("save file upload params: fileUpload={}", GsonUtils.toSimpleJson(this.fileUpload));
		try {
			SpringWiredBean springWiredBean = SpringWiredBean.getInstance();
			FileUploadService fileUploadService = (FileUploadService) springWiredBean.getBeanById("fileUploadService");
			fileUploadService.insertFileUpload(fileUpload);
		} catch (Exception e) {
			logger.error("save file upload fail : fileUpload={}", GsonUtils.toSimpleJson(this.fileUpload), e);
		}
	}
}
