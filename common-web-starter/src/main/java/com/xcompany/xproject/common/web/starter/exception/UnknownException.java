package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;

public class UnknownException extends CommonException {

	public UnknownException(int code, Object[] objects,
			List<Map<String, String>> errors) {
		super(code, objects, errors);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 6167890843629602313L;
	
}
