package com.qbao.store.service.impl.thirdpart;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ThirdPartyEnum;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.thirdpart.ThirdOauthLoginService;
import com.qbao.store.service.thirdpart.ThirdPartyAdapterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2017/7/24.
 */
@Service("thirdOauthLoginService")
public class ThirdOauthLoginServiceImpl extends ThirdPartyAdapterService implements ThirdOauthLoginService {

    @Override
    public UserEntity login(RequestData requestData) throws Exception {
        return registerIfNE(requestData);
    }

    @Transactional
    @Override
    public UserEntity bind(RequestData requestData) throws Exception {
        ThirdPartyEnum thirdPartyEnum = null;//第三方账户
        String redisKey = "";
        String userInfo = "";
        UserEntity _userEntity = null;
        if (StringUtils.isNotBlank(requestData.getThirdId()) && StringUtils.isNotBlank(requestData.getOpenId()) &&
                null != (thirdPartyEnum = ThirdPartyEnum.getFromKey(requestData.getThirdId()))) {
            userInfo = RedisUtil.hget(thirdPartyEnum.getOpenIdUserKey(), requestData.getOpenId());
            //账户绑定
            _userEntity = bindPassport(requestData, userInfo);
            //替换缓存数据
            RedisUtil.hset(thirdPartyEnum.getOpenIdUserKey(), requestData.getOpenId(), JSONObject.toJSONString(_userEntity));
        }
        return _userEntity;
    }
}
