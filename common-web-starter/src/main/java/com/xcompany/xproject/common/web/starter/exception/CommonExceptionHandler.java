package com.xcompany.xproject.common.web.starter.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xcompany.xproject.common.web.starter.constant.ResponseCode;
import com.xcompany.xproject.common.web.starter.http.APIResponse;
import com.xcompany.xproject.common.web.starter.http.APIResponse.APIResponseBuilder;

@ControllerAdvice
public class CommonExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@Autowired
	private Environment environment;

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.OK)  // 200
	@ResponseBody
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {

		LOGGER.error(ex.getMessage(), ex);
		
		APIResponseBuilder apiResponseBuilder = new APIResponse().createBuilder();
		
		if (ex instanceof CommonException) {
			int code = ((CommonException) ex).getCode();
			if (ex instanceof ParamsException) {
				ParamsException bindingErrorsException = (ParamsException) ex;
				BindingResult bindingResult = bindingErrorsException
						.getBindingResult();
				if (null != bindingResult) {
					List<FieldError> fieldErrors = bindingErrorsException
							.getBindingResult().getFieldErrors();

					List<Map<String, String>> errors = new ArrayList<Map<String, String>>();
					for (FieldError fieldError : fieldErrors) {
						Map<String, String> error = new HashMap<String, String>();
						error.put("field", fieldError.getField());
						error.put("message", fieldError.getDefaultMessage());
						errors.add(error);
					}
					LOGGER.error("ParamsException With Errors");
					apiResponseBuilder.setCode(code).setObjects(((CommonException) ex).getObjects())
						.setData(null).setErrors(errors);
				} else {
					LOGGER.error("ParamsException Without Errors");
					apiResponseBuilder.setCode(code).setObjects(((CommonException) ex).getObjects())
						.setData(null).setErrors(null);
				}

			} else {
				LOGGER.error("CommonException Not ParamsException");
				apiResponseBuilder.setCode(code).setObjects(((CommonException) ex).getObjects())
					.setData(null).setErrors(null);
			}

		} else {
			LOGGER.error("UnknownException");
			apiResponseBuilder.setCode(ResponseCode.UNKNOWN_EXCEPTION);
			Set<String> activeProfies = new HashSet<String>(Arrays.asList(this.environment.getActiveProfiles()));
			if (activeProfies.contains("default") || activeProfies.contains("dev") || activeProfies.contains("test")) {
				apiResponseBuilder.setMsg(ex.toString());
			} else if (activeProfies.contains("prod")) {
				apiResponseBuilder.setMsg(ex.getMessage());
			} else {
				apiResponseBuilder.setMsg(ex.getMessage());
			}
			
			apiResponseBuilder.setObjects(null);
			apiResponseBuilder.setData(null);
			apiResponseBuilder.setErrors(null);
		}
		
		return apiResponseBuilder.build().getEntry();
	}
}
