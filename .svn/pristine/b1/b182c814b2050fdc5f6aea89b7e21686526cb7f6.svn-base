package com.qbao.store.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.bqiong.usercenter.util.aes.AESUtils;
import com.bqiong.usercenter.util.pbkdf2.Pbkdf2Util;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.PasswordService;
import com.qbao.store.util.BizUtil;
import com.qbao.store.util.MD5Util;
import com.qbao.store.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ********************************************************** 内容摘要 ：
 * <p/>
 * <p/>
 * 作者 ：94841 创建时间 ：2016年4月29日 下午2:18:48 当前版本号：v1.0 历史记录 : 日期 : 2016年4月29日
 * 下午2:18:48 修改人： 描述 :
 * **********************************************************
 */
@Controller
@RequestMapping("/user")
public class PasswordController extends BaseController {
    @Autowired
    PasswordService passwordService;

    /**
     * 重置web密码
     * 参数:passport=13072759388&password=841B7F5C7465EB7BA2ADB6DBA287CB9E&token=123321&clientId=&buId=DOME002
     */
    @OperateType(bizType = BizType.resetPassword)
    @RequestMapping("/resetPassword4Web")
    @ResponseBody
    public String resetPassword4Web(RequestData requestData, HttpServletRequest request) {
        try {
            String token = request.getParameter("token");
            validateBuId(requestData.getBuId());
            // 请求来源是app_store时候，没有clientid，使用buId标识
            BizUtil.validateClientId(requestData);
            PassportType type = validatePassport(requestData.getCountryCode(), requestData.getPassport());
            if (type == PassportType.mobile) {
                SMSUtils.validateSmsToken(requestData.getPassport(), token, requestData.getClientId(), BizType.resetPassword.name());
            }
            if (type == PassportType.email) {
                MailUtil.validateEmailToken(requestData.getPassport(), token, requestData.getClientId(), BizType.resetPassword.name());
            }
            validatePassword(requestData.getPassword(), isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            UserEntity entity = passwordService.resetPassword(requestData, type);
            if (entity == null) {
                log.error("重置密码失败,mobile:{},smsToken:{},buId:{},clientId:{}", requestData.getPassword(), token, requestData.getBuId(), requestData.getClientId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            TokenUtil.destoryToken(requestData.getClientId(), entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("mobile=" + requestData.getMobile(), e);
        }
    }

    /**
     * 函数名称 : resetPassword 功能描述 : 参数及返回值说明：
     * 重置密码(通过手机)
     *
     * @return 修改记录： 日期 ：2016年4月29日 下午3:04:48 修改人： 描述 ：
     * @paramrequestData
     */
    @OperateType(bizType = BizType.resetPassword)
    @RequestMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(RequestData requestData) {
        try {
            validateBuId(requestData.getBuId());
            BizUtil.validateClientId(requestData);
            PassportType type = validatePassport(requestData.getCountryCode(), requestData.getMobile());
            if (type != PassportType.mobile) {
                log.error("参数mobile类型错误, passport:{}", requestData.getMobile());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.PASSPORT_ILLEGLE));
            }
            validatePassword(requestData.getPassword(), isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            SMSUtils.validateSmsToken(requestData.getMobile(), requestData.getSmsToken(), requestData.getClientId(), BizType.resetPassword.name());
            UserEntity entity = passwordService.resetPassword(requestData, type);
            if (entity == null) {
                log.error("重置密码失败" + ",mobile=" + requestData.getMobile() + ",smsToken =" + requestData.getSmsToken() + ",buId=" + requestData.getBuId() + ",clientId=" + requestData.getClientId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.PASSPORT_NOT_EXIST));
            }
            TokenUtil.destoryToken(requestData.getClientId(), entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("mobile=" + requestData.getMobile() + ",password=" + requestData.getPassword() + ",buId=" + requestData.getBuId() + ",clientId=" + requestData.getClientId(), e);
        }
    }

    /**
     * 重置密码(通过邮箱)
     *
     * @param requestData
     * @return
     */
    @OperateType(bizType = BizType.resetPassword)
    @RequestMapping("/resetPasswordEmail")
    @ResponseBody
    public String resetPasswordEmail(RequestData requestData) {
        try {
            validateBuId(requestData.getBuId());
            // 请求来源是app_store时候，没有clientid，使用buId标识
            BizUtil.validateClientId(requestData);
            PassportType type = validatePassport(null, requestData.getEmail());
            if (type != PassportType.email) {
                log.error("参数mobile类型错误, passport = " + requestData.getEmail());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.PASSPORT_ILLEGLE));
            }
            validatePassword(requestData.getPassword(), isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            MailUtil.validateEmailToken(requestData.getEmail(), requestData.getEmailToken(), requestData.getClientId(), BizType.resetPassword.name());
            UserEntity entity = passwordService.resetPassword(requestData, type);
            if (entity == null) {
                log.error("重置密码失败" + ",mobile=" + requestData.getEmail() + ",smsToken =" + requestData.getEmailToken() + ",buId=" + requestData.getBuId() + ",clientId=" + requestData.getClientId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.PASSPORT_NOT_EXIST));
            }
            TokenUtil.destoryToken(requestData.getClientId(), entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("mobile=" + requestData.getEmail() + ",password=" + requestData.getPassword() + ",buId=" + requestData.getBuId() + ",clientId=" + requestData.getClientId(), e);
        }
    }

    /**
     * 函数名称 : checkOldPwd 功能描述 : 参数及返回值说明：
     */
    @OperateType(bizType = BizType.modifyPassword)
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public String modifyPassword(RequestData requestData) {
        log.info("accessToken:{}", requestData.getAccessToken());
        try {
            String userVal = RedisUtil.hget(RedisContant.USER_BASIC_IDX, requestData.getUserId());
            UserEntity userEntity = null;
            if (StringUtils.isBlank(userVal) || (userEntity = JSONObject.parseObject(userVal, UserEntity.class)) == null) {
                log.error("用户不存在-> userId:{}", requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            //加盐
            TokenUtil.validateTokenSign(requestData.getAccessToken(), userEntity.getSalt());
            validatePasswordWithType(requestData.getPasswordOld(), PasswordType._old, isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            validatePasswordWithType(requestData.getPasswordNew(), PasswordType._new, isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            //数据库密码
            String md5Password = MD5Util.getMD5String(requestData.getPasswordOld() + userEntity.getSalt());
            String pbkdf2Password = Pbkdf2Util.encrypt(AESUtils.decrypt(requestData.getPasswordOld()), userEntity.getSalt());
            if (!userEntity.getPassword().equals(md5Password) && !userEntity.getPassword().equals(pbkdf2Password)) {
                throw new BizException(ErrorCode.PASSWORD_WRONG.getCode(), ErrorCode.PASSWORD_WRONG.getMessage());
            }
            if (requestData.getPasswordNew().equals(requestData.getPasswordOld())) {
                log.error(ErrorCode.SAME_WITH_OLD_PWD.getMessage());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SAME_WITH_OLD_PWD));
            }
            PassportType type = null;
            String passport = "";
            if (StringUtils.isNotBlank(userEntity.getEmail())) {
                type = PassportType.email;
                passport = userEntity.getEmail();
            } else if (StringUtils.isNotBlank(userEntity.getMobile()) && !userEntity.getMobile().startsWith("100")) {
                type = PassportType.mobile;
                passport = userEntity.getMobile();
            } else {
                log.error("账号异常->即非手机又非邮箱");
                throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
            }
            requestData.setPassport(passport);
            requestData.setPassword(requestData.getPasswordNew());
            UserEntity entity = passwordService.resetPassword(requestData, type);
            if (entity == null) {
                log.error("修改密码失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            TokenUtil.destoryToken(requestData.getClientId(), entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            if (e instanceof BizException && ErrorCode.PASSWORD_WRONG == ErrorCode.getFromKey(((BizException) e).getErrorCode())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.OLD_PWD_ERROR));
            }
            return handleException("accesstoken=" + requestData.getAccessToken() + ",buId=" + requestData.getBuId() + ",clientId=" + requestData.getClientId(), e);
        }
    }

}
