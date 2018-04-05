package com.qbao.store.service;

/**
 * redis接口
 * @author yewenhai
 *
 */
public interface AppRedisService {

    /***
     * redis数据处理
     * @param t
     */
    <T> void process(Object obj, Class<T> clazz);

}
