package com.bqiong.usercenter.http;

import java.io.Serializable;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:18:08 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:18:08 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class HttpResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 响应状态-成功
	 */
	public static final int STATUS_OK = HttpStatus.SC_OK;
	
	/**
	 * 响应状态
	 */
	private int status;
	
	/**
	 * 响应内容
	 */
	private String responseBody;
	
	/**
	 * 响应编码
	 */
	private String encoding;
	
	/**
	 * 默认无参构造函数, 未指定解析响应报文的编码, 按传输编码解析
	 */
	public HttpResponse() {
	}

	/**
	 * 构造函数, 指定解析响应报文的编码
	 * 
	 * @param encoding
	 *            响应编码
	 */
	public HttpResponse(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the responseBody
	 */
	public String getResponseBody() {
		return responseBody;
	}

	/**
	 * @param responseBody the responseBody to set
	 */
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder details = new StringBuilder();
		details.append(IOUtils.LINE_SEPARATOR).append("[Response Status]=").append(status);
		details.append(IOUtils.LINE_SEPARATOR).append("[Response Encoding]=").append(encoding);
		details.append(IOUtils.LINE_SEPARATOR).append("[Response Body]=").append(responseBody);
		
		return details.toString();
	}
}
