package com.qbao.store.controller.thirdpart;

import com.bqiong.usercenter.constants.*;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.util.CommonUtil;
import com.bqiong.usercenter.util.MailUtil;
import com.bqiong.usercenter.util.RedisUtil;
import com.bqiong.usercenter.util.SMSUtils;
import com.qbao.store.controller.BaseController;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.service.tourist.TouristBindService;
import com.qbao.store.util.BizUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ThirdPartyAdapterController
 *
 * @author Zhang ShanMin
 * @date 2017/7/24
 * @time 16:04
 */
public abstract class ThirdPartyAdapterController extends BaseController {

    @Autowired
    protected TouristBindService touristBindService;


    /**
     * 账户绑定
     * @param requestData
     * @return
     * @throws Exception
     */
    protected void bindPassport(RequestData requestData) throws Exception {
        //请求来源是app_store时候，没有clientid，使用buId标识
        BizUtil.validateClientId(requestData);
        PassportType type = validatePassport(requestData.getCountryCode(), requestData.getPassport());
        String redis_idx_key = null;
        if (PassportType.mobile == type) {
            SMSUtils.verifySmsCode(requestData.getCountryCode(), requestData.getPassport(), requestData.getVerifyCode(), requestData.getClientId(), BizType.bind.name());
            redis_idx_key = RedisContant.MOBILE_IDX;
        }
        if (PassportType.email == type) {
            MailUtil.verifyCode(requestData.getPassport(), requestData.getClientId(), BizType.bind.name(), requestData.getVerifyCode());
            redis_idx_key = RedisContant.EMAIL_IDX;
        }
        //仅可绑定未注册账号
        if (StringUtils.isNotBlank(RedisUtil.hget(redis_idx_key, requestData.getPassport()))) {//账户绑定，若账户的库表索引已存在则该账户已绑定
            throw new BizException(ErrorCode.BIND_PASSPORT_EXIST.getCode());
        }
        //需要实名认证
        if (requestData.getNeedCard()) {
            // 验证姓名
            CommonUtil.validateNameInner(requestData.getName());
            // 验证身份证号
            CommonUtil.validateCardInner(requestData.getCard());
        }

    }


}
