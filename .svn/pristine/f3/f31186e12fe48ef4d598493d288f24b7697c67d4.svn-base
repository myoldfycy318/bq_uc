package com.qbao.store.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年6月28日 上午9:30:59 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年6月28日 上午9:30:59 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class HttpServletResponseWrapper extends javax.servlet.http.HttpServletResponseWrapper
{

	private int statusCode;

	public HttpServletResponseWrapper(HttpServletResponse response)
	{
		super(response);
		this.statusCode = HttpServletResponse.SC_OK; // 默认的状态是200
	}

	
	public void sendError(int sc) throws IOException
	{
		statusCode = sc;
		super.sendError(sc);
	}

	
	public void sendError(int sc, String msg) throws IOException
	{
		statusCode = sc;
		super.sendError(sc, msg);
	}

	
	public void setStatus(int sc)
	{
		this.statusCode = sc;
		super.setStatus(sc);
	}

	public int getStatusCode()
	{
		return this.statusCode;
	}
}
