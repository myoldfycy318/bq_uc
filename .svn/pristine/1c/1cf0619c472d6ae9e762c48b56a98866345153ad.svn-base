package com.qbao.store.redis;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bqiong.usercenter.util.RedisUtil;
import com.qbao.store.service.AppRedisService;
import com.qbao.store.util.RedisDataType;
import com.qbao.store.util.SpringUtil;

/***
 * redis切面处理器
 * @author yewenhai
 *
 */
@Component
@Aspect
public class RedisAspectHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RedisAspectHandler.class);

    // 定义切入点
    @Pointcut("@annotation(com.qbao.store.redis.RedisConfig)")
    public void redisPointCut() {

    }

    /**
     * 清除缓存
     * @param joinPoint
     */
    @Around("redisPointCut()")
    public Object redis(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature; // 获取方法签名
        Method method = methodSignature.getMethod();

        RedisConfig config = method.getAnnotation(RedisConfig.class);
        Object result = null;
        try {
            result = this.getDataFromRedis(config, joinPoint); // 从redis中获取数据
        } catch (Exception e) {
            LOG.error("RedisAspectHandler.redis exception:{}", ExceptionUtils.getFullStackTrace(e));
        }
        if (null != result) {
            String beanId = config.processBean();
            if (StringUtils.isNotEmpty(beanId)) {
                AppRedisService redisService = SpringUtil.getBeanById(beanId);
                redisService.process(result, method.getReturnType());
                return result;
            }
        }
        try {
            result = joinPoint.proceed();// 若redis中无数据，则取方法返回值
            if (null != result) {
                this.setDataToRedis(config, result);
            }
        } catch (Throwable e) {
            LOG.error("RedisAspectHandler.redis exception:{}", ExceptionUtils.getFullStackTrace(e));
        }
        return result;
    }

    /**
     * 将数据存放到redis中
     * @param config
     */
    private void setDataToRedis(RedisConfig config, Object result) {
        String value = JSONObject.toJSONString(result);
        if (StringUtils.isNotEmpty(value) && !"{}".equals(value) && !"[]".equals(value)) {
            RedisDataType type = config.redisType();
            switch (type) {
            case STRING:
                this.setStringToRedis(config.key(), value, config.expireSeconds());
                break;
            default:
                break;
            }
        }
    }

    /**
     * 将数据存储在redis中
     * @param key
     * @param value
     * @param expireSeconds
     */
    private void setStringToRedis(String key, String value, int expireSeconds) {
        if (0 != expireSeconds) {
            RedisUtil.set(key, value, expireSeconds);
        } else {
            RedisUtil.set(key, value);
        }
    }

    /**
     * 从redis中获取数据
     * @param config
     * @return
     */
    private Object getDataFromRedis(RedisConfig config, ProceedingJoinPoint joinPoint) {
        RedisDataType type = config.redisType();
        String result = ""; // JSON字符串
        switch (type) {
        case STRING:
            result = RedisUtil.get(config.key());
            break;
        default:
            break;
        }
        if (StringUtils.isNotEmpty(result)) {
            return JSONObject.parseObject(result);
        }
        return null;
    }

    /**
     * 获取rediskey
     * @param config
     * @param joinPoint
     * @return
     */
    private String getKey(RedisConfig config, ProceedingJoinPoint joinPoint) {

        return null;
    }

    /**
     * 若用户未指定rediskey，则取方法参数toString作为redis的key,多个key以:分隔
     * @param joinPoint
     * @return
     */
    private String getDefaultKey(ProceedingJoinPoint joinPoint) {
        Object[] paramArgs = joinPoint.getArgs();
        StringBuffer keyName = new StringBuffer();
        // redis 方法参数.toString()  value = 返回结果的json字符串
        if ((null != paramArgs) && (paramArgs.length != 0)) {
            for (Object object : paramArgs) {
                if (!StringUtils.isEmpty(object.toString())) {
                    keyName.append(":").append(object.toString());
                }
            }
        }
        String result = keyName.toString();
        if (StringUtils.isNotEmpty(result)) {
            return result.substring(1);
        }
        return "";
    }

}
