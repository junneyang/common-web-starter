package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;



public class PermissionException extends CommonException {

	private static final long serialVersionUID = -6612190356331868387L;
	
	public PermissionException(int code, Object[] objects,
			List<Map<String, String>> errors) {
		super(code, objects, errors);
		// TODO Auto-generated constructor stub
	}
	
}
