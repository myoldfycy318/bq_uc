package com.bqiong.usercenter.http;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:18:02 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:18:02 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class HttpRequest implements Serializable
{

	private static final long serialVersionUID = 1L;

	/**
	 * 默认超时时间 (30S)
	 */
	public static final int DEFAULT_TIMEOUT = 30 * 1000;

	/**
	 * 默认编码
	 */
	public static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 默认内容类型
	 */
	public static final String DEFAULT_CONTENT_TYPE = "text/xml";

	/**
	 * 默认HTTP方法类型
	 */
	public static final HttpMethodType DEFAULT_METHOD_TYPE = HttpMethodType.POST;

	/**
	 * 目标地址
	 */
	private String url;

	/**
	 * 请求内容
	 */
	private String requestBody;

	/**
	 * 连接超时
	 */
	private int connectionTimeout = DEFAULT_TIMEOUT;

	/**
	 * 读取超时
	 */
	private int readTimeout = DEFAULT_TIMEOUT;

	/**
	 * 请求内容类型
	 */
	private String contentType = DEFAULT_CONTENT_TYPE;

	/**
	 * 编码
	 */
	private String encoding = DEFAULT_ENCODING;

	/**
	 * 方法类型
	 */
	private HttpMethodType methodType = DEFAULT_METHOD_TYPE;

	/**
	 * URL参数对
	 */
	private Map<String, String> params = new LinkedHashMap<String, String>();

	/**
	 * URL参数对
	 */
	private Map<String, String> headers = new LinkedHashMap<String, String>();

	/**
	 * POST 键值对
	 */
	private Map<String, String> nameValuePair = new LinkedHashMap<String, String>();

	/**
	 * 默认无参构造函数
	 */
	public HttpRequest()
	{

	}

	/**
	 * 构造函数
	 * 
	 * @param methodType
	 *            HTTP方法类型
	 * @param url
	 *            目标地址
	 * @param params
	 *            URL参数对
	 */
	public HttpRequest(HttpMethodType methodType, String url, Map<String, String> params)
	{

		this.methodType = methodType;
		this.url = url;
		this.params = params;
	}

	/**
	 * 构造函数
	 * 
	 * @param methodType
	 *            HTTP方法类型
	 * @param url
	 *            目标地址
	 * @param requestBody
	 *            请求内容
	 * @param contentType
	 *            请求内容类型
	 * @param encoding
	 *            编码
	 */
	public HttpRequest(HttpMethodType methodType, String url, String requestBody, String contentType, String encoding)
	{

		this.methodType = methodType;
		this.url = url;
		this.requestBody = requestBody;
		this.contentType = contentType;
		this.encoding = encoding;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{

		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{

		this.url = url;
	}

	/**
	 * @return the requestBody
	 */
	public String getRequestBody()
	{

		return requestBody;
	}

	/**
	 * @param requestBody
	 *            the requestBody to set
	 */
	public void setRequestBody(String requestBody)
	{

		this.requestBody = requestBody;
	}

	/**
	 * @return the connectionTimeout
	 */
	public int getConnectionTimeout()
	{

		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout
	 *            the connectionTimeout to set
	 */
	public void setConnectionTimeout(int connectionTimeout)
	{

		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @return the readTimeout
	 */
	public int getReadTimeout()
	{

		return readTimeout;
	}

	/**
	 * @param readTimeout
	 *            the readTimeout to set
	 */
	public void setReadTimeout(int readTimeout)
	{

		this.readTimeout = readTimeout;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType()
	{

		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType)
	{

		this.contentType = contentType;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding()
	{

		return encoding;
	}

	/**
	 * @param encoding
	 *            the encoding to set
	 */
	public void setEncoding(String encoding)
	{

		this.encoding = encoding;
	}

	/**
	 * @return the methodType
	 */
	public HttpMethodType getMethodType()
	{

		return methodType;
	}

	/**
	 * @param methodType
	 *            the methodType to set
	 */
	public void setMethodType(HttpMethodType methodType)
	{

		this.methodType = methodType;
	}

	/**
	 * @return the params
	 */
	public Map<String, String> getParams()
	{

		return params;
	}

	/**
	 * 添加参数, 参数名为空会被忽略
	 * 
	 * @param name
	 *            参数名
	 * @param value
	 *            参数值
	 */
	public void setParameter(String name, String value)
	{

		if (StringUtils.isNotBlank(name))
		{
			params.put(name, value);
		}
	}

	public Map<String, String> getNameValuePair()
	{
		return nameValuePair;
	}

	public void setNameValuePair(String name, String value)
	{
		if (StringUtils.isNotBlank(name))
		{
			nameValuePair.put(name, value);
		}
	}

	/**
	 * @return the headers
	 */
	public Map<String, String> getHeaders()
	{

		return headers;
	}

	/**
	 * 添加参数, 参数名为空会被忽略
	 * 
	 * @param name
	 *            参数名
	 * @param value
	 *            参数值
	 */
	public void setHeaders(String name, String value)
	{

		if (StringUtils.isNotBlank(name))
		{
			headers.put(name, value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{

		StringBuilder details = new StringBuilder();
		details.append(IOUtils.LINE_SEPARATOR).append("[Request URL]=").append(url);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request MethodType]=").append(methodType);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request Encoding]=").append(encoding);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request ContentType]=").append(contentType);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request Params]=").append(params);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request Headers]=").append(headers);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request Body]=").append(requestBody);
		details.append(IOUtils.LINE_SEPARATOR).append("[Request NameValuePairs]=").append(nameValuePair);
		return details.toString();
	}

	public static enum HttpMethodType
	{
		GET, POST
	}
}
