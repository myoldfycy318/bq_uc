package com.qbao.store.util;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.BUEnum;
import com.bqiong.usercenter.util.CommonUtil;
import com.qbao.store.entity.log.BiReportLogEntity;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.entity.user.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Date;

/**
 * BI 收集数据
 * Created by ym on 2017/11/22.
 */
public class CollectDataLog {
    protected final static Logger log = LoggerFactory.getLogger(CollectDataLog.class);

    public static AmqpTemplate biTemplate;

    public AmqpTemplate getBiTemplate() {
        return biTemplate;
    }

    public void setBiTemplate(AmqpTemplate biTemplate) {
        CollectDataLog.biTemplate = biTemplate;
    }

    /**
     * BI 游戏转化表 需求 (手游)  记录活跃数(login) 注册数(register) [激活数在ba项目]
     */
    public static void recordLog(RequestData requestData, UserEntity user) {
        try {
            BUEnum bu = StringUtils.isNotBlank(requestData.getBuId()) ? BUEnum.getFromKey(requestData.getBuId()) : null;
            String flag = CommonUtil.isCheckPlatform(bu);
            log.info("BI数据收集 buId:{},flag:{},channelId:{}", requestData.getBuId(), flag, requestData.getChannelId());
            BiReportLogEntity biEntity = new BiReportLogEntity();
            biEntity.setDataTime(new Date());
            biEntity.setAppCode(requestData.getClientId());
            biEntity.setUserId(user.getUserId());
            biEntity.setChannelId(requestData.getChannelId());
            biEntity.setSysType(requestData.getSysType());
            biEntity.setRecType(requestData.getRecType());
            biEntity.setPlatformType(requestData.getPlatformType());
            log.info("BIInfo:{}", JSONObject.toJSONString(biEntity));
            boolean isFlag = biEntity.validate(StringUtils.isNotBlank(flag) && flag.equals("platform") ? false : true);
            if (!isFlag && bu != null && StringUtils.isBlank(flag)) {
                log.error("BI参数为空且buId为DOME002,DOME007,DOME008,DOME009,才记录数据 -- (appCode,userId,channelId,sysType,buId,platformType)参数中某些为空");
            }

            if (isFlag && bu != null && StringUtils.isNotBlank(flag) && flag.equals("gameChange")) {
                //游戏转化表(D)
                biEntity.setStatisticsType("gameChange");
                biTemplate.convertAndSend("bi_collect_data_queue_key", biEntity); //异步通知使用rabbitmq
                log.info("游戏转化表- 日志记录成功");
            }
            if (isFlag && bu != null && StringUtils.isNotBlank(flag) && flag.equals("platform")) {
                //平台用户(D)
                biEntity.setStatisticsType("platform");
                biTemplate.convertAndSend("bi_collect_data_queue_key", biEntity); //异步通知使用rabbitmq
                log.info("平台用户 日志记录成功");
            }
            log.info("BI 数据记录完成");
        } catch (Exception e) {
            log.error("BI 收集数据异常", e);
        }
    }


}
