package com.qbao.store.controller.tourist;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.CommonUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.controller.thirdpart.ThirdPartyAdapterController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.RegisterService;
import com.qbao.store.service.tourist.TouristBindService;
import com.qbao.store.util.BizUtil;
import com.qbao.store.util.UserCenterContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tourist")
public class TouristController extends ThirdPartyAdapterController {
    private Logger logger = LoggerFactory.getLogger(TouristController.class);
    @Autowired
    TouristBindService touristBindService;

    @Autowired
    RegisterService registerService;

    @OperateType(bizType = BizType.touristLogin)
    @RequestMapping("/login")
    @ResponseBody
    public String login(RequestData requestData) {
        try {
            if (StringUtils.isBlank(requestData.getImsi())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BUEnum buEnum = CommonUtil.validateBuId(requestData.getBuId());
            BizUtil.validateClientId(requestData);
            UserEntity entity = touristBindService.touristLogin(requestData);
            UserCenterContext.buildUserBasicInfo2LogEntity(entity);
            String _clientid = BizUtil.distinguishPlatformOrGame(buEnum, requestData.getClientId());
            String accessToken = cacheToken(_clientid, entity.getUserId(), entity.getSalt());
            RedisUtil.set(UserCenterConstants.REDIS_LOGIN_TYPE_PREFIX + accessToken, LoginTypeEnum.tourist.name(), UserCenterConstants.TOKEN_EXPIRE_TIME);

            int isBind = 0;//游客是否绑定账户，0:未绑定,1:绑定
            String passport = "";
            if (StringUtils.isNotBlank(entity.getRegisterBy()) && "mobile".equals(entity.getRegisterBy()) && StringUtils.isNotBlank(entity.getMobile()) && !entity.getMobile().startsWith("100")) {
                passport = entity.getMobile();
                isBind = 1;
            }
            if (StringUtils.isNotBlank(entity.getRegisterBy()) && "email".equals(entity.getRegisterBy()) && StringUtils.isNotBlank(entity.getEmail())) {
                passport = entity.getEmail();
                isBind = 1;
            }
            Map<String, Object> data = new HashMap<String, Object>();
            data.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            data.put(UserCenterConstants.IS_BIND, isBind);
            data.put("passport", passport);
            return JSONObject.toJSONString(buildResponse(data));
        } catch (Exception e) {
            return handleException("", e);
        }
    }

    @OperateType(bizType = BizType.touristBind)
    @RequestMapping("/bind")
    @ResponseBody
    public String bind(RequestData requestData) {
        try {
            if (StringUtils.isBlank(requestData.getImsi())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BUEnum buEnum = CommonUtil.validateBuId(requestData.getBuId());
            //账户绑定
            bindPassport(requestData);
            UserEntity userEntity = touristBindService.bind(requestData);
            String _clientId = BizUtil.distinguishPlatformOrGame(buEnum, requestData.getClientId());
            String accessToken = cacheToken(_clientId, userEntity.getUserId(), userEntity.getSalt());
            RedisUtil.set(UserCenterConstants.REDIS_LOGIN_TYPE_PREFIX + accessToken, LoginTypeEnum.passport.name(), UserCenterConstants.TOKEN_EXPIRE_TIME);
            Map<String, String> data = new HashMap<String, String>();
            data.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            data.put("domeUserId", userEntity.getUserId());
            data.put("passport", requestData.getPassport());
            return JSONObject.toJSONString(BaseResponse.success(data));
        } catch (Exception e) {
            return handleException(null, e);
        }
    }

}
