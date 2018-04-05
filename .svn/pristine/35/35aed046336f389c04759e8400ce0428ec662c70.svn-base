package com.qbao.store.util;

import com.bqiong.usercenter.constants.BUEnum;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.RiskConstants;
import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.exception.BizException;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.init.InitEntity;
import com.qbao.store.service.InitService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * **********************************************************
 * 内容摘要	：<p>
 * <p/>
 * 作者	：94841
 * 创建时间	：2016年7月6日 下午3:21:53
 * 当前版本号：v1.0
 * 历史记录	:
 * 日期	: 2016年8月6日 下午3:21:53 	修改人：niuzan
 * 描述	:
 * **********************************************************
 */
@Component("initConstants")
public class InitConstants {

    private static final Logger log = LoggerFactory.getLogger(InitConstants.class);

    public static final String REDIS_INIT_PARAMTER_PREFIX = UserCenterConstants.REDIS_USERCENTER_APPLICATION + "initParams";

    @Autowired
    private InitService initService;

    private static Map<String, String> defConstantsMap = new HashMap<String, String>();

    public static void init() {
        defConstantsMap.put(RiskConstants.CAPTCHA_SWITCH, RiskConstants.DEF_SWITCH_VALUE);
        defConstantsMap.put(RiskConstants.CAPTCHA_LIMIT_COUNT, RiskConstants.DEF_CAPTCHA_LIMIT_COUNT);
        defConstantsMap.put(RiskConstants.CAPTCHA_COUNT_INTERVAL, RiskConstants.DEF_CAPTCHA_COUNT_INTERVAL);
        defConstantsMap.put(RiskConstants.CAPTCHA_RISKED_INTERVAL, RiskConstants.DEF_CAPTCHA_RISKED_INTERVAL);
        defConstantsMap.put(RiskConstants.CAPTCHA_EXPIRE_TIME, RiskConstants.DEF_CAPTCHA_EXPIRE_TIME);
        defConstantsMap.put(RiskConstants.SMS_COUNT_INTERVAL, RiskConstants.DEF_SMS_COUNT_INTERVAL);
        defConstantsMap.put(RiskConstants.SMS_LIMIT_COUNT, RiskConstants.DEF_SMS_LIMIT_COUNT);
        defConstantsMap.put(RiskConstants.SMS_RISKED_INTERVAL, RiskConstants.DEF_SMS_RISKED_INTERVAL);
    }

    public void load() {
        init();
        try {
            List<InitEntity> initList = initService.query(null, null);
            for (InitEntity initEntity : initList) {
                String name = StringUtils.isBlank(initEntity.getBuId()) ? initEntity.getName()
                        : initEntity.getBuId().toLowerCase() + "." + initEntity.getName();
                log.info("系统配置参数name=" + name + ",参数value=" + initEntity.getValue());
                RedisUtil.hset(REDIS_INIT_PARAMTER_PREFIX, name, initEntity.getValue());
            }
        } catch (Exception e) {
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode());
        }
    }

    private static String getInitValueByName(String name) {
        String value = RedisUtil.hget(REDIS_INIT_PARAMTER_PREFIX, name);
        return StringUtils.isNotBlank(value) ? value : getValueFromDefMap(name);
    }

    private static String getValueFromDefMap(String name) {
        return defConstantsMap.get(name);

    }

    public static String getValue(BUEnum buEnum, String name) {
        String value = InitConstants.getInitValueByName(buEnum.getBuId().toLowerCase(Locale.getDefault()) + "." + name);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        value = InitConstants.getInitValueByName(name);
        if (StringUtils.isBlank(value)) {
            log.error("获取配置参数错误，请检查paramter表中参数配置！buid=" + buEnum.getBuId() + "参数名称：" + name);
            throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), "配置参数错误");
        }
        return value;
    }

}
