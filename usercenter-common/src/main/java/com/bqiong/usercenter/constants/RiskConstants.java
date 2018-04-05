package com.bqiong.usercenter.constants;

public class RiskConstants 
{
	
	/**
	 * redis中根据passport维度风控的key
	 */
	public static final String REDIS_RISK_KEY = UserCenterConstants.REDIS_USERCENTER_APPLICATION + "risk:";
	
	/**
	 * redis缓存中验证码计数前缀 
	 */
	public static final String REDIS_CAPTCHA_COUNT_KEY_PREFIX = REDIS_RISK_KEY + "captcha_count:";
	
	/**
	 * redis中存放生成的captchakey、value对的key前缀
	 */
	public static final String REDIS_CAPTCHA_KEY_PREFIX = REDIS_RISK_KEY + "captcha_key:";
	/**
	 * redis中存放captchaKey的自增序列key
	 */
	public static final String REDIS_CAPTCHAKEY = REDIS_RISK_KEY + "captcha_key";
	
	/**
	 * redis中存放captcha的risked前缀
	 */
	public static final String REDIS_CAPTCHA_RISKED_PREFIX = REDIS_RISK_KEY + "captcha_risked:";

	
	/**
	 * redis中sms风控计数key
	 */
	public static final String REDIS_SMS_COUNT_KEY = REDIS_RISK_KEY + "sms_count:";
	
	/**
	 * redis中sms风控r记录key
	 */
	public static final String REDIS_SMS_RISKED_KEY = REDIS_RISK_KEY + "sms_risked:";
	/**
	 * REDIS中记录验证码结果
	 */
	public static final String RECORD_RESULT = "result";
	
	/**
	 * 记录次数
	 */
	public static final String RECORD_COUNT = "count";
	
	/**
	 * 验证码统计时间间隔
	 */
	public static final String CAPTCHA_COUNT_INTERVAL = "captchaCountInterval";
	
	/**
	 * 验证码风控时间间隔
	 */
	public static final String CAPTCHA_RISKED_INTERVAL = "captchaRiskedInterval";
	
	//验证码开关
	public static final String CAPTCHA_SWITCH = "captchaSwitch";
	
	public static final String CAPTCHA_LIMIT_COUNT = "captchaLimitCount";
	
	public static final String NEED_CAPTCHA = "needCaptcha";
	
	public static final String CAPTCHA = "captcha";
	
	public static final String CAPTCHA_KEY = "captchaKey";
	
	public static final String CAPTCHA_EXPIRE_TIME = "expireTime";
	
	//手机验证码开关
	public static final String SMS_SWITCH = "smsSwitch";
	
	//阈值
	public static final String SMS_LIMIT_COUNT = "smsLimitCount";
	
	//手机验证码统计时间区间
	public static final String SMS_COUNT_INTERVAL = "smsCountInterval";
	
	//risk有效时间
	public static final String SMS_RISKED_INTERVAL = "smsRiskedInterval";
	
	//手机验证码结果码
	public static final String SMS_RISK_RESULT = "smsRiskResult";
	
	//=================================默认值区=================================================//
	public static final String SWITCH_ON = "on";
	
	public static final String SWITCH_OFF = "off";
	
	public static final String DEF_SWITCH_VALUE = "on";
	
	public static final String DEF_CAPTCHA_LIMIT_COUNT = "3";
	
	//统计时间区间2分钟，单位秒
	public static final String DEF_CAPTCHA_COUNT_INTERVAL = "120";
	
	//风控有效时间30分钟，单位秒
	public static final String DEF_CAPTCHA_RISKED_INTERVAL = "1800";
	
	//redis中验证码有效时间，单位秒
	public static final String DEF_CAPTCHA_EXPIRE_TIME = "120";
	
	//手机验证码默认风控阈值
	public static final String DEF_SMS_LIMIT_COUNT = "5"; 
	
	//统计时间区间24小时
	public static final String DEF_SMS_COUNT_INTERVAL = "86400";
	
	//风控有效时间24小时
	public static final String DEF_SMS_RISKED_INTERVAL = "86400";
	
}
