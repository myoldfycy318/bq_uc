package com.qbao.store.controller.thirdpart;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.constants.ThirdPartyEnum;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ThirdPartyController
 *
 * @author Zhang ShanMin
 * @date 2017/9/20
 * @time 10:53
 */

@Controller
@RequestMapping("/thirdParty")
public class ThirdPartyController extends BaseController {

    /**
     * 根据
     *
     * @param requestData
     * @return
     */

    @RequestMapping("/getUserInfoByOPenId")
    @ResponseBody
    public String getUserInfoByOPenId(RequestData requestData) {

        ThirdPartyEnum thirdEnum = null;
        try {
            if (StringUtils.isBlank(requestData.getThirdId()) || StringUtils.isBlank(requestData.getOpenId())
                    || null == (thirdEnum = ThirdPartyEnum.getFromKey(requestData.getThirdId()))) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USERID_ILLEGLE));
            }
            String userInfo = RedisUtil.hget(thirdEnum.getOpenIdUserKey(), requestData.getOpenId());
            UserEntity userEntity = null;
            Map<String, String> map = new HashMap<>();
            if (StringUtils.isBlank(userInfo) || (userEntity = JSONObject.parseObject(userInfo, UserEntity.class)) == null) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            return JSONObject.toJSONString(BaseResponse.success(userEntity));
        } catch (Exception e) {
            return handleException(null, e);
        }
    }

    @ResponseBody
    @RequestMapping("/getOpenIdByUserId")
    public String getOpenIdByUserId(RequestData requestData, HttpServletRequest request) {
        if (StringUtils.isBlank(requestData.getUserId()) || StringUtils.isBlank(requestData.getAppId())) {
            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USERID_NULL.getCode(), ErrorCode.USERID_NULL.getMessage()));
        }
        String openId = RedisUtil.hget(RedisContant.WEIXIN_PUBLIC_IDX + ":" + requestData.getUserId(), requestData.getAppId());
        if (StringUtils.isBlank(openId)) {
            return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage()));
        }
        Map<String, String> data = new HashMap<String, String>();
        data.put("wxOpenId", openId);
        return JSONObject.toJSONString(BaseResponse.success(data));
    }
}
