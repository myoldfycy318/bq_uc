package com.qbao.store.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 动态数据源切面处理代码,convention over configuration根据方法名前缀来选取主从数据源.
 * NOTE-THAT: 目前应用市场事物做在service层级别,故数据源选择也
 * 必须在service层级别,如果设置在DAO层机会出现多个连续DAO操作使用的
 * 可能不是同一个数据源,照成事物失效.
 * 该切面执行的顺序必须在所有切面执行之前.
 */
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    /**
     * 动态数据源选择器 *
     */
    @Resource
    private DynamicDataSourceSelector dynamicDataSourceSelector;

    /**
     * 切数据库操作方法
     *
     * @param point 切点
     * @return 方法返回结果
     * @throws Throwable
     */
    public Object doAroundMethod(ProceedingJoinPoint point) throws Throwable {
        Object response = null;
        logger.info("切换数据源，数据源:{}，目标类:{},目标方法:{},", UserCenterContext.getDataSource(), point.getTarget(), point.getSignature().getName());
        try {
            boolean isBinded = dynamicDataSourceSelector.isBinded();
            //如果当前线程未绑定数据源,则选取数据源
            if (!isBinded) {
                dynamicDataSourceSelector.markDataSource(UserCenterContext.getDataSource());
            }
            response = point.proceed();
        } catch (Exception ex) {
            logger.info("分库切换异常,数据源:{}", UserCenterContext.getDataSource(), ex);
            throw ex;
        } finally {
            if (dynamicDataSourceSelector.isBinded()) { // 方法执行完毕,释放当前数据源
                dynamicDataSourceSelector.remove();
            }
        }
        return response;
    }

}
