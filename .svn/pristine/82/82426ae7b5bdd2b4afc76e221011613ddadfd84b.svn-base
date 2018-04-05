package com.qbao.store.controller.newOpenPlatform;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.BizType;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by youmin on 2017/10/10.
 */
@Controller
@RequestMapping("/newOpen")
public class NewOpenRegisterController extends BaseController {
    @Autowired
    private RegisterService registerService;

    /**
     * 函数名称 : register
     * 功能描述 :开放平台 注册
     * @throws BizException
     */
    @OperateType(bizType = BizType.register)
    @RequestMapping("/register")
    @ResponseBody
    public String register(RequestData requestData, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(requestData.getPassport());
            requestData.setBuId("DOME010");
            PassportType type = validatePassport(null, requestData.getPassport());
            if (type == PassportType.mobile) {
                SMSUtils.validateSmsToken(requestData.getPassport(), requestData.getVerifyToken(), UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.register.name());
            }
            if (type == PassportType.email) {
                MailUtil.validateEmailToken(requestData.getPassport(), requestData.getVerifyToken(), UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, BizType.register.name());
            }

            validatePassword(requestData.getPassword(), true);
            UserEntity entity = registerService.register(requestData, type);
            if (entity == null) {
                log.error("用户注册失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            String accessToken = cacheToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId(), entity.getSalt());

            Map<String, String> result = new HashMap<String, String>();
            result.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            return JSONObject.toJSONString(buildResponse(result));

        } catch (Exception e) {
            return handleException("mobile=" + requestData.getPassport() + ",verifyToken=" + requestData.getVerifyCode() + ", clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }

    /**
     * 函数名称 : autoRegister
     * 功能描述 :
     * 参数及返回值说明：
     *
     * @return 修改记录：
     * 日期 ：2016年8月8日 上午10:14:05	修改人：
     * 描述	：
     */
    @OperateType(bizType = BizType.register)
    @RequestMapping("/autoRegister")
    @ResponseBody
    //String passport, String password,
    public String autoRegister(RequestData requestData, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(requestData.getPassport());
            requestData.setBuId("DOME010");
            validatePassword(requestData.getPassword(), true);
            PassportType type = validatePassport(null, requestData.getPassport());
            UserEntity entity = registerService.register(requestData, type);
            if (entity == null) {
                log.error("用户注册失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            String accessToken = cacheToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId(), entity.getSalt());
            Map<String, String> result = new HashMap<String, String>();
            result.put(UserCenterConstants.USER_ID, entity.getUserId());
            result.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            return JSONObject.toJSONString(buildResponse(result));

        } catch (Exception e) {
            return handleException("mobile=" + requestData.getPassport() + ", clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }
}
