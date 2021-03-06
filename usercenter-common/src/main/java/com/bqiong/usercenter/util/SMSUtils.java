package com.bqiong.usercenter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.sms.client.message.api.SMSSendApi;
import com.api.sms.common.exception.SmsFailedException;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.MobileCodeTextEnum;
import com.bqiong.usercenter.constants.TraditionChineseMobileCodeTextEnum;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;

public class SMSUtils {

	protected static final Logger logger = LoggerFactory.getLogger(SMSUtils.class);

	private static final String SMS_CODE = "regverifyCode";
	
	private static final String SMS_CONTENT = "content";
	
	public static String cacheSmsToken(String mobile, String clientId, String bizType)
	{
		String smsToken = RandomStringUtil.generateVerifyToken();
		String smsTokenKey = UserCenterConstants.REIDS_SMS_TOKEN_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile;
		RedisUtil.set(smsTokenKey, smsToken, UserCenterConstants.SMS_EMAIL_TOKEN_TIME);
		logger.info("生成smsToken,smsToke=" + smsToken);
		return smsToken;
	}
	
	/**
	 * 
	 *  函数名称 : validateSmsToken
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param mobile
	 *  	@param smsToken
	 *  	@param clientId
	 *  	@param bizType
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月12日 下午3:32:38	修改人：  
	 *  	描述	：
	 *
	 */
    public static void validateSmsToken(String mobile, String smsToken, String clientId, String bizType) {
        if (StringUtils.isBlank(smsToken)) {
            logger.error("短信验证码smsToken为空" + ",mobile=" + mobile + ",clientId=" + clientId + ",bizType=" + bizType);
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
        }
        String smsTokenKey = UserCenterConstants.REIDS_SMS_TOKEN_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile;
        logger.info("smsTokenKey:{}", smsTokenKey);
        String val = RedisUtil.get(smsTokenKey);
        if (StringUtils.isEmpty(val)) {
            logger.info("短信验证码smsToken已过期" + ",mobile=" + mobile + ",smsToken=" + smsToken + ",bizType=" + bizType + ",clientId=" + clientId);
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
        }
        if (!smsToken.equals(val)) {
            logger.error("短信验证码smsToken错误" + ",mobile=" + mobile + ",smsToken=" + smsToken + ",bizType=" + bizType + ",clientId=" + clientId);
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
        }
        RedisUtil.del(smsTokenKey);
    }

	/**
	 * 生成手机验证码
	 * 
	 * @param mobile
	 * @param clientId TODO
	 * @param bizType TODO
	 * @param request
	 * @throws SmsFailedException
	 * @throws PayOrderException
	 */
	public static void generateAndSaveVerifyCode(String countryCode,String mobile, String ip, String clientId, String bizType) {
		final String mobile_final = countryCode + mobile;
		// 判断手机号码+业务+第三方
		String mobileCache = RedisUtil.get(UserCenterConstants.REDIS_SMS_EXPIRE_PREFIX + clientId + ":" + bizType + ":" + mobile_final);

		if (StringUtils.isNotBlank(mobileCache)) 
		{
			logger.info("mobile:" + mobile + "一分钟内发送短信,暂停发送");
			throw new BizException(ErrorCode.SEND_SMS_ONCE_MIN.getCode());
		}
		String verifyCode = "";
		String format = "";
		// 验证码
		verifyCode = generateVerifyCode();
		logger.info("生成验证码->手机号码：{},验证码:{}", mobile_final, verifyCode);
		String smsContentKey = UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final + ":" + SMS_CONTENT;
		RedisUtil.setex(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final, 120, mobile);
		RedisUtil.setex(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final + ":" + SMS_CODE, 120, verifyCode);
		RedisUtil.setex(smsContentKey, 120, verifyCode + "$");
		RedisUtil.setex(UserCenterConstants.REDIS_SMS_EXPIRE_PREFIX + clientId + ":" + bizType + ":" + mobile_final, 60, verifyCode);
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		format = df.format(new Date());
		int busId = 65;
		String mobileCodeText = "";
		//国内
		if (countryCode.startsWith("86")) {
			mobileCodeText = MobileCodeTextEnum.getFromKey(bizType).getText();
		//台地区
		}else if(countryCode.equals("886")){
			busId = 87;
			mobileCodeText = TraditionChineseMobileCodeTextEnum.getFromKey(bizType).getText();
			if (mobile.startsWith("0")) {
				mobile = mobile.substring(1);
			}
			mobile = countryCode + mobile;
		}
		//国外，港澳
		else{
			busId = 87;
			mobileCodeText = TraditionChineseMobileCodeTextEnum.getFromKey(bizType).getText();
			mobile = mobile_final;
		}
		try 
		{
			mobileCodeText = String.format(mobileCodeText, verifyCode);
			logger.info("验证码内容-->mobileCodeText:{}",mobileCodeText);
//			SMSSendApi.sendSmsSignleCode(busId, mobile, mobileCodeText, verifyCode, format, 5);
			String signType = "5";
			FormBody body = new FormBody.Builder()
				.add("busid", busId + "")
				.add("receiver",mobile)
				.add("content", mobileCodeText)
				.add("requestTime", format)
				.add("signType", signType)
				.add("key", MD5Util.MD5Encode("ac1772b79fbb1199", "utf-8"))
				.build();
			
			Request req = new Request.Builder()
			.url("http://sms.qbao.com/api/sms/extranet/smsSend")
			.post(body)
			.build();
			Response resp = MyOkHttpClient.getInstance().exec(req);
			//请求成功
			if(resp.isSuccessful()){
				logger.info("发送验证码->请求结果:{},手机号码:{},返回内容:{}", resp.code(), mobile_final , resp.body().string());
			}else{
				logger.info("发送验证码->请求结果:{},手机号码:{},返回内容:{}",resp.code(),mobile_final,resp.body().string());
				throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
			}
//			MyOkHttpClient.getInstance().exec(req, new Callback() {
//				@Override
//				public void onResponse(Call call, Response response) throws IOException {
//					
//					if (response.isSuccessful()) {
//						logger.info("发送验证码->请求结果:{},手机号码:{},返回内容:{}", response.code(), mobile_final , response.body().string());
//					}else{
//						logger.info("发送验证码->请求结果:{},手机号码:{},返回内容:{}",response.code(),mobile_final,response.body().string());
//					}
//				}
//				
//				@Override
//				public void onFailure(Call call, IOException e) {
//					logger.error("手机号码：{},发送验证码失败：{}", mobile_final ,e.getMessage());
//				}
//			});
			
//			logger.info("手机号码：" +  mobile + ",短信验证码发送成功。验证码为：" + verifyCode);
		}catch (Exception e) {
			logger.error("----->验证码发送失败,手机号码：{},生成验证码为:{}" ,mobile, verifyCode, e);
		}
	}

