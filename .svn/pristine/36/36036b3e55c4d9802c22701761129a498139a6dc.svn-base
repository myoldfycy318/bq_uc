package com.qbao.store.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 删除redis缓存配置
 * @author yewenhai
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheDelConfig {

    String[] key() default "store";

    String[] fieldKey() default {};

}
