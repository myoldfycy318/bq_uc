package com.qbao.store.controller.newOpenPlatform;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.Risk;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.RandomStringUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by youmin on 2017/10/10.
 */
@Controller
@RequestMapping("/newOpen")
public class NewOpenVerifyCodeController extends BaseController {

    /**
     * 函数名称 : getCode
     * 功能描述 : 获取验证码
     * 参数及返回值说明：
     *
     * @param passport 账号
     * @param userIp   用户ip
     * @param bizType
     * @param isInland 是否为国内邮箱，默认是
     * @return 修改记录：
     */
    @RequestMapping("/getCode")
    @ResponseBody
    @Risk
    public String getCode(String bizType, String passport, String userIp,
                          @RequestParam(value = "isInland", defaultValue = "0") int isInland,
                          @RequestParam(value = "isNewOpen", defaultValue = "0") int isNewOpen) {
        try {
            validateIp(userIp);
            validateBizType(bizType);
            validatePassport4OpenPlatform(passport);
            PassportType type = PassportType.validatePassport(null, passport);
            String userValue = "";
            switch (BizType.getFromKey(bizType)) {
                case register:
                    if (type == PassportType.mobile) {
                        userValue = RedisUtil.hget(RedisContant.MOBILE_IDX, passport);
                        if (StringUtils.isNotBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_EXIST.getMessage());
                            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.OPEN_PASSPORT_EXIST));
                        }

                        SMSUtils.generateAndSaveVerifyCode(UserCenterConstants.DEFAULT_COUNTRY_CODE, passport, userIp, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        return JSONObject.toJSONString(BaseResponse.success());
                    }
                    if (type == PassportType.email) {
                        userValue = RedisUtil.hget(RedisContant.EMAIL_IDX, passport);
                        if (StringUtils.isNotBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_EXIST.getMessage());
                            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.OPEN_PASSPORT_EXIST));
                        }
                        //新开放平台邮箱验证码直接接口返回(2017-9-19 by_zhangshanmin)
                        Map<String, String> map = getEmailVerifyCode(bizType, passport, 1 == isNewOpen, isInland == 0);
                        return JSONObject.toJSONString(BaseResponse.success(map));
                    }
                    break;

                case resetPassword:
                    if (type == PassportType.mobile) {
                        userValue = RedisUtil.hget(RedisContant.MOBILE_IDX, passport);
                        if (StringUtils.isBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getMessage());
                            throw new BizException(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getCode());
                        }

                        SMSUtils.generateAndSaveVerifyCode(UserCenterConstants.DEFAULT_COUNTRY_CODE, passport, userIp, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        return JSONObject.toJSONString(BaseResponse.success());
                    }
                    if (type == PassportType.email) {
                        userValue = RedisUtil.hget(RedisContant.EMAIL_IDX, passport);
                        if (StringUtils.isBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getMessage());
                            throw new BizException(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getCode());
                        }
                        //新开放平台邮箱验证码直接接口返回(2017-9-19 by_zhangshanmin)
                        Map<String, String> map = getEmailVerifyCode(bizType, passport, 1 == isNewOpen, isInland == 0);
                        return JSONObject.toJSONString(BaseResponse.success(map));
                    }
                    break;
                default:
                    break;
            }
            log.error("非法的业务类型！");
            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
        } catch (Exception e) {
            return handleException("mobile=" + passport + ", userIp=" + userIp + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }

    /**
     * 新开放平台邮箱验证码直接接口返回
     *
     * @param bizType
     * @param passport
     * @return
     */
    private Map<String, String> getEmailVerifyCode(String bizType, String passport, boolean isNewOpen, boolean isInland) {
        Map<String, String> map = new HashMap<>();
        if (isNewOpen) {  //新开放平台邮箱验证码直接接口返回
            String code = RandomStringUtil.generateCode(UserCenterConstants.EMAIL_CODE_LENGTH);
            String emailCodeKey = UserCenterConstants.REDIS_EMAIL_CODE_KEY_PREFIX + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM
                    + ":" + BizType.getFromKey(bizType).name() + ":" + passport;
            map.put("verifyCode", code);
            RedisUtil.set(emailCodeKey, code, UserCenterConstants.EMAIL_CODE_EXPIRE_TIME);
        } else {
            MailUtil.sendMail(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name(), isInland);
        }
        return map;
    }

    /**
     * 函数名称 : verifyCode
     */
    @RequestMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(String bizType, String passport, String userIp, String code, HttpServletRequest request) {
        try {
            validateIp(userIp);
            validateBizType(bizType);
            validatePassport4OpenPlatform(passport);
            PassportType type = validatePassport(null, passport);

            Map<String, String> result = new HashMap<String, String>();
            String userValue = "";
            switch (BizType.getFromKey(bizType)) {
                case register:
                    if (type == PassportType.mobile) {
                        userValue = RedisUtil.hget(RedisContant.MOBILE_IDX, passport);
                        if (StringUtils.isNotBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_EXIST.getMessage());
                            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.OPEN_PASSPORT_EXIST));
                        }

                        SMSUtils.verifySmsCode(UserCenterConstants.DEFAULT_COUNTRY_CODE, passport, code, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        String smsToken = SMSUtils.cacheSmsToken(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        result.put(UserCenterConstants.VERIFY_CODE_TOKEN, smsToken);
                        return JSONObject.toJSONString(buildResponse(result));
                    }
                    if (type == PassportType.email) {
                        userValue = RedisUtil.hget(RedisContant.EMAIL_IDX, passport);
                        if (StringUtils.isNotBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_EXIST.getMessage());
                            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.OPEN_PASSPORT_EXIST));
                        }

                        MailUtil.verifyCode(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name(), code);
                        String emailToken = MailUtil.cacheEmailToken(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        result.put(UserCenterConstants.VERIFY_CODE_TOKEN, emailToken);
                        return JSONObject.toJSONString(buildResponse(result));
                    }
                    break;

                case resetPassword:
                    if (type == PassportType.mobile) {
                        userValue = RedisUtil.hget(RedisContant.MOBILE_IDX, passport);
                        if (StringUtils.isBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getMessage());
                            throw new BizException(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getCode());
                        }

                        SMSUtils.verifySmsCode(UserCenterConstants.DEFAULT_COUNTRY_CODE, passport, code, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        String smsToken = SMSUtils.cacheSmsToken(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        result.put(UserCenterConstants.VERIFY_CODE_TOKEN, smsToken);
                        return JSONObject.toJSONString(buildResponse(result));
                    }
                    if (type == PassportType.email) {
                        userValue = RedisUtil.hget(RedisContant.EMAIL_IDX, passport);
                        if (StringUtils.isBlank(userValue)) {
                            log.error(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getMessage());
                            throw new BizException(ErrorCode.OPEN_PASSPORT_NOT_EXIST.getCode());
                        }

                        MailUtil.verifyCode(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name(), code);
                        String emailToken = MailUtil.cacheEmailToken(passport, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.getFromKey(bizType).name());
                        result.put(UserCenterConstants.VERIFY_CODE_TOKEN, emailToken);
                        return JSONObject.toJSONString(buildResponse(result));
                    }
                    break;

                default:
                    break;
            }
            log.error("非法的业务类型！" + "bizType = " + bizType);
            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
        } catch (Exception e) {
            return handleException("mobile=" + passport + ",code=" + code + ", userIp=" + userIp + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }
}
