package com.qbao.store.aspect;

import com.bqiong.usercenter.annotation.DefDataSource;
import com.qbao.store.util.UserCenterContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 默认数据源切换
 * DefDataSourceAspect
 *
 * @author Zhang ShanMin
 * @date 2017/7/19
 * @time 18:01
 */
@Aspect
@Component("defDataSourceAspect")
@Order(0)//执行权重 最小最先执行
public class DefDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DefDataSourceAspect.class);


    @Around("@annotation(com.bqiong.usercenter.annotation.DefDataSource)")
    public Object arround(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("默认数据源--before invoke Object:" + pjp.getTarget() + ",Method:" + pjp.getSignature().getName());
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        DefDataSource operateType = method.getAnnotation(DefDataSource.class);
        UserCenterContext.setDataSource(operateType.datasource());//切换数据源使用
        return pjp.proceed();
    }

}
