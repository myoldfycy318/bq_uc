package com.qbao.store.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qbao.store.util.RedisDataType;

/**
 * redis注解
 * @author yewenhai
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisConfig {
    /** redis数据类型 **/
    RedisDataType redisType();

    /** 数据key **/
    String key() default "";

    /** ZSET HASH内部数据key **/
    String fieldKey() default "";

    /** ZSET score **/
    String score() default "";

    /** 过期时间(秒) **/
    int expireSeconds() default 0;

    /** 预处理bean名称 **/
    String processBean() default "";

}
