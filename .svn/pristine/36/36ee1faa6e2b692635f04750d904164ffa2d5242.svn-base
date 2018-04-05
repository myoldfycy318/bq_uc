package com.bqiong.usercenter.http;

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:17:49 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:17:49 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class ExtPostMethod extends PostMethod
{

	private String encoding;

	public ExtPostMethod()
	{
		super();
	}

	public ExtPostMethod(String uri)
	{
		super(uri);
	}

	public ExtPostMethod(String uri, String encoding)
	{
		super(uri);
		setEncoding(encoding);
	}

	@Override
	public String getRequestCharSet()
	{
		if (encoding != null)
		{
			return encoding;
		}

		return super.getRequestCharSet();
	}

	/**
	 * @param encoding
	 */
	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}
}
