package com.qbao.store.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.IBaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.UserService;
import com.qbao.store.util.BizUtil;
import com.qbao.store.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * idcard
 *
 * @author hunsy
 */
@Controller
@RequestMapping(value = "idcard")
public class UserCardController extends IBaseController {

    @Autowired
    private UserService userService;

    /**
     * 验证用户是否实名认证
     *
     * @param accessToken 用户登录的Token
     * @param clientId    客户端id
     * @param buId        BUId
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public String check(
            @RequestParam(value = "accessToken") String accessToken,
            @RequestParam(value = "clientId") String clientId,
            @RequestParam(value = "buId") String buId) {
        try {
            BUEnum bu = validateBuId(buId);
            if (StringUtils.isEmpty(clientId) && bu != BUEnum.DOME_SDK) {
                clientId = buId;
            }
            validateClientId(clientId);
            Map<String, String> tokenMap = TokenUtil.parseToken(accessToken);
            String userId = tokenMap.get(UserCenterConstants.DOME_USER_ID);
            String userValue = RedisUtil.hget(RedisContant.USER_BASIC_IDX, userId);
            UserEntity _userEntity = null;
            if (StringUtils.isBlank(userValue) || (_userEntity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                logger.error("用户不存在Redis userId=" + userId);
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }
            String card = "";
            String name = "";
            int length = 1;
            boolean checked = false;
            if (StringUtils.isNotBlank(_userEntity.getIdCardName()) && StringUtils.isNotBlank(_userEntity.getIdCardNo())) {
                name = _userEntity.getIdCardName();
                card = _userEntity.getIdCardNo();
                length = name.length() - 1;
                checked = true;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("checked", checked);
            map.put("idcard", card);
            map.put("name", name);
            map.put("length", length);
            map.put("userName", _userEntity.getUserName());
            return JSONObject.toJSONString(BaseResponse.success(map));
        } catch (Exception e) {
            return this.handleException(e);
        }
    }


    /**
     * 实名认证
     */
    @OperateType(bizType = BizType.idcard)
    @RequestMapping(value = "attestation", method = RequestMethod.POST)
    @ResponseBody
    public String attestation(RequestData requestData) {
        try {
            BUEnum bu = validateBuId(requestData.getBuId());
            if (StringUtils.isEmpty(requestData.getClientId()) && bu != BUEnum.DOME_SDK) {
                requestData.setClientId(requestData.getBuId());
            }
            validateClientId(requestData.getClientId());
            //验证手机号码格式
            validateMobileInner(requestData.getMobile());
            //验证国内姓名
            validateNameInner(requestData.getName());
            // 验证身份证格式
            validateCardInner(requestData.getCard());
            SMSUtils.verifySmsCode(requestData.getCountryCode(), requestData.getMobile(), requestData.getVerifyCode(), requestData.getClientId(), BizType.idcard.name());
            String userValue = RedisUtil.hget(RedisContant.USER_BASIC_IDX, requestData.getUserId());
            UserEntity _userEntity = null;
            if (StringUtils.isBlank(userValue) || (_userEntity = JSONObject.parseObject(userValue, UserEntity.class)) == null) {
                logger.error("用户不存在Redis userId{}", requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.USER_NOT_EXIST));
            }

//			Long num = RedisUtil.incrByStep(RiskConstants.REDIS_RISK_KEY + "attestation:" + uid, 1);
//			// 每天只能验证3次
//			if (num >= 4) {
//				log.error("用户认证次数达到上限:num:{}", num);
//				throw new BizException(ErrorCode.IDCARD_CHECK_LIMIT.getCode());
//			}
            _userEntity.setIdCardName(requestData.getName());
            _userEntity.setIdCardNo(requestData.getCard());
            String[] dbTblIdx = requestData.getDbTblIdx().split("-");
            UserEntity.Builder userBuilder = new UserEntity.Builder().idCardName(_userEntity.getIdCardName()).idCardNo(_userEntity.getIdCardNo()).updateTime(new Date());
            if (!userService.updateUserById(dbTblIdx[1], dbTblIdx[2], userBuilder.build())) {
                logger.error("实名认证发生错误,用户UserId{}", requestData.getUserId());
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            BizUtil.updateUserCache(_userEntity); //更新缓存信息
            Map<String, Object> map = new HashMap<>();
            map.put("checked", true);
            return JSONObject.toJSONString(BaseResponse.success(map));
        } catch (Exception e) {
            return this.handleException(e);
        }
    }


}
