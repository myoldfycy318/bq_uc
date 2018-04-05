package com.bqiong.usercenter.http;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.http.HttpRequest.HttpMethodType;


/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:17:56 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:17:56 	修改人：
 *  	描述	:
 ***********************************************************
 */
@Service
public class HttpClientServiceImpl implements HttpClientService
{

	private static final Logger logger = LoggerFactory.getLogger(HttpClientServiceImpl.class);

	/**
	 * 默认SSL端口
	 */
	private static final int DEFAULT_SSL_PORT = 443;

	/**
	 * 自定义SSL端口
	 */
	private int customSSLPort = 8445;

	/**
	 * 是否支持日志输出
	 */
	private boolean isLogEnabled = false;

	/**
	 * 初始化
	 */
	public void init()
	{
		if (logger.isInfoEnabled())
		{
			logger.info("Init SSLSocketFactory");
		}

		// 注册SSL工厂
		Protocol.registerProtocol("https", new Protocol("https", new SSLSocketFactoryImpl(), DEFAULT_SSL_PORT));
		Protocol.registerProtocol("https ", new Protocol("https", new SSLSocketFactoryImpl(), customSSLPort));
	}

	/**
	 * {@inheritDoc}
	 */
	public String postData(String url, String data, String encoding)
	{
		return postData(url, data, encoding, encoding, HttpRequest.DEFAULT_TIMEOUT, HttpRequest.DEFAULT_TIMEOUT);
	}

	public String postData(String url, String data, String encoding, int connectionTimeout, int readTimeout)
	{
		return postData(url, data, encoding, encoding, connectionTimeout, readTimeout);
	}

