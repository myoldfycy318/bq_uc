package com.qbao.store.util;

import com.bqiong.usercenter.constants.UserCenterConstants;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.service.sequence.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * **********************************************************
 *  内容摘要	：<p>
 *
 *  作者	：94841
 *  创建时间	：2016年4月15日 下午4:49:56 
 *  当前版本号：v1.0
 *  历史记录	:
 *  	日期	: 2016年4月15日 下午4:49:56 	修改人：niuzan
 *  	描述	:
 ***********************************************************
 */
@Service
public class UserIdGenerator
{
	@Autowired
	SequenceService sequenceService;
	
	/**
	 * 
	 *  函数名称 : generateUserId
	 *  功能描述 :  生成冰穹用户的id
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年4月19日 下午2:56:00	修改人：  
	 *  	描述	：
	 *
	 */
	public String generateUserId()
	{
		int seq = sequenceService.getNextVal(UserCenterConstants.USER_ID);
		if(String.valueOf(seq).length() == UserCenterConstants.SEQUENCE_LENGHT)
		{
			return UserCenterConstants.BQ_PREFIX + seq;
		}
		int needLength = UserCenterConstants.SEQUENCE_LENGHT - String.valueOf(seq).length();
		String seqStr = "";
		for(int i = 0; i < needLength; i++)
		{
			seqStr = seqStr + "0";
		}
		return UserCenterConstants.BQ_PREFIX + seqStr + seq;
		
	}
	
	/**
	 * 
	 *  函数名称 : generateOpenUserId
	 *  功能描述 :  生成开放平台用户的id
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年5月12日 下午12:10:02	修改人：  nz
	 *  	描述	：
	 *
	 */
	public String generateOpenUserId()
	{
		int seq = sequenceService.getNextVal(UserCenterConstants.OPEN_USER_ID);
		return String.valueOf(seq);
	}

    /**
     * 生成冰穹用户的id
     * @return
     */
    public String produceUserId() {
        JedisPool jedisPool = null;
        Jedis jedis = null;
        String identifier = null;
        String redisKey = "uc:userid";
        try {
            jedisPool = RedisUtil.getPool();
            jedis = jedisPool.getResource();
            identifier = RedisUtil.acquireLockWithTimeout(jedis, redisKey, 1000, 5 * 1000);
            StringBuilder sb = new StringBuilder(UserCenterConstants.BQ_PREFIX);
            sb.append(String.format("%09d", jedis.incr(redisKey)));
            return sb.toString();
        } catch (Exception ex) {
            RedisUtil.releasePool(jedisPool, jedis);
        } finally {
            RedisUtil.releaseLock(jedis, redisKey, identifier);
            RedisUtil.releasePool(jedisPool, jedis);
        }
        return null;
    }

}
