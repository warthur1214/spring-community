package com.warthur.community.common.framework.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)        // 设置顺序为最高优先级
public @interface ApiLimit {

    /**
     *
     * @Description: 限制某时间段内可以访问的次数，默认设置100
     * @return
     *
     * @author leechenxiang
     * @date 2016年12月14日 下午8:22:29
     */
    int counts() default 100;

    /**
     *
     * @Description: 限制访问的某一个时间段，单位为秒，默认值1分钟即可
     * @return
     *
     * @author leechenxiang
     * @date 2016年12月14日 下午8:21:59
     */
    int times() default 60;
}
