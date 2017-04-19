package com.xcompany.xproject.common.web.starter.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcompany.xproject.common.web.starter.http.RequestEntry;

public class LoggingFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
	private final NamedThreadLocal<Timestamp> startTimeThreadLocal =  new NamedThreadLocal<Timestamp>("startTimeThreadLocal");
	
	@Autowired
	private Environment environment;
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("====================LoggingFilter_Init====================");
    }

    @Override
    public void destroy() {
    	LOGGER.info("====================LoggingFilter_Destroy====================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    	HttpServletResponse httpServletResponse = (HttpServletResponse)response;
    	
    	String requestID = httpServletRequest.getHeader("X-RequestId");
		if (null == requestID) {
			MDC.put("requestID", UUID.randomUUID().toString());
		} else {
			MDC.put("requestID", requestID);
		}
		httpServletResponse.addHeader("X-RequestId", MDC.get("requestID").toString());
		
		Timestamp timestamp = new Timestamp(new Date().getTime());
		startTimeThreadLocal.set(timestamp);
		
		LOGGER.info("====================PRE_FILTER====================");
		RequestEntry requestEntry = new RequestEntry(httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), httpServletRequest.getQueryString(), httpServletRequest.getContentLengthLong());
		LOGGER.info(requestEntry.toString());
		
		// contentCacheLimit the maximum number of bytes to cache per request
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {
        	LOGGER.info("====================POST_FILTER====================");
            String requestBody = new String(requestWrapper.getContentAsByteArray());
            if (requestBody.length() > 0) {
            	ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<String, Object>();
                map = mapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
                LOGGER.info(String.format("REQUEST_BODY %s" , map));
			} else {
				LOGGER.info(String.format("REQUEST_BODY: %s" , "NULL"));
			}
            
            String contentType = response.getContentType();
            LOGGER.info(String.format("RESPONSE_CONTENT_TYPE: %s", contentType));
            String responseBody = new String(responseWrapper.getContentAsByteArray());
            responseWrapper.copyBodyToResponse();
            if (null != contentType && contentType.equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
                // Do not forget this line after reading response content or actual response will be empty!
                if (responseBody.length() > 0) {
                	LOGGER.info(String.format("RESPONSE_BODY: %s", responseBody));
    			} else {
    				LOGGER.info(String.format("RESPONSE_BODY: %s", "NULL"));
    			}
			} else {
				LOGGER.info(String.format("RESPONSE_BODY: %s", "IGNORE"));
			}                     
            
            Timestamp endTimeStamp = new Timestamp(new Date().getTime());
    		Timestamp startTimestamp = startTimeThreadLocal.get();
    		long elapsedTime = endTimeStamp.getTime() - startTimestamp.getTime();
    		long threshhold = Long.parseLong(environment.getProperty("logging.elapsedtime.threshhold"));
    		String elapsedTimeString = String.format("%s takes %d milliseconds", httpServletRequest.getRequestURI(), elapsedTime);
    		if (elapsedTime >= threshhold) {
    			LOGGER.error(elapsedTimeString);
    		} else {
    			LOGGER.info(elapsedTimeString);
    		}
    		LOGGER.info("====================END_FILTER====================");
//    		MDC.clear();
        }

    }
}
