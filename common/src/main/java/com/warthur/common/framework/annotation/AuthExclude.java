package com.warthur.common.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by warth on 2018/4/10.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuthExclude {
}
