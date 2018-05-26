package com.warthur.community.common.framework.aop;

import com.warthur.community.common.framework.annotation.ApiLimit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;

public interface CommunityAspect {

    void doBefore(JoinPoint joinPoint);

    Object doAround(ProceedingJoinPoint joinPoint);

    void doAfter(Object obj);
}
