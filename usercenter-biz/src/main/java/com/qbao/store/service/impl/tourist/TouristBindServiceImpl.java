package com.qbao.store.service.impl.tourist;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.PassportType;
import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.constants.ThirdConstants;
import com.bqiong.usercenter.util.RandomStringUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.thirdpart.ThirdPartyAdapterService;
import com.qbao.store.service.tourist.TouristBindService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("touristBindService")
public class TouristBindServiceImpl extends ThirdPartyAdapterService implements TouristBindService {
    private static final Logger log = LoggerFactory.getLogger(TouristBindServiceImpl.class);


    @Transactional
    @Override
    public UserEntity touristLogin(RequestData requestData) throws Exception {
        //TODO 一个设备可能有多个游客账号，登录时候如果没有账号新建，有的话选择没有绑定的账号进行登录。先有逻辑是一个设备对应一个游客
        String userInfo = RedisUtil.hget(RedisContant.IMSI_BAISC_IDX, requestData.getImsi());
        UserEntity userEntity = null;
        //userinfo为空则注册、不为空获取用信息
        if (StringUtils.isBlank(userInfo) || (userEntity = JSONObject.parseObject(userInfo, UserEntity.class)) == null) {
            requestData.setMobile(ThirdConstants.DEF_THIRD_MOBILE_PREFIX + RandomStringUtil.getRandomMobileSuffix());
            requestData.setPassword(ThirdConstants.DEF_THIRD_PASSWORD);
            return registerService.register(requestData, PassportType.imsi);
        }
        return userEntity;
    }


    @Transactional
    @Override
    public UserEntity bind(RequestData requestData) throws Exception {
        String userInfo = RedisUtil.hget(RedisContant.IMSI_BAISC_IDX, requestData.getImsi());
        //账户绑定
        UserEntity _userEntity = bindPassport(requestData, userInfo);
        RedisUtil.hset(RedisContant.IMSI_BAISC_IDX, requestData.getImsi(), JSONObject.toJSONString(_userEntity));
        return _userEntity;
    }

}
