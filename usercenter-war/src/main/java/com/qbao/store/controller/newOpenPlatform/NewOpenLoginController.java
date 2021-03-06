package com.qbao.store.controller.newOpenPlatform;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.BizType;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.aes.AESUtils;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 新开放平台
 * Created by ym on 2017/10/10.
 */
@Controller
@RequestMapping("/newOpen")
public class NewOpenLoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @OperateType(bizType = BizType.login)
    @RequestMapping("/login")
    @ResponseBody
    public String login(RequestData requestData, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(requestData.getPassport());
            validatePassword(requestData.getPassword(), true);
            PassportType passportType = PassportType.validatePassport(null, requestData.getPassport());
            UserEntity entity = loginService.login(requestData, passportType);
            log.info("开放平台用户登录返回实体entity=" + entity);

            if (entity == null) {
                log.error("用户登录失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            String accessToken = cacheToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, entity.getUserId(), entity.getSalt());
            Map<String, String> data = new HashMap<String, String>();
            data.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            return JSONObject.toJSONString(buildResponse(data));
        } catch (Exception e) {
            return handleException("passport=" + requestData.getPassport() + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }

    @RequestMapping("/isPassportExist")
    @ResponseBody
    public String isPassportExist(String passport, HttpServletRequest request) {
        try {
            validatePassport4OpenPlatform(passport);

            boolean isExist = loginService.isPassportExist4OpenPlatformWithoutDB(passport);
            log.info("用户查询结果，isExist=" + isExist);

            Map<String, Boolean> data = new HashMap<String, Boolean>();
            data.put(UserCenterConstants.IS_PASSPORT_EXIST, isExist);
            return JSONObject.toJSONString(buildResponse(data));
        } catch (Exception e) {
            return handleException("passport=" + passport + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }


    @RequestMapping("/testAes")
    @ResponseBody
    public String test(String password) {
        if (password.length() > 6) {
            System.out.println(AESUtils.decrypt(password));
        }

        return AESUtils.encrypt(password);
    }
}
