package com.qbao.store.controller.thirdpart;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.constants.ThirdConstants;
import com.bqiong.usercenter.constants.ThirdPartyEnum;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.service.thirdpart.BindService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/thirdpart")
public class IsbindController extends BaseController {
    @Autowired
    BindService bindService;

    @RequestMapping("/isBind")
    @ResponseBody
    public String isBind(RequestData requestData) {
        try {
            validateBuId(requestData.getBuId());
            validateClientId(requestData.getClientId());
            validateThirdId(String.valueOf(requestData.getThirdId()));
            Map<String, String> data = new HashMap<String, String>();
            ThirdPartyEnum thirdPartyEnum = ThirdPartyEnum.getFromKey(String.valueOf(requestData.getThirdId()));
            String userInfo = null;
            switch (thirdPartyEnum) {
                //第三方钱宝openId对应库表索引、用户基本信息加载到redis
                case QBAO: {
                    RedisUtil.hget(RedisContant.QBAO_THIRD_BASIC_IDX, requestData.getOpenId());
                    break;
                }
                case FACEBOOK: {
                    RedisUtil.hget(RedisContant.FB_THIRD_BASIC_IDX, requestData.getOpenId());
                    break;
                }
            }
            data.put(ThirdConstants.IS_BIND, "false");
            if (StringUtils.isNotBlank(userInfo)) {
                data.put(ThirdConstants.IS_BIND, "true");
            }
            return JSONObject.toJSONString(buildResponse(data));
        } catch (Exception e) {
            return handleException(e.getMessage(), e);
        }
    }

}
