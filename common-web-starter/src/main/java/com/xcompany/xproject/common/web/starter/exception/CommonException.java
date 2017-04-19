package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;

public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 997200116692026722L;
	private int code;
	private Object[] objects;
	private List<Map<String,String>> errors;
	
	
	public CommonException(int code, Object[] objects,
			List<Map<String, String>> errors) {
		super();
		this.code = code;
		this.objects = objects;
		this.errors = errors;
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
	public List<Map<String, String>> getErrors() {
		return errors;
	}
	public void setErrors(List<Map<String, String>> errors) {
		this.errors = errors;
	}

}
