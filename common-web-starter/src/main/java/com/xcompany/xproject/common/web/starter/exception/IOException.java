package com.xcompany.xproject.common.web.starter.exception;

import java.util.List;
import java.util.Map;

public class IOException extends CommonException {

	public IOException(int code, Object[] objects,
			List<Map<String, String>> errors) {
		super(code, objects, errors);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -7302548304670382701L;
	
}
