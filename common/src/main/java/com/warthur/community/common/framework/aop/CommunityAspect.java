package com.warthur.community.common.framework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public interface CommunityAspect {

    void doBefore(JoinPoint joinPoint);

    Object doAround(ProceedingJoinPoint joinPoint);

    void doAfter(Object obj);
}
