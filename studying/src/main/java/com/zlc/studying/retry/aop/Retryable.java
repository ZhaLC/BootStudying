package com.zlc.studying.retry.aop;

import java.lang.annotation.*;

/**
 * @create : 2020-10-24 11:16
 * @desc :
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retryable {

    /**
     * Exception的才retry 默认RuntimeException.class
     * @return
     */
    Class<? extends Throwable> value() default RuntimeException.class;

    /**
     * 重试次数 默认3
     * @return
     */
    int maxAttempts() default 3;
}
