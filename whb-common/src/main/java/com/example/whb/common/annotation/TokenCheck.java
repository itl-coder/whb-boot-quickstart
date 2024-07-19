package com.example.whb.common.annotation;

import java.lang.annotation.*;

/**
 * 对需要Token的地方添加注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenCheck {
    /**
     * 默认是必要的
     *
     * @return
     */
    public boolean required() default true;
}
