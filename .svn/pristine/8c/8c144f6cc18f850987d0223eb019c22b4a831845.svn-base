package com.bqiong.usercenter.http;

import java.util.Map;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:17:43 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:17:43 	修改人：
 *  	描述	:
 ***********************************************************
 */
public interface HttpClientService {
	/**
	 * 以POST方式发送Data报文
	 * 
	 * @param url
	 *            目标地址
	 * @param Data
	 *            Data格式报文信息
	 * @param encoding
	 *            传输/解析编码
	 * @return 响应报文
	 * @throws IllegalArgumentException
	 *             输入参数非法
	 * @throws AppRTException
	 *             响应状态不是200
	 */
	public String postData(String url, String data, String encoding);

	/**
	 * 
	 * @param url
	 * @param Data
	 * @param encoding
	 * @param connectionTimeout
	 * @param readTimeout
	 * @return
	 */
	public String postData(String url, String data, String encoding,
			int connectionTimeout, int readTimeout);

	/**
	 * 以POST方式发送Data报文
	 * 
	 * @param url
	 *            目标地址
	 * @param Data
	 *            Data格式报文信息
	 * @param reqEncoding
	 *            传输编码
	 * @param respEncoding
	 *            解析编码
	 * @return 响应报文
	 * @throws IllegalArgumentException
	 *             输入参数非法
	 * @throws AppRTException
	 *             响应状态不是200
	 */
	public String postData(String url, String data, String reqEncoding,
			String respEncoding);

	/**
	 * 
	 * @param url
	 * @param Data
	 * @param reqEncoding
	 * @param respEncoding
	 * @param connectionTimeout
	 * @param readTimeout
	 * @return
	 */
	public String postData(String url, String data, String reqEncoding,
			String respEncoding, int connectionTimeout, int readTimeout);

	/**
	 * 以GET方式在URL中添加参数然后请求目标地址，并得到响应Data
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数对
	 * @param encoding
	 *            解析编码
	 * @return 响应报文
	 * @throws IllegalArgumentException
	 *             输入参数非法
	 * @throws AppRTException
	 *             响应状态不是200
	 */
	public String getData(String url, Map<String, String> params, String encoding);

	/**
	 * 
	 * @param url
	 * @param params
	 * @param encoding
	 * @param connectionTimeout
	 * @param readTimeout
	 * @return
	 */
	public String getData(String url, Map<String, String> params,
			String encoding, int connectionTimeout, int readTimeout);

	/**
	 * 以GET方式在URL中添加参数然后请求目标地址，并得到响应Data
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数对
	 * @param reqEncoding
	 *            传输编码
	 * @param respEncoding
	 *            解析编码
	 * @return 响应报文
	 * @throws IllegalArgumentException
	 *             输入参数非法
	 * @throws AppRTException
	 *             响应状态不是200
	 */
	public String getData(String url, Map<String, String> params,
			String reqEncoding, String respEncoding);

	/**
	 * 
	 * @param url
	 * @param params
	 * @param reqEncoding
	 * @param respEncoding
	 * @param connectionTimeout
	 * @param readTimeout
	 * @return
	 */
	public String getData(String url, Map<String, String> params,
			String reqEncoding, String respEncoding, int connectionTimeout,
			int readTimeout);

	/**
	 * 调用HTTP方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @return HTTP响应
	 * @throws IllegalArgumentException
	 *             输入参数非法
	 */
	public HttpResponse execute(HttpRequest request)throws Exception;

	/**
	 * 调用HTTP方法
	 * 
	 * @param request
	 *            HTTP请求
	 * @param response
	 *            HTTP响应
	 * @throws Exception 
	 * @throws IllegalArgumentException
	 *             输入参数非法
	 */
	public void execute(HttpRequest request, HttpResponse response) throws Exception;
}
