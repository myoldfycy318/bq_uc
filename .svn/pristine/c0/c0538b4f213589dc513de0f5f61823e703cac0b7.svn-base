package com.bqiong.usercenter.httpresponse;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;


/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 上午11:38:29 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 上午11:38:29 	修改人：
 *  	描述	:
 ***********************************************************
 */

public class HttpResponseDto<T> implements Serializable
{
	/** 
	 * serialVersionUID:
	 */  
	private static final long serialVersionUID = -4594975302711744499L;
	
	private String code;
	
	private String message;
	
	private List<T> dataList;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getList()
	{
		return dataList;
	}
	
	public void setList(List<T> list)
	{
		this.dataList = list;
	}

	@Override
	public String toString()
	{
		return JSONObject.toJSONString(this);
	}

}