	/**
	 * 验证手机验证码
	 * 
	 * @param mobile
	 * @param clientId TODO
	 * @param bizType TODO
	 * @param request
	 * @throws SmsFailedException
	 * @throws PayOrderException
	 */
	public static void verifySmsCode(String countryCode,String mobile, String verifyCode, String clientId, String bizType) 
	{
		if(StringUtils.isBlank(verifyCode))
		{
			logger.error("参数mobileCode为空");
			throw new BizException(ErrorCode.VERIFY_CODE_NULL.getCode());
		}
		String mobile_final = countryCode + mobile;
		String value = RedisUtil.get(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final + ":" +  SMS_CONTENT);
		
		logger.info("验证验证码的手机号：{},验证码为：{},查询到的验证码为：{}",mobile_final,verifyCode,value);
		logger.info("查询验证码key:{},value:{}",UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final + ":" +  SMS_CONTENT,value);
		if (StringUtils.isBlank(value)) 
		{
			logger.error("mobileCode错误");
			throw new BizException(ErrorCode.VERIFY_CODE_ERROR.getCode());
		}

		String valueArray[] = value.split("\\$");
		if(!valueArray[0].equals(verifyCode))
		{
			logger.error("mobileCode错误");
			throw new BizException(ErrorCode.VERIFY_CODE_ERROR.getCode());
		}
		RedisUtil.del(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final);
		RedisUtil.del(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final + ":" + SMS_CODE);
		RedisUtil.del(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile_final + ":" + SMS_CONTENT);
		RedisUtil.del(UserCenterConstants.REDIS_SMS_EXPIRE_PREFIX + clientId + ":" + bizType + ":" + mobile_final);
	}
	
	/**
	 * 
	 *  函数名称 : getSmsCodeByIds
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param mobile
	 *  	@param verifyCode
	 *  	@param clientId
	 *  	@param bizType
	 *
	 *  修改记录：
	 *  	日期 ：2016年7月28日 下午12:26:21	修改人：  nz
	 *  	描述	：根据mobile,clientId,biztype，获取手机验证码,目前仅测试时使用
	 *
	 */
	public static String getSmsCodeByMobile(String mobile, String clientId, String bizType) 
	{
		String value = RedisUtil.get(UserCenterConstants.REIDS_SMS_KEY_PREFIX + clientId + ":" + bizType + ":" + mobile + ":" +  SMS_CONTENT);
		if (StringUtils.isBlank(value)) 
		{
			logger.error("未进行验证码下发操作请求，请先进行验证码下发请求！");
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}

		String valueArray[] = value.split("\\$");
		if(StringUtils.isBlank(valueArray[0]))
		{
			logger.error("mobileCode错误");
			throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
		}
		return valueArray[0];
	}

	private static String generateVerifyCode() 
	{
		return String.valueOf(new Random().nextInt(899999) + 100000);
	}
	
	public static void main(String[] args) {
		System.out.println(String.format("","1234"));
	}

}
