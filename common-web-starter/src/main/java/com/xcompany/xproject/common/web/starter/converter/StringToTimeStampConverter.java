package com.xcompany.xproject.common.web.starter.converter;

import java.sql.Timestamp;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTimeStampConverter implements Converter<String, Timestamp>{

	@Override
	public Timestamp convert(String source) {
		// TODO Auto-generated method stub
		if (null != source) {
			return new Timestamp(Long.parseLong(source));
		} else {
			return null;
		}
		
	}
	
}
