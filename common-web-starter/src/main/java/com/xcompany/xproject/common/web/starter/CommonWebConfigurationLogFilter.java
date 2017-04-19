package com.xcompany.xproject.common.web.starter;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xcompany.xproject.common.web.starter.filter.LoggingFilter;

@Configuration
public class CommonWebConfigurationLogFilter extends WebMvcConfigurerAdapter {
//	@Bean
//	public LoggerHandlerInterceptor LoggerHandlerInterceptor() {
//		return new LoggerHandlerInterceptor();
//	}
	@Bean
	public Filter LoggingFilter() {
		LoggingFilter loggingFilter = new LoggingFilter();
		return loggingFilter;
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		// addPathPatterns, excludePathPatterns 
//		registry.addInterceptor(LoggerHandlerInterceptor()).addPathPatterns("/**");
//		super.addInterceptors(registry);
//	}
}
