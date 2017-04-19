package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;

public class NetworkException extends CommonException {

	public NetworkException(int code, Object[] objects,
			List<Map<String, String>> errors) {
		super(code, objects, errors);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -5889401412547044320L;
	
}
