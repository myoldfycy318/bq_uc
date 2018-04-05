package com.qbao.store.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.httpresponse.BaseResponse;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.controller.IBaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * ********************************************************** 内容摘要 ：
 * <p>
 * <p>
 * 作者 ：94841 创建时间 ：2016年4月22日 下午3:48:11 当前版本号：v1.0 历史记录 : 日期 : 2016年4月22日
 * 下午3:48:11 修改人：niuzan 描述 :
 * **********************************************************
 */
@Controller
@RequestMapping("/email")
public class EmailVerifyCodeController extends IBaseController {

    /**
     * 函数名称 : getCode 功能描述 : 参数及返回值说明：
     *
     * @param bizType  业务类型 必填
     * @param email    邮箱地址 必填
     * @param userIp   用户IP 必填
     * @param clientId 客户端id
     * @param isInland 0 国内 1 海外
     * @return 修改记录： 日期 ：2016年4月22日 下午06:06:28 修改人： niuzan 描述 ： 修改记录： 日期：
     * 2017年4月05日上午11:30:20 修改人：liuzhongwei 描述：增加邮箱查询，验证邮箱是否存在。
     * 2017年5月05日上午11:30:20 修改人：liuzhongwei 描述：重构了代码
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(
            String bizType,
            String email,
            String clientId,
            String buId,
            @RequestParam(required = false) String userIp,
            @RequestParam(defaultValue = "0") int isInland) {
        try {
            logger.info("请求参数:isInland:{}", isInland);
            // 验证账号为空，或者账号类型是否存在
            validateEmail(email);
            BUEnum bu = validateBuId(buId);
            // 请求来源是app_store时候，没有clientid，使用buId标识
            if (StringUtils.isBlank(clientId) && !BUEnum.isMobileGameBU(buId)) {
                clientId = buId;
            }
            validateClientId(clientId);
            validateIp(userIp);
            BizType bt = validateBizType(bizType);
            // 查询邮箱
            String emailIdxVal = RedisUtil.hget(RedisContant.EMAIL_IDX, email);
            // 查询邮箱是否注册过
            if ((bt == BizType.register || bt == BizType.bind) && StringUtils.isNotBlank(emailIdxVal)) {
                logger.error(ErrorCode.PASSPORT_EXIST.getMessage());
                throw new BizException(ErrorCode.PASSPORT_EXIST.getCode(),ErrorCode.PASSPORT_EXIST.getMessage());
            }

            // 查询邮箱是否注册过，没有注册的邮箱不可以修改密码
            if ((bt == BizType.resetPassword || bt == BizType.changeBind) && StringUtils.isBlank(emailIdxVal)) {
                logger.error(ErrorCode.PASSPORT_NOT_EXIST.getMessage());
                throw new BizException(ErrorCode.PASSPORT_NOT_EXIST.getCode(),ErrorCode.PASSPORT_NOT_EXIST.getMessage());
            }
            //发送邮箱验证码
            MailUtil.sendMail(email, clientId, bt.name(), isInland == 0);
            return JSONObject.toJSONString(BaseResponse.success());
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * 验证邮箱验证码
     *
     * @param bizType   业务类型
     * @param email     邮箱地址
     * @param userIp    用户IP
     * @param clientId  客户端id
     * @param buId      BUId
     * @param emailCode 验证码
     *                  <p>
     *                  修改记录：
     *                  2017年5月05日上午11:30:20 修改人：liuzhongwei 描述：重构了代码
     * @return
     */
    @RequestMapping("/verifyCode")
    @ResponseBody
    public String verifyCode(
            String bizType,
            String email,
            String clientId,
            String buId,
            String emailCode,
            String userIp) {
        try {
            BUEnum bu = validateBuId(buId);
            // 请求来源是app_store时候，没有clientid，使用buId标识
            if (StringUtils.isBlank(clientId) && !BUEnum.isMobileGameBU(buId)) {
                clientId = buId;
            }
            validateClientId(clientId);
            validateEmail(email);
            validateIp(userIp);
            BizType bt = validateBizType(bizType);

            String emailIdxVal = RedisUtil.hget(RedisContant.EMAIL_IDX, email);
            String emailToken = "";
            Map<String, String> result = new HashMap<>();
            if (bt == BizType.register || bt == BizType.bind) {
                // 查询手邮箱是否注册过
                if (StringUtils.isNotBlank(emailIdxVal)) {
                    logger.error(ErrorCode.PASSPORT_EXIST.getMessage());
                    throw new BizException(ErrorCode.PASSPORT_EXIST.getCode(),ErrorCode.PASSPORT_EXIST.getMessage());
                }
                MailUtil.verifyCode(email, clientId, bu.name(), emailCode);
                emailToken = MailUtil.cacheEmailToken(email, clientId, bt.name());
                result.put(UserCenterConstants.SMS_TOKEN, emailToken);
            }

            if (bt == BizType.resetPassword || BizType.getFromKey(bizType) == BizType.changeBind) {
                // 查询邮箱是否注册过
                if (StringUtils.isBlank(emailIdxVal)) {
                    logger.error(ErrorCode.PASSPORT_NOT_EXIST.getMessage());
                    throw new BizException(ErrorCode.PASSPORT_NOT_EXIST.getCode(),ErrorCode.PASSPORT_NOT_EXIST.getMessage());
                }
                MailUtil.verifyCode(email, clientId, bt.name(), emailCode);
                emailToken = MailUtil.cacheEmailToken(email, clientId, bt.name());
                result.put(UserCenterConstants.SMS_TOKEN, emailToken);
            }

            /**
             * 发行需求逻辑，邮箱账号绑定手机
             */
            /*
             * if(BizType.getFromKey(bizType) == BizType.bindMobile) {
			 * SMSUtils.verifySmsCode(email, emailCode, clientId,
			 * BizType.getFromKey(bizType).name());
			 * 
			 * //smsToken = SMSUtils.cacheSmsToken(mobile, clientId,
			 * BizType.getFromKey(bizType).name());
			 * 
			 * //result.put(UserCenterConstants.SMS_TOKEN, smsToken); return
			 * JSONObject.toJSONString(BaseResponse.success()); }
			 */
            return JSONObject.toJSONString(BaseResponse.success(result));
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    /**
//     * 函数名称 : getCode4Test 功能描述 : 参数及返回值说明：
//     *
//     * @param bizType
//     * @param mobile
//     * @param userIp
//     * @param clientId
//     * @param buId
//     * @param request
//     * @return 修改记录： 日期 ：2016年7月28日 下午12:24:20 修改人： nz 描述 ：这个方法是为测试写的，没有实际业务功能
//     */
//    @RequestMapping("/getCode4Test")
//    @ResponseBody
//    public String getCode4Test(String bizType, String mobile, String userIp,
//                               String clientId, String buId) {
//        try {
//            BUEnum bu = validateBuId(buId);
//            // 请求来源是app_store时候，没有clientid，使用buId标识
//            if (StringUtils.isBlank(clientId)
//                    && BUEnum.getFromKey(buId) != BUEnum.DOME_SDK) {
//                clientId = buId;
//            } else {
//                validateClientId(clientId);
//            }
//            validatePassport(null, mobile);
//            validateIp(userIp);
//            validateBizType(bizType);
//            PassportType type = PassportType.getPassportType(mobile);
//            if (type != PassportType.mobile) {
//                log.error("手机号码类型错误, mobile = " + mobile);
//                return JSONObject.toJSONString(BaseResponse
//                        .fail(ErrorCode.PASSPORT_ILLEGLE));
//            }
//
//            Map<String, String> result = new HashMap<String, String>();
//            EmailIndexEntity entity = emailIndexService.queryByEmail(mobile);
//            if (BizType.getFromKey(bizType) == BizType.register) {
//                // 查询手机号是否注册过
//                if (entity != null) {
//                    log.error(ErrorCode.PASSPORT_EXIST.getMessage());
//                    return JSONObject.toJSONString(BaseResponse
//                            .fail(ErrorCode.PASSPORT_EXIST));
//                }
//                String mobileCode = SMSUtils.getSmsCodeByMobile(mobile,
//                        clientId, BizType.getFromKey(bizType).name());
//                result.put(UserCenterConstants.MOBILE_CODE, mobileCode);
//                return JSONObject.toJSONString(buildResponse(result));
//            }
//
//            if (BizType.getFromKey(bizType) == BizType.resetPassword) {
//                // 查询手机号是否注册过
//                if (entity == null) {
//                    log.error(ErrorCode.PASSPORT_NOT_EXIST.getMessage());
//                    return JSONObject.toJSONString(BaseResponse
//                            .fail(ErrorCode.PASSPORT_NOT_EXIST));
//                }
//                String mobileCode = SMSUtils.getSmsCodeByMobile(mobile,
//                        clientId, BizType.getFromKey(bizType).name());
//                result.put(UserCenterConstants.MOBILE_CODE, mobileCode);
//                return JSONObject.toJSONString(buildResponse(result));
//            }
//
//            /**
//             * 发行需求逻辑，邮箱账号绑定手机
//             */
//            if (BizType.getFromKey(bizType) == BizType.bind) {
//                String mobileCode = SMSUtils.getSmsCodeByMobile(mobile,
//                        clientId, BizType.getFromKey(bizType).name());
//                result.put(UserCenterConstants.MOBILE_CODE, mobileCode);
//                return JSONObject.toJSONString(BaseResponse.success());
//            }
//            log.error("非法的业务类型！" + "bizType = " + bizType);
//            return JSONObject.toJSONString(BaseResponse
//                    .fail(ErrorCode.SYSTEM_EXCEPTION));
//        } catch (Exception e) {
//            return handleException("mobile=" + mobile + ", userIp=" + userIp
//                    + ",buId=" + buId + ",clientId=" + clientId, e);
//        }
//    }

}
