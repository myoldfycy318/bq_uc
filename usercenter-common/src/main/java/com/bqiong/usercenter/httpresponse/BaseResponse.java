package com.bqiong.usercenter.httpresponse;

import com.bqiong.usercenter.constants.ErrorCode;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月19日 下午4:19:25 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月19日 下午4:19:25 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class BaseResponse 
{
	/**
	 * 对外响应码
	 */
	public String code;
	
	/**
	 * 对外响应信息
	 */
	public String message;
	
	/**
	 * 返回数据
	 */
	public Object data;
	
	public BaseResponse()
	{
		
	}
	
	public BaseResponse(String code, String message) 
	{
		this.code = code;
		this.message = message;
	}
	
	public BaseResponse(String code, String message, Object data) 
	{
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static BaseResponse success()
	{
		return new BaseResponse(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage());
	}
	
	public static BaseResponse fail(ErrorCode errorCode)
	{
		return new BaseResponse(errorCode.getCode(), errorCode.getMessage());
	}
	
	public static BaseResponse fail(ErrorCode errorCode, Object data)
	{
		return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), data);
	}
	
	public static BaseResponse fail(String code, String message)
	{
		return new BaseResponse(code, message);
	}
	
	public static BaseResponse success(Object data)
	{
		return new BaseResponse(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
	}
	
	
	public String getCode() 
	{
		return code;
	}

	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@SuppressWarnings("rawtypes")
	public static BaseResponse buildResponse(ErrorCode errorCode, Map result)
	{
		if(errorCode == ErrorCode.SUCCESS)
		{
			if(CollectionUtils.isEmpty(result))
			{
				return BaseResponse.success();
			}
			return BaseResponse.success(result);
		}
		if(CollectionUtils.isEmpty(result))
		{
			return BaseResponse.fail(errorCode);
		}
		return BaseResponse.fail(errorCode, result);
	}

    public static boolean isSuccess(BaseResponse baseResponse){
         return ErrorCode.SUCCESS.getCode().equals(baseResponse.getCode());
    }
}
