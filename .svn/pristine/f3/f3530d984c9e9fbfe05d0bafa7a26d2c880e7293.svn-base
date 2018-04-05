package com.qbao.store.listener.mqlistener;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.RedisContant;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.entity.request.RequestData;
import com.qbao.store.service.UserService;
import com.qbao.store.util.UserCenterContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * DBTblIdxRecoveryListener
 *
 * @author Zhang ShanMin
 * @date 2017/7/28
 * @time 14:51
 */
@Component("dBTblIdxRecoveryListener")
public class DBTblIdxRecoveryListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(DBTblIdxRecoveryListener.class);
    @Resource(name = "userService")
    private UserService userService;

    @Override
    public void onMessage(Message message) {
        String dbTblIdx = null;
        try {
            String body = new String(message.getBody(), "utf-8");
            RequestData requestData = JSONObject.parseObject(body, RequestData.class);
            String[] dbTblIdxArr = requestData.getDbTblIdx().split("-");
            String dbName = dbTblIdxArr[0];
            String tableName = dbTblIdxArr[1];
            String seqId = dbTblIdxArr[2];
            UserCenterContext.setDataSource(dbName);//切换数据源使用
            //判断数据库该库表索引是有数据，没数据则将库表索引添加到库表列表内
            if (StringUtils.isBlank(userService.queryUserIdById(tableName, seqId))) {
                RedisUtil.rpush(RedisContant.DB_TBL_ID_IDX, requestData.getDbTblIdx());
            }
        } catch (Exception e) {
            logger.error("库表索引回收异常，dbTblIdx:{}", dbTblIdx, e);
        }
    }
}
