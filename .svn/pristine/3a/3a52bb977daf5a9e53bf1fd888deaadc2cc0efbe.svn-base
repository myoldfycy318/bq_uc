package com.qbao.store.controller.openPlatform;

import javax.servlet.http.HttpServletRequest;

import com.bqiong.usercenter.annotation.DefDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.BizType;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.PasswordType;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.service.LoginService;
import com.qbao.store.service.PasswordService;
import com.qbao.store.util.TokenUtil;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p>
 * 作者	：94841
 * 创建时间	：2016年4月29日 下午2:18:48
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年4月29日 下午2:18:48 	修改人：
 * 描述	:
 * **********************************************************
 */
@Controller
@RequestMapping("/open")
public class OpenPasswordController extends BaseController {
    @Autowired
    PasswordService passwordService;

    @Autowired
    LoginService loginService;

    /**
     * 函数名称 : resetPassword
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param resetPassword
     * @param passport
     * @param confirmPassword
     * @param verifyToken
     * @param request
     * @return 修改记录：
     * 日期 ：2016年4月29日 下午3:04:48	修改人：
     * 描述	：
     */
    @DefDataSource
    @RequestMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(String passport, String resetPassword, String confirmPassword, String verifyToken, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(passport);
            PassportType type = validatePassport(null, passport);

            switch (type) {
                case mobile:
                    SMSUtils.validateSmsToken(passport, verifyToken, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.resetPassword.name());
                    break;

                case email:
                    MailUtil.validateEmailToken(passport, verifyToken, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.resetPassword.name());
                    break;
                default:
                    break;
            }

            validatePassword(resetPassword, true);

            validatePassword(confirmPassword, true);

            if (!resetPassword.equals(confirmPassword)) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.CONFIRM_RESET_NOT_SAME));
            }

            OpenPlatformBasicEntity entity = passwordService.resetPassword4Open(passport, resetPassword);

            if (entity == null) {
                log.error("重置密码失败" + ",passport=" + passport + ",verifyToken =" + verifyToken + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM);
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            TokenUtil.destoryToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("passport=" + passport + ",password=" + resetPassword + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }


    /**
     * 函数名称 : checkOldPwd
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param passport
     * @param passwordOld
     * @param passwordNew
     * @param request
     * @return 修改记录：
     * 日期 ：2016年5月4日 上午10:08:28	修改人：
     * 描述	：
     */
    @DefDataSource
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public String modifyPassword(String passport, String passwordOld, String passwordNew, HttpServletRequest request) {
        try {
            PassportType type = validatePassport(null, passport);
            if (type == null) {
                log.error("参数passport类型错误, passport = " + passport);
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.PASSPORT_ILLEGLE));
            }

            validatePasswordWithType(passwordOld, PasswordType._old, true);

            validatePasswordWithType(passwordNew, PasswordType._new, true);

            if (passwordNew.equals(passwordOld)) {
                log.error(ErrorCode.SAME_WITH_OLD_PWD.getMessage());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SAME_WITH_OLD_PWD));
            }

            OpenPlatformBasicEntity basicEntity = loginService.login4OpenPlatform(passport, passwordOld);

            if (basicEntity == null) {
                log.error("修改密码失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }

            OpenPlatformBasicEntity entity = passwordService.resetPassword4Open(passport, passwordNew);

            if (entity == null) {
                log.error("修改密码失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            TokenUtil.destoryToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("passport=" + passport, e);
        }
    }
}
