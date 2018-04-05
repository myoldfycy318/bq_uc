package com.qbao.store.listener.mqlistener;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.exception.BizException;
import com.qbao.store.entity.user.UserEntity;
import com.qbao.store.mapper.user.UserMapper;
import com.qbao.store.service.UserService;
import com.qbao.store.util.UserCenterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户登录各平台记录
 * Created by ym on 2017/11/8.
 */
@Component("userLoginPlatformRecordListener")
public class UserLoginPlatformRecordListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(UserLoginPlatformRecordListener.class);

    @Resource
    UserMapper userMapper;
    @Resource(name = "userService")
    UserService userService;

    @Override
    public void onMessage(Message message) {
        String dbTblIdx = null;
        try {
            String body = new String(message.getBody(), "utf-8");
            UserEntity userEntity = JSONObject.parseObject(body, UserEntity.class);
            System.out.println(userEntity.getDbTblIdx());
            String[] dbTblIdxArr = userEntity.getDbTblIdx().split("-");
            String dbName = dbTblIdxArr[0];
            String tableName = dbTblIdxArr[1];
            String seqId = dbTblIdxArr[2];
            UserCenterContext.setDataSource(dbName);//切换数据源使用
            if (!userService.updateUserById(tableName, seqId, userEntity)) {
                logger.error("更新login_platform_record栏位失败，用户信息：{}", JSONObject.toJSONString(userEntity));
                throw new BizException(ErrorCode.SYSTEM_EXCEPTION.getCode(), ErrorCode.SYSTEM_EXCEPTION.getMessage());
            }
        } catch (Exception e) {
            logger.error("更新login_platform_record栏位异常，dbTblIdx:{}", dbTblIdx, e);
        }

    }
}
