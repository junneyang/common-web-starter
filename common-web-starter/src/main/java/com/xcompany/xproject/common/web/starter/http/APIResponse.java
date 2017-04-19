package com.xcompany.xproject.common.web.starter.http;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;

import com.xcompany.xproject.common.web.starter.constant.ResponseCode;
import com.xcompany.xproject.common.web.starter.context.ApplicationContextProvider;


public class APIResponse {
	private static final Logger LOGGER = LoggerFactory.getLogger(APIResponse.class);
	
	private int code;
	private Object[] objects;
	private String msg;
	private Object data;
	private List<Map<String,String>> errors;
	
	public APIResponse() {
		super();
	}

	public APIResponse(APIResponseBuilder apiResponseBuilder) {
		super();
		
		this.code = apiResponseBuilder.code;
		this.objects = apiResponseBuilder.objects;
		this.msg = apiResponseBuilder.msg;
		this.data = apiResponseBuilder.data;
		this.errors = apiResponseBuilder.errors;
	}

	public ResponseEntry getEntry() {
		
		ResponseEntry responseEntry = new ResponseEntry();
		MessageSource messageSource = (MessageSource) ApplicationContextProvider.getBean("messageSource", MessageSource.class);
		Environment environment = (Environment) ApplicationContextProvider.getBean("environment" ,Environment.class);
		LOGGER.info(messageSource.toString());
		LOGGER.info(environment.toString());
		
		Locale locale = LocaleContextHolder.getLocale();
		String app = environment.getProperty("spring.application.name");

//		String requestid = MDC.get("requestID");
//		responseEntry.setRequestid(requestid);

		responseEntry.setCode(this.code);
		if (this.code == ResponseCode.UNKNOWN_EXCEPTION) {
			responseEntry.setMsg(this.msg);
		} else {
			String msg = messageSource.getMessage(Integer.toString(this.code), this.objects, null, locale);
			responseEntry.setMsg(msg);
		}
		
		responseEntry.setData(this.data);
		responseEntry.setErrors(this.errors);
//		responseEntry.setTimestamp(new Date().getTime());
//		responseEntry.setTimestamp(java.util.Calendar.getInstance().getTimeInMillis());
		responseEntry.setTimestamp(new Timestamp(new Date().getTime()));
		responseEntry.setApp(app);
		LOGGER.debug(responseEntry.toString());
		return responseEntry;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Map<String, String>> getErrors() {
		return errors;
	}

	public void setErrors(List<Map<String, String>> errors) {
		this.errors = errors;
	}
	
	
	public APIResponseBuilder createBuilder() {
		return new APIResponseBuilder();
	}
	public static class APIResponseBuilder {

		private int code;
		private Object[] objects;
		private String msg;
		private Object data;
		private List<Map<String,String>> errors;
		
		public APIResponseBuilder() {
			super();
		}
		public APIResponseBuilder setCode(int code) {
			this.code = code;
			return this;
		}
		public APIResponseBuilder setObjects(Object[] objects) {
			this.objects = objects;
			return this;
		}
		public APIResponseBuilder setMsg(String msg) {
			this.msg = msg;
			return this;
		}
		public APIResponseBuilder setData(Object data) {
			this.data = data;
			return this;
		}
		public APIResponseBuilder setErrors(List<Map<String,String>> errors) {
			this.errors = errors;
			return this;
		}
		public APIResponse build() {
			return new APIResponse(this);
		}
		
	}
	
}