	/**
	 * {@inheritDoc}
	 */
	public String postData(String url, String data, String reqEncoding, String respEncoding)
	{
		return this.postData(url, data, reqEncoding, respEncoding, HttpRequest.DEFAULT_TIMEOUT, HttpRequest.DEFAULT_TIMEOUT);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param connectionTimeout
	 * @param readTimeout
	 */
	public String postData(String url, String data, String reqEncoding, String respEncoding, int connectionTimeout,
			int readTimeout)
	{
		if (StringUtils.isBlank(url))
		{
			throw new IllegalArgumentException("The url cannot be blank.");
		}

		// 请求对象
		HttpRequest httpReq = new HttpRequest();
		httpReq.setMethodType(HttpMethodType.POST);
		httpReq.setUrl(url);
		httpReq.setContentType("text/Data");
		httpReq.setEncoding(reqEncoding);
		httpReq.setRequestBody(data);
		httpReq.setConnectionTimeout(connectionTimeout);
		httpReq.setReadTimeout(readTimeout);

		// 响应对象, 指定解析编码
		HttpResponse httpResp = new HttpResponse(respEncoding);

		// 发送请求并得到响应
		execute(httpReq, httpResp);

		// 验证响应状态
		validateResponseStatus(httpResp);

		return httpResp.getResponseBody();
	}

	public String getData(String url, Map<String, String> params, String encoding)
	{
		return getData(url, params, encoding, encoding, HttpRequest.DEFAULT_TIMEOUT, HttpRequest.DEFAULT_TIMEOUT);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param connectionTimeout
	 * @param readTimeout
	 */
	public String getData(String url, Map<String, String> params, String encoding, int connectionTimeout, int readTimeout)
	{
		return getData(url, params, encoding, encoding, connectionTimeout, readTimeout);
	}

	public String getData(String url, Map<String, String> params, String reqEncoding, String respEncoding)
	{
		return this.getData(url, params, reqEncoding, respEncoding, HttpRequest.DEFAULT_TIMEOUT, HttpRequest.DEFAULT_TIMEOUT);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param connectionTimeout
	 * @param readTimeout
	 */
	public String getData(String url, Map<String, String> params, String reqEncoding, String respEncoding,
			int connectionTimeout, int readTimeout)
	{
		if (StringUtils.isBlank(url))
		{
			throw new IllegalArgumentException("The url cannot be blank.");
		}

		// 请求对象
		HttpRequest httpReq = new HttpRequest();
		httpReq.setMethodType(HttpMethodType.GET);
		httpReq.setUrl(url);
		httpReq.setEncoding(reqEncoding);
		if (MapUtils.isNotEmpty(params))
		{
			httpReq.getParams().putAll(params);
		}
		httpReq.setConnectionTimeout(connectionTimeout);
		httpReq.setReadTimeout(readTimeout);

		// 响应对象, 指定解析编码
		HttpResponse httpResp = new HttpResponse(respEncoding);

		// 发送请求并得到响应
		execute(httpReq, httpResp);

		// 验证响应状态
		validateResponseStatus(httpResp);

		return httpResp.getResponseBody();
	}

	/**
	 * 调用HTTP方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @return HTTP响应
	 */
	public HttpResponse execute(HttpRequest request)
	{
		HttpResponse response = new HttpResponse();
		execute(request, response);

		return response;
	}

	/**
	 * 调用HTTP方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @param response
	 *            HTTP响应
	 */
	public void execute(HttpRequest request, HttpResponse response)
	{
		if (request == null)
		{
			throw new IllegalArgumentException("The request cannot be null.");

		} else if (response == null)
		{
			throw new IllegalArgumentException("The response cannot be null.");
		}

		if (isLogEnabled && logger.isInfoEnabled())
		{
			logger.info(JSONObject.toJSONString(request));
		}

		HttpMethod method = null;
		InputStream in = null;
		try
		{
			method = buildHttpMethod(request);

			HttpClient client = new HttpClient();

			HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
			params.setConnectionTimeout(request.getConnectionTimeout());
			params.setSoTimeout(request.getReadTimeout());

			response.setStatus(client.executeMethod(method));
			in = method.getResponseBodyAsStream();
			if (StringUtils.isBlank(response.getEncoding()) && method instanceof HttpMethodBase)
			{
				response.setEncoding(((HttpMethodBase) method).getResponseCharSet());
			}

			// Encoding 为空，使用平台默认字符集
			response.setResponseBody(IOUtils.toString(in, response.getEncoding()));

			if (isLogEnabled && logger.isInfoEnabled())
			{
				logger.info(JSONObject.toJSONString(request));
			}
		} catch (Exception e)
		{
			logger.error("request url = " + request.getUrl() + ". error msg = " + e.getMessage(), e);

		} finally
		{
			IOUtils.closeQuietly(in);
			if (method != null)
			{
				method.releaseConnection();
			}
		}
	}

	/**
	 * 构建HTTP方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @return httpMethod对象
	 */
	private HttpMethod buildHttpMethod(HttpRequest request)
	{
		HttpMethod method = null;

		switch (request.getMethodType())
		{
		case GET:
			method = buildGetMethod(request);
			break;

		case POST:
			method = buildPostMethod(request);
			break;

		default:
			method = buildPostMethod(request);
			break;
		}

		return postProcessMethod(method, request);
	}

	/**
	 * 后处理HttpMethod
	 * 
	 * @param method
	 *            HTTP方法
	 * @param request
	 *            HTTP请求
	 * @return HTTP方法
	 */
	private HttpMethod postProcessMethod(HttpMethod method, HttpRequest request)
	{
		method.addRequestHeader("Connection", "close"); // 服务端应答完毕自动关闭连接

		Map<String, String> params = request.getParams();
		Map<String, String> nvPairs = request.getNameValuePair();
		if (MapUtils.isNotEmpty(params))
		{
			List<NameValuePair> nvPairList = new ArrayList<NameValuePair>();
			for (String name : params.keySet())
			{
				nvPairList.add(new NameValuePair(name, params.get(name)));
			}

			String origQueryString = method.getQueryString();
			if (StringUtils.isNotBlank(origQueryString))
			{
				for (String nvPair : StringUtils.split(origQueryString, "&"))
				{
					if (StringUtils.isNotBlank(nvPair))
					{
						String[] nv = StringUtils.split(nvPair, "=");
						if (ArrayUtils.isNotEmpty(nv))
						{
							nvPairList.add(new NameValuePair(nv[0], StringUtils.defaultString(getArrayElement(nv, 1))));
						}
					}
				}
			}

			// 服务器端需设定编码
			method.setQueryString(EncodingUtil.formUrlEncode(nvPairList.toArray(new NameValuePair[nvPairList.size()]),
					request.getEncoding()));
		} else if (HttpMethodType.POST.equals(request.getMethodType()) && MapUtils.isNotEmpty(nvPairs))
		{
			List<NameValuePair> nvPairList = new ArrayList<NameValuePair>();
			for (String name : nvPairs.keySet())
			{
				nvPairList.add(new NameValuePair(name, nvPairs.get(name)));
			}
			PostMethod postMethod = (PostMethod) method;
			// 服务器端需设定编码
			postMethod.setRequestBody(nvPairList.toArray(new NameValuePair[nvPairList.size()]));
		}

		Map<String, String> headers = request.getHeaders();
		if (MapUtils.isNotEmpty(headers))
		{
			for (String name : headers.keySet())
			{
				method.addRequestHeader(name, headers.get(name));
			}
		}

		return method;
	}

	/**
	 * 方法用途: 根据指定下标从给定的数组中获得数据，数组为空或下标超限时返回null<br>
	 * 实现步骤: <br>
	 * 
	 * @param array
	 * @param index
	 * @return
	 */
	private String getArrayElement(String[] array, int index)
	{
		if (array == null || index < 0 || index >= array.length)
		{
			return null;
		}

		return array[index];
	}

	/**
	 * 构建HTTP Get方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @return GetMethod对象
	 */
	private GetMethod buildGetMethod(HttpRequest request)
	{
		return new GetMethod(request.getUrl());
	}

	/**
	 * 构建HTTP Post方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @return PostMethod对象
	 */
	private PostMethod buildPostMethod(HttpRequest request)
	{
		PostMethod postMethod = new PostMethod(request.getUrl());
		try
		{
			if (request.getRequestBody() != null)
			{
				String encoding = StringUtils.defaultIfEmpty(request.getEncoding(), HttpRequest.DEFAULT_ENCODING);
				String contentType = StringUtils.defaultIfEmpty(request.getContentType(), HttpRequest.DEFAULT_CONTENT_TYPE);

				postMethod.setRequestEntity(new StringRequestEntity(request.getRequestBody(), contentType, encoding));
			}

			// 服务器端需设置HTTP Request Body编码
		} catch (UnsupportedEncodingException e)
		{
			logger.info("UnsupportedEncodingException", e);
		}

		return postMethod;
	}

	/**
	 * 检查响应状态是否为200, 不是的话抛出异常
	 * 
	 * @param httpResp
	 *            响应对象
	 * @exception AppRTException
	 *                响应状态不是200
	 */
	private void validateResponseStatus(HttpResponse httpResp)
	{
		// 验证响应状态
		if (httpResp.getStatus() != HttpResponse.STATUS_OK)
		{
			logger.info("Invalid http status! Status=" + httpResp.getStatus());
		}
	}

	/**
	 * 设置自定义SSL端口
	 * 
	 * @param customSSLPort
	 *            自定义SSL端口
	 */
	public void setCustomSSLPort(int customSSLPort)
	{
		this.customSSLPort = customSSLPort;
	}

	/**
	 * @param isLogEnabled
	 *            the isLogEnabled to set
	 */
	public void setLogEnabled(boolean isLogEnabled)
	{
		this.isLogEnabled = isLogEnabled;
	}
}
