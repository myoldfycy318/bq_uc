package com.qbao.store.controller.openPlatform;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bqiong.usercenter.annotation.DefDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.openPlatform.OpenPlatformBasicEntity;
import com.qbao.store.service.openPlatform.OpenPlatformService;
import com.qbao.store.util.TokenUtil;

@Controller
@RequestMapping("/open")
public class OpenUserController extends BaseController {
    @Autowired
    private OpenPlatformService openPlatformService;

    @DefDataSource
    @RequestMapping("/getByToken")
    @ResponseBody
    public String getByToken(String accessToken, HttpServletRequest request) {
        try {
            accessToken = TokenUtil.validateToken(accessToken, UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, true);
            Map<String, String> tokenMap = TokenUtil.parseToken(accessToken);
            String userId = tokenMap.get(UserCenterConstants.DOME_USER_ID);
            OpenPlatformBasicEntity user = openPlatformService.queryById(userId);
            if (user == null) {
                log.error("用户不存在");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            log.info("查询所得用户信息user=" + user);

            TokenUtil.validateTokenSign(accessToken, user.getSalt());
            //延长token时间24小时，只有用户在连续24小时不登录的情况下会需要重新登录
            TokenUtil.expireToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, userId);

            Map<String, String> data = new HashMap<String, String>();
            data.put("userId", user.getUserId());
            data.put("userName", StringUtils.isBlank(user.getMobile()) ? user.getEmail() : user.getMobile());
            return JSONObject.toJSONString(buildResponse(data));
        } catch (Exception e) {
            return handleException("token=" + accessToken + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }

    @DefDataSource
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(String accessToken, HttpServletRequest request) {
        try {
            accessToken = TokenUtil.decryptToken(accessToken);
            Map<String, String> tokenMap = TokenUtil.parseToken(accessToken);
            String userId = tokenMap.get(UserCenterConstants.DOME_USER_ID);
            OpenPlatformBasicEntity user = openPlatformService.queryById(userId);
            if (user == null) {
                log.error("用户查询不存在,登出失败！");
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            log.info("查询所得用户信息user=" + user);

            TokenUtil.validateTokenSign(accessToken, user.getSalt());
            TokenUtil.destoryToken(UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, userId);
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException("token=" + accessToken + ",clientId=" + UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM, e);
        }
    }
}
