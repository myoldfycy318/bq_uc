package com.bqiong.usercenter.util;

public class HttpCommonUtil 
{
	//后台参数查询url
	private static String baUrl;
	
	public static final String FORMAT = "%s/%s";
	
	public static final String DEF_CONTENT_TYPE = "text/Data";
	
	public static final String DEF_ENCODING = "utf-8";
	
	public static boolean isSuccess(String code, String message)
	{
		return SUCCESS_CODE.equals(code) && SUCCESS_MESSAGE.equalsIgnoreCase(message);
	}
	
	//json返回标识字段
	public static final String RESPONSE_CODE = "responseCode";
	
	/**
	 *json返回标识字段 
	 */
	public static final String ERROR_MESSAGE = "errorMsg";
	
	/**
	 * json返回值的标识字段
	 */
	public static final String DATA = "data";
	/**
	 * 成功的code值
	 */
	public static final String SUCCESS_CODE = "1000";
	
	public static final String SUCCESS_MESSAGE = "";
	
	/***************************查询clientId*******************************/
	public static final String DEF_BA_URL = "http://192.168.130.132:9000";
	
	public static final String CLIENTID_QUERY_URI = "merchantappaudit/toview?appCode=";
	
	public static String getBaUrl() 
	{
		return baUrl;
	}

	public static void setBaUrl(String baUrl)
	{
		HttpCommonUtil.baUrl = baUrl;
	}
}
