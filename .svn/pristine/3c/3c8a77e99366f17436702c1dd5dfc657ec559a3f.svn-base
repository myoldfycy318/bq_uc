package com.qbao.store.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.BUEnum;
import com.bqiong.usercenter.constants.BizType;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.aes.AESUtils;
import com.qbao.store.util.TokenUtil;

/**
 * Created by hunsy on 2017/5/5.
 */
public abstract class IBaseController {

    protected Logger logger = LoggerFactory.getLogger(IBaseController.class);

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    protected String handleException(Throwable e) {
        String resp = null;
        ErrorCode errorCode;
        logger.error("err:{}", e);
        if (e instanceof BizException) {
            errorCode = ErrorCode.getFromKey(((BizException) e).getErrorCode());
        } else {
            errorCode = ErrorCode.SYSTEM_EXCEPTION;
        }
        if (errorCode != null) {
            resp = JSONObject.toJSONString(BaseResponse.fail(errorCode));
        }
        return resp;
    }

    /**
     * 验证BU ID
     *
     * @param buId
     */
    protected BUEnum validateBuId(String buId) throws BizException {
        BUEnum buEnum = BUEnum.getFromKey(buId);
        if (buEnum == null) {
            logger.error("buid不存在->buid:{}", buId);
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        return buEnum;
    }

    /**
     * 验证 clientId
     *
     * @param clientId
     */
    protected void validateClientId(String clientId) throws BizException {
        if (StringUtils.isEmpty(clientId)) {
            logger.error("clientId不能为空");
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
    }

    /**
     * 验证国内的手机号
     *
     * @return
     */
    public void validateMobileInner(String mobile) throws BizException {
        if (StringUtils.isEmpty(mobile)) {
            logger.error("账号为空");
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }
        String regMobile = "^[1][3-8]\\d{9}$";
        if (!mobile.matches(regMobile)) {
            logger.error("国内手机号码格式错误->mobile:{}", mobile);
            throw new BizException(ErrorCode.PASSPORT_ILLEGLE.getCode(), ErrorCode.PASSPORT_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证国外的手机号
     *
     * @return
     */
    public void validateMobileAbroad(String mobile) throws BizException {
        if (StringUtils.isEmpty(mobile)) {
            logger.error("账号为空");
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }
        String otherMobile = "^[0-9]*$";
        if (!mobile.matches(otherMobile)) {
            logger.error("国外手机号码格式错误->mobile:{}", mobile);
            throw new BizException(ErrorCode.PASSPORT_ILLEGLE.getCode(), ErrorCode.PASSPORT_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证国内姓名
     */
    public void validateNameInner(String name) throws BizException {
        if (StringUtils.isEmpty(name)) {
            logger.error("姓名为空");
            throw new BizException(ErrorCode.NAME_NOT_NULL.getCode(), ErrorCode.NAME_NOT_NULL.getMessage());
        }
        String nameReg = "^[\u4E00-\u9FFF]+$";
        if (name.length() < 2 || name.length() > 6 || !name.matches(nameReg)) {
            logger.error("姓名只能输入2-6个汉字->name:{}", name);
            throw new BizException(ErrorCode.NAME_ILLEGLE.getCode(), ErrorCode.NAME_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证身份证正则
     *
     * @param card
     * @return
     */
    protected void validateCardInner(String card) throws BizException {
        String reg15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String reg19 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

        String str = "{11:\"北京\",12:\"天津\",13:\"河北\",14:\"山西\",15:\"内蒙古\",21:\"辽宁\",22:\"吉林\",23:\"黑龙江\",31:\"上海\",32:\"江苏\",33:\"浙江\",34:\" 安徽\",35:\"福建\",36:\"江西\",37:\"山东\",41:\"河南\",42:\"湖北\",43:\"湖南\",44:\"广东\",45:\"广西\",46:\"海南\",50:\"重庆\",51:\"四川\",52:\"贵州\" ,53:\"云南\",54:\"西藏\",61:\"陕西\",62:\"甘肃\",63:\"青海\",64:\"宁夏\",65:\"新疆\",71:\"台湾\",81:\"香港\",82:\"澳门\",91:\"国外\"}";

        if (card.length() != 15 && card.length() != 18) {
            logger.error("card位数不对->card:{}", card);
            throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode(), ErrorCode.IDCARD_ILLEGLE.getMessage());
        }
        boolean flag = false;
        if (card.length() == 15) {
            flag = card.matches(reg15);
        }

        if (card.length() == 18) {
            flag = card.toUpperCase().matches(reg19);
        }
        if (!flag) {
            logger.error("身份证格式不正确->card:{}", card);
            throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode(), ErrorCode.IDCARD_ILLEGLE.getMessage());
        }

        JSONObject obj = JSON.parseObject(str);
        if (StringUtils.isEmpty(obj.getString(card.substring(0, 2)))) {
            logger.error("非法地区->card:{}", card);
            throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode(), ErrorCode.IDCARD_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证邮箱格式
     *
     * @param email
     */
    protected void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            logger.error("邮箱为空");
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }
        String regEmail = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        String regEmail4Pubish = "^.*@.*$";
        if (!email.matches(regEmail) && !email.matches(regEmail4Pubish)) {
            logger.error("邮箱格式错误->email:{}", email);
            throw new BizException(ErrorCode.PASSPORT_ILLEGLE.getCode(), ErrorCode.PASSPORT_ILLEGLE.getMessage());
        }
    }

    /**
     * 验证IP地址
     *
     * @param userIp
     */
    protected void validateIp(String userIp) {
        String each = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regIP = "^" + each + "\\." + each + "\\." + each + "\\." + each + "$";
        if (StringUtils.isBlank(userIp)) {
            logger.error(ErrorCode.IP_NULL.getMessage());
        } else if (!userIp.matches(regIP)) {
            logger.error(ErrorCode.IP_ILLEGLE.getMessage() + ",userIp =" + userIp);
        }
    }

    /**
     * 验证BizType
     *
     * @param bizType
     * @return
     * @throws Exception
     */
    protected BizType validateBizType(String bizType) throws Exception {
        if (StringUtils.isBlank(bizType)) {
            logger.error("bizType为空!");
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        BizType bizTypeEnum = BizType.getFromKey(bizType);

        if (bizTypeEnum == null) {
            logger.error("非法的业务类型！");
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        return bizTypeEnum;
    }

    /**
     * 验证账号
     *
     * @param countryCode
     * @param passport
     * @return
     */
    protected PassportType validatePassport(String countryCode, String passport) {

        if (StringUtils.isEmpty(passport)) {
            logger.info("passport is null");
            throw new BizException(ErrorCode.PASSPORT_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }

        if (passport.contains("@")) {
            // 验证邮箱
            validateEmail(passport);
            return PassportType.email;
        }

        // 所有的游客，都作为手机号
        if (passport.startsWith("100")) {
            return PassportType.mobile;
        }

        // 国内的手机号码
        if (countryCode == null || countryCode.equals("86")) {
            validateMobileInner(passport);
            return PassportType.mobile;
        } else {
            validateMobileAbroad(passport);
            return PassportType.mobile;
        }
    }

    /**
     * 是否需要加密
     *
     * @param fromKey
     */
    protected boolean isAESContent(BUEnum fromKey) {
        return true;
    }

    /**
     * 验证，解密密码
     *
     * @param password
     * @param isAESPassword
     * @throws Exception
     */
    protected String validatePassword(String password, boolean isAESPassword) throws BizException {
        if (StringUtils.isBlank(password)) {
            logger.error("参数密码为空");
            throw new BizException(ErrorCode.PASSWORD_NULL.getCode(), ErrorCode.PASSPORT_NULL.getMessage());
        }
        // 解密密码
        if (isAESPassword) {
            password = AESUtils.decrypt(password);
        }
        // 密码长度不够
        if (UserCenterConstants.PASSWORD_MIN_LENGTH > password.length()
                || UserCenterConstants.PASSWORD_MAX_LENGTH < password.length()) {
            logger.error(ErrorCode.PASSWORD_ILLEGLE.getMessage());
            throw new BizException(ErrorCode.PASSWORD_ILLEGLE.getCode(), ErrorCode.PASSWORD_ILLEGLE.getMessage());
        }
        // 包含非法字符
        for (int i = 0; i < password.length(); i++) {
            if (UserCenterConstants.PASSWORD_ALLOW_CHAR.indexOf(password.charAt(i)) == -1) {
                logger.error(ErrorCode.PASSWORD_ILLEGLE.getMessage() + ",非法字符：" + password.charAt(i));
                throw new BizException(ErrorCode.PASSWORD_ILLEGLE.getCode(), ErrorCode.PASSWORD_ILLEGLE.getMessage());
            }
        }
        return password;
    }

    /**
     * 缓存Token
     *
     * @param clientId
     * @param userId
     * @param salt
     * @return
     */
    protected String cacheToken(String clientId, String userId, String salt) {
        String tokenKey = UserCenterConstants.REDIS_TOKEN_KEY_PREFIX + clientId + ":" + userId;
        String accessToken = RedisUtil.get(tokenKey);
        if (StringUtils.isNotBlank(accessToken)){
            RedisUtil.expire(tokenKey, UserCenterConstants.TOKEN_EXPIRE_TIME);
            return accessToken;
        }
        accessToken = TokenUtil.generateAccessToken(clientId, userId, salt);
        if (StringUtils.isBlank(accessToken)) {
            logger.error(
                    "token生成失败->,clientId:{},,userId={},salt is null?,salt:{}",
                    clientId, userId, salt);
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
        }
        RedisUtil.set(tokenKey, accessToken, UserCenterConstants.TOKEN_EXPIRE_TIME);
        logger.info("tokenKey:{},accessToken:{}", tokenKey, accessToken);
        return accessToken;
    }

//	public static void main(String strs[]){
//
//		String str = "{11:\"北京\",12:\"天津\",13:\"河北\",14:\"山西\",15:\"内蒙古\",21:\"辽宁\",22:\"吉林\",23:\"黑龙江\",31:\"上海\",32:\"江苏\",33:\"浙江\",34:\" 安徽\",35:\"福建\",36:\"江西\",37:\"山东\",41:\"河南\",42:\"湖北\",43:\"湖南\",44:\"广东\",45:\"广西\",46:\"海南\",50:\"重庆\",51:\"四川\",52:\"贵州\" ,53:\"云南\",54:\"西藏\",61:\"陕西\",62:\"甘肃\",63:\"青海\",64:\"宁夏\",65:\"新疆\",71:\"台湾\",81:\"香港\",82:\"澳门\",91:\"国外\"}";
//		String card = "11";
//		JSONObject obj = JSON.parseObject(str);
//		String s = obj.getString(card.substring(0,2));
//		System.out.println(s);
//		if (StringUtils.isEmpty(s)){
////			logger.error("非法地区->card:{}", card);
////			throw new BizException(ErrorCode.IDCARD_ILLEGLE.getCode());
//		}
//	}

}
