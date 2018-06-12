package com.warthur.community.common.framework.aop;

import com.warthur.community.common.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author warthur
 */
@Component
@Aspect
@Slf4j
@Order(300)
public class BindingResultAspect {

    @Pointcut(value = "execution(@(org.springframework.web.bind.annotation.RequestMapping || " +
            "org.springframework.web.bind.annotation.GetMapping || " +
            "org.springframework.web.bind.annotation.PostMapping || " +
            "org.springframework.web.bind.annotation.PutMapping || " +
            "org.springframework.web.bind.annotation.DeleteMapping) " +
            "public * com.warthur.community..*.controller..*(..)) " +
            "&& args(..,result)")
    public void bindingResultError(BindingResult result) {}

    @Around(value = "bindingResultError(result)", argNames = "joinPoint, result")
    public Object doAround(ProceedingJoinPoint joinPoint, BindingResult result) {

        // 判断
        if (result.hasErrors()) {
            return ResponseUtil.error(result);
        }

        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("BindingResultAspect error: {}", throwable);
        }
        return null;
    }

}
