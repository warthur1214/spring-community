package com.warthur.community.common.framework.aop;

import com.warthur.community.common.Constants;
import com.warthur.community.common.entity.ErrorCode;
import com.warthur.community.common.framework.annotation.ApiLimit;
import com.warthur.community.common.framework.cache.StringRedisCache;
import com.warthur.community.common.framework.exception.CommunityException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * api 请求次数限制切面
 */
@Component
@Aspect
@Order(50)
@Slf4j
public class ApiLimitAspect extends AbstractAspect {

    @Autowired
    private StringRedisCache stringRedisCache;

    @Pointcut(value = "execution(@(com.warthur.community.common.framework.annotation.ApiLimit) " +
            "public * com.warthur.community..*.controller..*(..))")
    public void apiLimit() {}

    @Override
    @Before(value = "apiLimit()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = getRequest(joinPoint);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiLimit apiLimit = method.getAnnotation(ApiLimit.class);

        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String redisKey = Constants.API_REQUEST_LIMITS + uri + ":" + ip;

        long count = stringRedisCache.incr(Constants.API_REQUEST_LIMITS + uri + ":" + ip);
        if (count == 1) {
            stringRedisCache.expire(redisKey, apiLimit.times());
        }

        // 如果redis中的count大于限制的次数，则报错
        if (count > apiLimit.counts()) {
            throw new CommunityException(ErrorCode.API_OUT_LIMITS);
        }
    }

    @Override
    public Object doAround(ProceedingJoinPoint joinPoint) {
        return null;
    }

    @Override
    public void doAfter(Object obj) {

    }
}
