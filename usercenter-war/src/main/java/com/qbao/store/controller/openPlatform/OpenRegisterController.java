package com.qbao.store.controller.openPlatform;

import java.util.HashMap;
import java.util.Map;

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
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.service.RegisterService;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p>
 * 作者	：94841
 * 创建时间	：2016年4月15日 下午6:01:21
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年4月15日 下午6:01:21 	修改人：niuzan
 * 描述	:
 * **********************************************************
 */
@Controller
@RequestMapping("/open")
public class OpenRegisterController extends BaseController {
    @Autowired
    private RegisterService registerService;

    /**
     * 函数名称 : register
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param verifyToken
     * @param passport
     * @param password
     * @return 修改记录：
     * 日期 ：2016年4月13日 下午4:30:36	修改人：  niuzan
     * 描述	：
     * @throws BizException
     */
    @DefDataSource
    @RequestMapping("/register")
    @ResponseBody
    public String register(String passport, String password, String verifyToken, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(passport);

            PassportType type = validatePassport(null, passport);
            if (type == PassportType.mobile) {
                SMSUtils.validateSmsToken(passport, verifyToken, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.register.name());
            }
            if (type == PassportType.email) {
                MailUtil.validateEmailToken(passport, verifyToken, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.register.name());
            }

            validatePassword(password, true);
            OpenPlatformBasicEntity basicEntity = registerService.register4OpenPlatform(passport, password);
            if (basicEntity == null) {
                log.error("用户注册失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }

            String accessToken = cacheToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, basicEntity.getUserId(), basicEntity.getSalt());

            Map<String, String> result = new HashMap<String, String>();
            result.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            return JSONObject.toJSONString(buildResponse(result));

        } catch (Exception e) {
            return handleException("mobile=" + passport + ",verifyToken=" + verifyToken + ", clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }

    /**
     * 函数名称 : autoRegister
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @param passport
     * @param password
     * @param request
     * @return 修改记录：
     * 日期 ：2016年8月8日 上午10:14:05	修改人：
     * 描述	：
     */
    @DefDataSource
    @RequestMapping("/autoRegister")
    @ResponseBody
    public String autoRegister(String passport, String password, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(passport);

            validatePassword(password, true);
            OpenPlatformBasicEntity basicEntity = registerService.register4OpenPlatform(passport, password);
            if (basicEntity == null) {
                log.error("用户注册失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }

            String accessToken = cacheToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, basicEntity.getUserId(), basicEntity.getSalt());

            Map<String, String> result = new HashMap<String, String>();
            result.put(UserCenterConstants.USER_ID, basicEntity.getUserId());
            result.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            return JSONObject.toJSONString(buildResponse(result));

        } catch (Exception e) {
            return handleException("mobile=" + passport + ", clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }
}
