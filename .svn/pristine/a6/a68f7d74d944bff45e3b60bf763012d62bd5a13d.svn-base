package com.qbao.store.service.thirdpart;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.util.RandomStringUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.mapper.user.UserMapper;
import com.qbao.store.service.RegisterService;
import com.qbao.store.util.BizUtil;
import com.qbao.store.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * ThirdPartyAdapter
 *
 * @author Zhang ShanMin
 * @date 2017/7/24
 * @time 11:43
 */
public abstract class ThirdPartyAdapterService {
    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyAdapterService.class);
    @Autowired
    protected RegisterService registerService;
    @Autowired
    protected UserMapper userMapper;

    /**
     * 用户登录，若用户不存在则注册一默认用户
     *
     * @param requestData
     * @return
     * @throws Exception
     */
    protected UserEntity registerIfNE(RequestData requestData) throws Exception {
        ThirdPartyEnum thirdPartyEnum = ThirdPartyEnum.getFromKey(requestData.getThirdId());
        if (thirdPartyEnum == null)
            new BizException(ErrorCode.USERID_ILLEGLE.getCode());
        String userInfo = RedisUtil.hget(thirdPartyEnum.getOpenIdUserKey(), requestData.getOpenId());
        UserEntity userEntity = null;
        //userinfo为空则注册、不为空获取用信息7
        if (StringUtils.isBlank(userInfo) || (userEntity = JSONObject.parseObject(userInfo, UserEntity.class)) == null) {
            requestData.setMobile(ThirdConstants.DEF_THIRD_MOBILE_PREFIX + RandomStringUtil.getRandomMobileSuffix());
            requestData.setPassword(ThirdConstants.DEF_THIRD_PASSWORD);
            return registerService.register(requestData, PassportType.mobile);
        }
        return userEntity;
    }

    /**
     * 账户绑定
     *
     * @param requestData
     * @param userInfo
     * @return
     */
    protected UserEntity bindPassport(RequestData requestData, String userInfo) {
        PassportType type = null;
        UserEntity _userEntity = null;
        if (StringUtils.isBlank(userInfo) || (_userEntity = JSONObject.parseObject(userInfo, UserEntity.class)) == null) {
            throw new BizException(ErrorCode.BIND_FAIL.getCode());
        }
        if (BizUtil.isBind4PassPort(_userEntity)) {
            throw new BizException(ErrorCode.HAS_BIND.getCode());
        }
        //判断账号是否已经存在，仅可绑定未注册账号
        if (!requestData.getCountryCode().equals("86")) {
            type = PassportType.getPassportType(requestData.getPassport());
        } else {
            type = PassportType.getPassportTypeForInner(requestData.getPassport());
        }
        _userEntity.setRegisterBy(type.name());
        _userEntity.setClientId(requestData.getClientId());
        UserEntity.Builder userBuilder = new UserEntity.Builder().registerBy(type.name()).updateTime(new Date());
        switch (type) {
            case mobile: {
                _userEntity.setMobile(requestData.getPassport());
                _userEntity.setCountryCode(StringUtils.isBlank(requestData.getCountryCode()) ? "86" : requestData.getCountryCode());
                _userEntity.setRegisterBy("mobile");
                userBuilder.mobile(_userEntity.getMobile()).countryCode(_userEntity.getCountryCode());
                break;
            }
            case email: {
                _userEntity.setEmail(requestData.getPassport());
                userBuilder.email(_userEntity.getEmail());
                break;
            }
        }
        _userEntity.setRegisterBy(type.name());
        String encPassword = MD5Util.getMD5String(requestData.getPassword() + _userEntity.getSalt());
        _userEntity.setPassword(encPassword);
        userBuilder.password(_userEntity.getPassword());
        userBuilder.clientId(_userEntity.getClientId());
        String[] dbTblIdx = requestData.getDbTblIdx().split("-");
        String dbName = dbTblIdx[0];
        String tableName = dbTblIdx[1];
        String seqId = dbTblIdx[2];
        if (requestData.getNeedCard()) {//实名绑定
            _userEntity.setIdCardNo(requestData.getCard());
            _userEntity.setIdCardName(requestData.getName());
            userBuilder.idCardNo(_userEntity.getIdCardNo()).idCardName(_userEntity.getIdCardName());
        }
        if (!userMapper.updateUserById(tableName, seqId, userBuilder.build())) {
            logger.error("游客绑定失败，imsi:{},绑定用户信息：{}", requestData.getImsi(), JSONObject.toJSONString(userBuilder.build()));
            throw new BizException(ErrorCode.BIND_FAIL.getCode());
        }
        if (StringUtils.isNotBlank(_userEntity.getEmail())) {
            RedisUtil.hset(RedisContant.EMAIL_IDX, _userEntity.getEmail(), requestData.getDbTblIdx());
            RedisUtil.hset(RedisContant.EMAIL_BASIC_IDX, _userEntity.getEmail(), JSONObject.toJSONString(_userEntity));
        }
        if (StringUtils.isNotBlank(_userEntity.getMobile()) && !_userEntity.getMobile().startsWith("100")) {
            RedisUtil.hset(RedisContant.MOBILE_IDX, _userEntity.getMobile(), requestData.getDbTblIdx());
            RedisUtil.hset(RedisContant.MOBILE_BASIC_IDX, _userEntity.getMobile(), JSONObject.toJSONString(_userEntity));
        }
        RedisUtil.hset(RedisContant.USER_BASIC_IDX, _userEntity.getUserId(), JSONObject.toJSONString(_userEntity));
        return _userEntity;
    }
}
