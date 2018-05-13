package com.warthur.community.common.framework.aop;

import com.warthur.community.common.Error;
import com.warthur.community.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Aspect
@Slf4j
@Order(-490)
public class SignAuthAspect extends AbstractAspect {

    @Pointcut("execution(public * com.warthur.community..*.controller..*(..))")
    public void signAuth() {}

    @Override
    public void doBefore(JoinPoint joinPoint) {

    }

    @Override
    @Around("signAuth()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        HttpServletRequest request = getRequest(joinPoint);
        String signature = request.getParameter("signature");

        String encrySignature = validate(joinPoint, "123456");
        log.info("signautre: {}, encrySignature: {}", signature, encrySignature);

        // 校验签名合法性
        if (signature == null || signature.equals("") || !encrySignature.equals(signature)) {
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
