package com.qbao.store.controller.thirdpart;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.CommonUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.mapper.user.UserBasicMapper;
import com.qbao.store.service.RegisterService;
import com.qbao.store.service.thirdpart.BindService;
import com.qbao.store.service.thirdpart.ThirdPartyService;
import com.qbao.store.util.BizUtil;
import com.qbao.store.util.UserCenterContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/thirdpart")
public class BindController extends BaseController {
    @Autowired
    BindService bindService;

    @Autowired
    RegisterService registerService;

    @Autowired
    UserBasicMapper userBasicMapper;

    @Resource(name = "faceBookService")
    private ThirdPartyService faceBookService;

    @OperateType(bizType = BizType.thirdPartyLogin)
    @RequestMapping("/implicitBind")
    @ResponseBody
    public String implicitBind(RequestData requestData) {
        try {
            if (StringUtils.isBlank(requestData.getOpenId())) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BUEnum buEnum = CommonUtil.validateBuId(requestData.getBuId());
            BizUtil.validateClientId(requestData);
            UserEntity userEntity = faceBookService.login(requestData);
            UserCenterContext.buildUserBasicInfo2LogEntity(userEntity);
            String _clientId = BizUtil.distinguishPlatformOrGame(buEnum, requestData.getClientId());
            String accessToken = cacheToken(_clientId, userEntity.getUserId(), userEntity.getSalt());
            RedisUtil.set(UserCenterConstants.REDIS_LOGIN_TYPE_PREFIX + accessToken, LoginTypeEnum.thirdpart.name(), UserCenterConstants.TOKEN_EXPIRE_TIME);
            Map<String, String> data = new HashMap<String, String>();
            data.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            data.put(UserCenterConstants.DOME_USER_ID, userEntity.getUserId());
            return JSONObject.toJSONString(buildResponse(data));

        } catch (Exception e) {
            return handleException(e.getMessage(), e);
        }
    }

}
