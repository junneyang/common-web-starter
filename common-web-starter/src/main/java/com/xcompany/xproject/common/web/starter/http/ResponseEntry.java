package com.xcompany.xproject.common.web.starter.http;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public class ResponseEntry {
//	private String requestid;
//	private int status;
	private int code;
	private String msg;
	private Object data;
	private List<Map<String,String>> errors;
	private Timestamp timestamp;
	private String app;
	
	
	public ResponseEntry() {
		super();
	}
	
//	public String getRequestid() {
//		return requestid;
//	}
//
//	public void setRequestid(String requestid) {
//		this.requestid = requestid;
//	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "ResponseEntry [code=" + code + ", msg=" + msg + ", data="
				+ data + ", errors=" + errors + ", timestamp=" + timestamp
				+ ", app=" + app + "]";
	}

	

}
