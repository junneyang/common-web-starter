package com.xcompany.xproject.common.web.starter.http;


public class RequestEntry {
	private String method;
	private String uri;
	private String queryString;
	private Long contentLength;
	
	
	public RequestEntry(String method, String uui, String queryString,
			Long contentLength) {
		super();
		this.method = method;
		this.uri = uui;
		this.queryString = queryString;
		this.contentLength = contentLength;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUui() {
		return uri;
	}
	public void setUui(String uui) {
		this.uri = uui;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public Long getContentLength() {
		return contentLength;
	}
	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}
	@Override
	public String toString() {
		return "RequestEntry [method=" + method + ", uri=" + uri
				+ ", queryString=" + queryString + ", contentLength="
				+ contentLength + "]";
	}

}
