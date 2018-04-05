package com.qbao.store.controller.newOpenPlatform;

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
import com.qbao.store.service.LoginService;
import com.qbao.store.service.PasswordService;
import com.qbao.store.util.MD5Util;
import com.qbao.store.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ym on 2017/10/10.
 */
@Controller
@RequestMapping("/newOpen")
public class NewOpenPasswordController extends BaseController {
    @Autowired
    PasswordService passwordService;

    @Autowired
    LoginService loginService;

    /**
     * 函数名称 : resetPassword
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @return 修改记录：
     * 日期 ：2016年4月29日 下午3:04:48	修改人：
     * 描述	：
     */
    @OperateType(bizType = BizType.resetPassword)
    @RequestMapping("/resetPassword")
    @ResponseBody
    //String passport, String password, String confirmPassword, String verifyToken,
    public String resetPassword(RequestData requestData, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(requestData.getPassport());
            PassportType type = validatePassport(null, requestData.getPassport());
            switch (type) {
                case mobile:
                    SMSUtils.validateSmsToken(requestData.getPassport(), requestData.getVerifyToken(), UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.resetPassword.name());
                    break;

                case email:
                    MailUtil.validateEmailToken(requestData.getPassport(), requestData.getVerifyToken(), UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.resetPassword.name());
                    break;
                default:
                    break;
            }
            validatePassword(requestData.getPassword(), true);
            validatePassword(requestData.getConfirmPassword(), true);

            if (!requestData.getPassword().equals(requestData.getConfirmPassword())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.CONFIRM_RESET_NOT_SAME));
            }
            UserEntity entity = passwordService.resetPassword(requestData, type);
            if (entity == null) {
                log.error("重置密码失败" + ",passport=" + requestData.getPassport() + ",verifyToken =" + requestData.getVerifyCode() + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM);
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            TokenUtil.destoryToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("passport=" + requestData.getPassport() + ",password=" + requestData.getPassword() + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }


    /**
     * 函数名称 : checkOldPwd
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param request
     * @return 修改记录：
     * 日期 ：2016年5月4日 上午10:08:28	修改人：
     * 描述	：
     */
    @OperateType(bizType = BizType.resetPassword)
    @RequestMapping("/modifyPassword")
    @ResponseBody
    //String passport, String passwordOld, String passwordNew,
    public String modifyPassword(RequestData requestData, HttpServletRequest request) {
        try {
            PassportType type = validatePassport(null, requestData.getPassport());
            if (type == null) {
                log.error("参数passport类型错误, passport = " + requestData.getPassport());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.PASSPORT_ILLEGLE));
            }
            String redis_key = "";
            switch (type) {
                case mobile:
                    redis_key = RedisContant.MOBILE_BASIC_IDX;
                    break;
                case email:
                    redis_key = RedisContant.EMAIL_BASIC_IDX;
                    break;
            }
            validatePasswordWithType(requestData.getPasswordOld(), PasswordType._old, true);
            validatePasswordWithType(requestData.getPasswordNew(), PasswordType._new, true);
            String userValue = RedisUtil.hget(redis_key, requestData.getPassport());
            UserEntity entity = null;
            if (StringUtils.isBlank(userValue) || (entity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                log.error("用户不存在passport:{}", requestData.getPassport());
                throw new BizException(ErrorCode.USER_NOT_EXIST.getCode());
            }

            //数据库密码
            String md5Password = MD5Util.getMD5String(requestData.getPasswordOld() + entity.getSalt());
            String pbkdf2Password = Pbkdf2Util.encrypt(AESUtils.decrypt(requestData.getPasswordOld()), entity.getSalt());
            if (!entity.getPassword().equals(md5Password) && !entity.getPassword().equals(pbkdf2Password)) {
                throw new BizException(ErrorCode.PASSWORD_WRONG.getCode(), ErrorCode.PASSWORD_WRONG.getMessage());
            }
            if (requestData.getPasswordNew().equals(requestData.getPasswordOld())) {
                log.error(ErrorCode.SAME_WITH_OLD_PWD.getMessage());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SAME_WITH_OLD_PWD));
            }
            requestData.setPassword(requestData.getPasswordNew());
            UserEntity userEntity = passwordService.resetPassword(requestData, type);
            if (userEntity == null) {
                log.error("修改密码失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            TokenUtil.destoryToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("passport=" + requestData.getPassport(), e);
        }
    }
}
