package com.qbao.store.util;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * BizUtil
 *
 * @author Zhang ShanMin
 * @date 2017/7/20
 * @time 20:11
 */
public class BizUtil {

    /**
     * 账户是否绑定
     *
     * @param entity
     * @return
     */
    public static boolean isBind4PassPort(UserEntity entity) {
        if (StringUtils.isNotBlank(entity.getRegisterBy()) && "mobile".equals(entity.getRegisterBy()) && StringUtils.isNotBlank(entity.getMobile()) && !entity.getMobile().startsWith("100")) {
            return true;
        }
        if (StringUtils.isNotBlank(entity.getRegisterBy()) && "email".equals(entity.getRegisterBy()) && StringUtils.isNotBlank(entity.getEmail())) {
            return true;
        }
        return false;
    }

    /**
     * 验证clientId
     * 请求来源是app_store时候，没有clientid，使用buId标识
     *
     * @param requestData
     */
    public static void validateClientId(RequestData requestData) {
        if (StringUtils.isBlank(requestData.getClientId()) && !BUEnum.isMobileGameBU(requestData.getBuId())) {
            requestData.setClientId(requestData.getBuId());
        } else {
            if (StringUtils.isBlank(requestData.getClientId()))
                throw new BizException(ErrorCode.USERID_ILLEGLE.getCode());
        }
    }

    /**
     * 更新缓存信息
     *
     * @param _userEntity
     */
    public static void updateUserCache(UserEntity _userEntity) {
        //更新缓存信息
        if (StringUtils.isNotBlank(_userEntity.getMobile()) && !_userEntity.getMobile().startsWith("100")) {
            RedisUtil.hset(RedisContant.MOBILE_BASIC_IDX, _userEntity.getMobile(), JSONObject.toJSONString(_userEntity));
        }
        if (StringUtils.isNotBlank(_userEntity.getEmail())) {
            RedisUtil.hset(RedisContant.EMAIL_BASIC_IDX, _userEntity.getEmail(), JSONObject.toJSONString(_userEntity));
        }
        if (StringUtils.isNotBlank(_userEntity.getImsi())) {
            RedisUtil.hset(RedisContant.IMSI_BAISC_IDX, _userEntity.getImsi(), JSONObject.toJSONString(_userEntity));
        }
        ThirdPartyEnum thirdPartyEnum = null;
        if (StringUtils.isNotBlank(_userEntity.getThirdId()) && StringUtils.isNotBlank(_userEntity.getOpenId())
                && null != (thirdPartyEnum = ThirdPartyEnum.getFromKey(_userEntity.getThirdId()))) {
            RedisUtil.hset(thirdPartyEnum.getOpenIdUserKey(), _userEntity.getOpenId(), JSONObject.toJSONString(_userEntity));
        }
        RedisUtil.hset(RedisContant.USER_BASIC_IDX, _userEntity.getUserId(), JSONObject.toJSONString(_userEntity));
    }


    /**
     * 根据buId区分平台和游戏
     */

    public static String distinguishPlatformOrGame(BUEnum bu, String clientId) {
        switch (bu) {
            case OPEN_PLATFORM:
            case PASSPORT:
            case YEYOU:
            case DMOE_H5:
                return UserCenterConstants.DEF_CLIENTID_4_OPEN_PLATFORM;
            default:
                return clientId;
        }

    }

    /**
     * 用户是否绑定手机号
     *
     * @param mobile
     * @return
     */
    public static String checkBindMobile(String mobile) {
        return StringUtils.isBlank(mobile) || mobile.startsWith("100") ? "false" : "true";
    }


    /**
     * 用户是否绑定手机号
     *
     * @param mobile
     * @return
     */
    public static String userPassport(String mobile,String email) {
        return StringUtils.isBlank(mobile) || mobile.startsWith("100") ? StringUtils.isBlank(email) ? "" : email : mobile;
    }


}
