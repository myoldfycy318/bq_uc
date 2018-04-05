package com.qbao.store.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhanglb on 6/17/15.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheConfig {
    String key() default "store";

    String fieldKey() default "";

    int expireTime() default 1800;
}
