package com.xcompany.xproject.common.web.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonWebConfigurationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonWebConfigurationService.class);
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String sayHello() {
		LOGGER.info(this.message);
		return this.message;
	}
}
