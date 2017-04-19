package com.xcompany.xproject.common.web.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="common.web")
public class CommonWebConfigurationProperties {
	
	private String message = "hello";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
