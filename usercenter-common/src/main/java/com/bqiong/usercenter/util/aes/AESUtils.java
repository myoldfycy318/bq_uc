package com.bqiong.usercenter.util.aes;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月25日 下午1:27:58 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月25日 下午1:27:58 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
public class AESUtils
{
	private static String passKey;
	
	public static String encrypt(String content)
	{
		return AESCoder.getEncryptResult(content, passKey);
	}
	
	public static String decrypt(String content)
	{
		return AESCoder.getDecryptResult(content, passKey);
	}

	public static String getPassKey() {
		return passKey;
	}
	

	public static void setPassKey(String passKey) {
		AESUtils.passKey = passKey;
	}
}
