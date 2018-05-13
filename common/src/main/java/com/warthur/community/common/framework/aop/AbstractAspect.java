package com.warthur.community.common.framework.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

abstract class AbstractAspect implements CommunityAspect {

    HttpServletRequest getRequest(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    private Map<String, Object> handleQueryMap(JoinPoint joinPoint) {
        HttpServletRequest request = getRequest(joinPoint);
        Map<String, Object> requestMap = new TreeMap<>();

        // 获取requestBody，放入treeMap
        if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
            requestMap = new ObjectMapper().convertValue(joinPoint.getArgs()[0], TreeMap.class);
        }

        // 获取 query string，放入treeMap
        requestMap.putAll(request.getParameterMap());

        // 剔除signature
        requestMap.remove("signature");

        // 复杂对象的值只拿size
        for (Map.Entry<String, Object> entry: requestMap.entrySet()) {

            if (entry.getValue() instanceof String[]) {
                List<String> values = Arrays.asList((String[]) entry.getValue());
                entry.setValue(values.get(0));
                if (values.size() > 1) {
                    entry.setValue(values.size());
                }
            }

            if (entry.getValue() instanceof Map) {
                entry.setValue(((Map) entry.getValue()).size());
            }
            if (entry.getValue() instanceof List) {
                entry.setValue(((List) entry.getValue()).size());
            }
        }

        return requestMap;
    }

    String validate(JoinPoint joinPoint, String secret) {
        Map<String, Object> map = handleQueryMap(joinPoint);
        String beforeValid = secret + ":" + Base64.getEncoder().encodeToString(Joiner.on("&").withKeyValueSeparator("=").join(map).getBytes());
        return DigestUtils.sha1Hex(beforeValid.getBytes());
    }
}
