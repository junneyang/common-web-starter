package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class ParamsException extends CommonException {
	private static final long serialVersionUID = 1600465240492997865L;
	private BindingResult bindingResult;
	
	public ParamsException(int code, Object[] objects,
			List<Map<String, String>> errors, BindingResult bindingResult) {
		super(code, objects, errors);
		this.bindingResult = bindingResult;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}
	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}

}
