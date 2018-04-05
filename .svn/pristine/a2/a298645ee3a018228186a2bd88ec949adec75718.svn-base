package com.qbao.store.service.impl.thirdpart;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bqiong.usercenter.constants.ErrorCode;
import com.bqiong.usercenter.constants.ThirdPartyEnum;
import com.bqiong.usercenter.exception.BizException;
import com.qbao.store.entity.thirdpart.BindEntity;
import com.qbao.store.helper.ShardHelper;
import com.qbao.store.mapper.thirdpart.BindMapper;
import com.qbao.store.service.thirdpart.BindService;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年5月17日 下午5:56:04 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年5月17日 下午5:56:04 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Service("bindService")
public class BindServiceImpl implements BindService
{
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    BindMapper bindMapper;
    
    @Override
    public BindEntity queryByThird(String thirdId, String openUid) throws Exception
    {
        String tableName = ShardHelper.getBindTableByThird(ThirdPartyEnum.getFromKey(thirdId), openUid);
        if(StringUtils.isBlank(tableName))
        {
            log.error("分表信息获取失败，thirdId=" + thirdId + ",openUid=" + openUid);
            return null;
        }
        return bindMapper.queryByThirdUid(openUid, tableName);
    }
    
    @Override
    public BindEntity queryByDomeId(String thirdId, String userId) throws Exception 
    {
        String tableName = ShardHelper.getBindTableByDomeUid(userId);
        if(StringUtils.isBlank(tableName))
        {
            log.error("分表信息获取失败" + ",userId="+userId);
            return null;
        }
        return bindMapper.queryByDomeUid(thirdId, userId, tableName);
    }
    
    @Override
    public void bind(BindEntity entity) throws Exception 
    {
        //先向第三方分表中添加数据
        String thirdTableName = ShardHelper.getBindTableByThird(ThirdPartyEnum.getFromKey(entity.getThirdId()), entity.getOpenId());
        if(StringUtils.isBlank(thirdTableName))
        {
            log.error("插入按照第三方id分表绑定信息，分表信息获取失败，entity=" + entity);
            throw new BizException(ErrorCode.BIND_FAIL.getCode());
        }
       int thirdResult = bindMapper.insert(entity, thirdTableName);
       if(thirdResult != 1)
       {
           log.error("插入按照第三方id分表绑定信息，向绑定关系表中添加数据失败，entity=" + entity);
           throw new BizException(ErrorCode.BIND_FAIL.getCode());
       }
       //向冰穹分表中添加数据
       String domeTableName = ShardHelper.getBindTableByDomeUid(entity.getUserId());
       if(StringUtils.isBlank(domeTableName))
       {
           log.error("插入按照冰穹id分表绑定信息，分表信息获取失败" + ",entity="+entity);
           throw new BizException(ErrorCode.BIND_FAIL.getCode());
       }
       int domeResult = bindMapper.insert(entity, domeTableName);
       if(domeResult != 1)
       {
           log.error("插入按照冰穹id分表绑定信息，向绑定关系表中添加数据失败，entity=" + entity);
           throw new BizException(ErrorCode.BIND_FAIL.getCode());
       }
    }
    
    @Override
    public boolean isBind(String thirdId, String openId, String userId) throws Exception 
    {
        BindEntity domeBindEntity = queryByDomeId(thirdId, userId);
        if(domeBindEntity == null)
        {
            log.info("第三方分表中用户信息未绑定！");
            return false;
        }
        BindEntity thirdBindEntity = queryByThird(thirdId, openId);
        if(thirdBindEntity == null)
        {
            log.info("冰穹用户id分表中用户信息未绑定！");
            return false;
        }
        return true;
    }
}
