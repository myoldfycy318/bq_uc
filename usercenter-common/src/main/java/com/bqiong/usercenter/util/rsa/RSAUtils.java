package com.bqiong.usercenter.util.rsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.exception.BizException;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月25日 上午10:55:08 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月25日 上午10:55:08 	修改人：
 *  	描述	:
 ***********************************************************
 */
public class RSAUtils 
{
	private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
	
	private String privateKey;

	public String getPrivateKey() 
	{
		return privateKey;
	}

	public void setPrivateKey(String privateKey) 
	{
		this.privateKey = privateKey;
	}
	
	public String decypt(String value)
	{
		try 
		{
			return new String(new String(RSACoder.decryptByPrivateKey(RSACoder.decryptBASE64(value), privateKey)));
		
		} catch (Exception e)
		{
			log.error("解密消息失败！", e);
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
	}
	
}
