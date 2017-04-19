package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;


public class BusinessException extends CommonException {

	private static final long serialVersionUID = -4182753460188854583L;
	
	public BusinessException(int code, Object[] objects,
			List<Map<String, String>> errors) {
		super(code, objects, errors);
		// TODO Auto-generated constructor stub
	}

}
