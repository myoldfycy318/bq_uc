package com.qbao.store.controller.v2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.annotation.OperateType;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.IBaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.service.RegisterService;
import com.qbao.store.util.BizUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 针对passport项目的接口<br>
 * v2 2017/06/1
 *
 * @author hunsy
 */
@RequestMapping(value = "v2/register")
@Controller
public class V2RegisterController extends IBaseController {

    @Autowired
    private RegisterService registerService;

    /**
     * 手机注册
     * String passport, String password,
     * String mobileCode, String buId,
     * String avatarUrl, String clientId,
     * String channelId, String name, String card,
     *
     * @RequestParam(defaultValue = "true") Boolean needCard,
     * @RequestParam(defaultValue = "86") String countryCode,
     * @RequestParam(defaultValue = "N") String gender
     */
    @OperateType(bizType = BizType.register)
    @RequestMapping("/mobile")
    @ResponseBody
    public String mobileRegister(RequestData requestData) {
        try {
            BUEnum buEnum = validateBuId(requestData.getBuId());
            BizUtil.validateClientId(requestData);
            // 验证手机号码
            if (requestData.getCountryCode().equals("86")) {
                validateMobileInner(requestData.getPassport());
            } else {
                validateMobileAbroad(requestData.getPassport());
            }
            // 验证密码
            validatePassword(requestData.getPassword(), isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            if (requestData.getNeedCard()) {
                // 验证姓名
                validateNameInner(requestData.getName());
                // 验证身份证号
                validateCardInner(requestData.getCard());
            }
            // 验证手机验证码
            SMSUtils.verifySmsCode(requestData.getCountryCode(), requestData.getPassport(), requestData.getMobileCode(), requestData.getClientId(), BizType.register.name());
            requestData.setMobile(requestData.getPassport());
            // 注册
            UserEntity entity = registerService.register(requestData, PassportType.mobile);
            logger.info("注册返回结果--->entity:{}", JSON.toJSONString(entity));
            if (entity == null) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            // 生成AccessToken令牌
            String clientId = BizUtil.distinguishPlatformOrGame(buEnum,requestData.getClientId());
            String accessToken = cacheToken(clientId, entity.getUserId(), entity.getSalt());
            Map<String, String> result = new HashMap<String, String>();
            result.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            result.put("domeUserId", entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success(result));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * 邮箱是否已经注册了
     *
     * @param passport
     * @return
     */
    @RequestMapping(value = "has")
    @ResponseBody
    public String haveRegister(String passport) {
        try {
            if (StringUtils.isEmpty(passport)) {
                logger.error("账号为空");
                throw new BizException(ErrorCode.PASSPORT_NULL.code);
            }
            if (passport.contains("@")) {
                validateEmail(passport);
                String userVal = RedisUtil.hget(RedisContant.EMAIL_BASIC_IDX, passport);
                if (StringUtils.isNotBlank(userVal)) {
                    logger.error("邮箱已经注册");
                    throw new BizException(ErrorCode.PASSPORT_EXIST.getCode(),ErrorCode.PASSPORT_EXIST.getMessage());
                }
            } else {
                validateMobileInner(passport);
                String userVal = RedisUtil.hget(RedisContant.MOBILE_BASIC_IDX, passport);
                if (StringUtils.isNotBlank(userVal)) {
                    logger.error("手机号码已经注册");
                    throw new BizException(ErrorCode.PASSPORT_EXIST.getCode(),ErrorCode.PASSPORT_EXIST.getMessage());
                }
            }
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * 邮箱注册
     * String email, String password, String avatarUrl,
     * String buId, String clientId, String channelId,
     * String card, String name, String emailCode,
     *
     * @RequestParam(defaultValue = "N") String gender,
     * @RequestParam(defaultValue = "true") Boolean needCard
     * * @return
     */
    @OperateType(bizType = BizType.register)
    @RequestMapping("/email")
    @ResponseBody
    public String registerEmail(RequestData requestData) {
        try {
            BUEnum buEnum = validateBuId(requestData.getBuId());
            BizUtil.validateClientId(requestData);
            validateEmail(requestData.getEmail());
            if (requestData.getNeedCard()) {
                // 验证姓名
                validateNameInner(requestData.getName());
                // 验证身份证号
                validateCardInner(requestData.getCard());
            }
            validatePassword(requestData.getPassword(), isAESContent(BUEnum.getFromKey(requestData.getBuId())));
            UserEntity entity = registerService.register(requestData, PassportType.email);
            if (entity == null) {
                return JSONObject.toJSONString(BaseResponse.fail(ErrorCode.SYSTEM_EXCEPTION));
            }
            String clientId = BizUtil.distinguishPlatformOrGame(buEnum,requestData.getClientId());
            String accessToken = cacheToken(clientId, entity.getUserId(), entity.getSalt());
            Map<String, String> result = new HashMap<String, String>();
            result.put(UserCenterConstants.ACCESS_TOKEN, accessToken);
            result.put("domeUserId", entity.getUserId());
            return JSONObject.toJSONString(BaseResponse.success(result));
        } catch (Exception e) {
            return handleException(e);
        }
    }
}
