package com.warthur.community.common.framework.aop;

import com.warthur.community.common.Constants;
import com.warthur.community.common.Error;
import com.warthur.community.common.framework.cache.DataRedisCache;
import com.warthur.community.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
@Order(-490)
public class SignAuthAspect extends AbstractAspect {

    @Autowired
    private DataRedisCache dataRedisCache;

    @Pointcut("execution(@(org.springframework.web.bind.annotation.RequestMapping || " +
            "org.springframework.web.bind.annotation.GetMapping || " +
            "org.springframework.web.bind.annotation.PostMapping || " +
            "org.springframework.web.bind.annotation.PutMapping || " +
            "org.springframework.web.bind.annotation.DeleteMapping)" +
            "public * com.warthur.community..*.controller..*(..)) " +
            "!execution(public * com.warthur.community..*.controller..login(..))) &&" +
            "!execution(public * com.warthur.community..*.controller..addUser(..)))")
    public void signAuth() {}

    @Pointcut("!execution(@com.warthur.community.common.framework.annotation.AuthExclude " +
            "public * com.warthur.community..*.controller..*(..)))")
    public void excludeMethod() {}

    @Override
    public void doBefore(JoinPoint joinPoint) {

    }

    @Override
    @Around("signAuth() && excludeMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        // 过滤不需要校验的api

        // 接收到请求，记录请求内容
        HttpServletRequest request = getRequest(joinPoint);
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String authorization = request.getHeader(Constants.JWT_TOKEN_HEADER);

        // timestamp合法性校验 误差3s
        if (StringUtils.isEmpty(timestamp) || !validTimestamp(timestamp)) {
            return ResponseUtil.error(Error.TIMESTAMP_ILLEGAL);
        }

        Object userInfo = dataRedisCache.get(Constants.USER_NAMESPACE + authorization);

        String encrySignature = validate(joinPoint, "secret");
        log.info("signautre: {}, encrySignature: {}", signature, encrySignature);

        // 校验签名合法性
        if (StringUtils.isEmpty(signature) || !encrySignature.equals(signature)) {
            return ResponseUtil.error(Error.SIGNATURE_ILLEGAL);
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("签名校验切面异常：{}", throwable);
        }
        return null;
    }

    @Override
    public void doAfter(Object obj) {

    }
}
