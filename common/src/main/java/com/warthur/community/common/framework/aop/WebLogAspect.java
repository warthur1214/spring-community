package com.warthur.community.common.framework.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
@Order(-500)
public class WebLogAspect extends AbstractAspect {

	@Pointcut("execution(@(org.springframework.web.bind.annotation.RequestMapping || " +
			"org.springframework.web.bind.annotation.GetMapping || " +
			"org.springframework.web.bind.annotation.PostMapping || " +
			"org.springframework.web.bind.annotation.PutMapping || " +
			"org.springframework.web.bind.annotation.DeleteMapping) " +
            "public * com.warthur.community..*.controller..*(..))")
	public void webLog() {}

	@Override
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
		HttpServletRequest request = getRequest(joinPoint);

		// 记录下请求内容
		log.info("REQUEST: {} -> {}", request.getMethod(), request.getRequestURL());
		log.info("请求控制器方法: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		log.info("请求参数: {}", Arrays.toString(joinPoint.getArgs()));
	}

	@Override
	public Object doAround(ProceedingJoinPoint joinPoint) {
		return null;
	}

	@Override
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfter(Object ret) {
		// 处理完请求，返回内容
		log.info("RESPONSE : " + JSON.toJSONString(ret));
	}
}
