package com.warthur.community.common.framework.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warthur.community.common.Constants;
import com.warthur.community.common.framework.exception.CommunityException;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("unchecked")
abstract class AbstractAspect implements CommunityAspect {

    HttpServletRequest getRequest(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    private List<String> handleQueryMap(JoinPoint joinPoint) {
        HttpServletRequest request = getRequest(joinPoint);
        Map<String, Object> requestMap = new HashMap<>();

        // 获取requestBody，放入treeMap
        if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
            requestMap = new ObjectMapper().convertValue(joinPoint.getArgs()[0], TreeMap.class);
        }

        // 获取 query string，放入treeMap
        List<String> requestList = new ArrayList<>(Arrays.asList(request.getQueryString().split("&")));

        // 剔除signature
        requestList.remove("signature=" + request.getParameter("signature"));

        // 复杂对象的值只拿size
        for (Map.Entry<String, Object> entry: requestMap.entrySet()) {
            StringBuilder params = new StringBuilder(entry.getKey()).append("=");
            if (entry.getValue() instanceof Map) {
                params.append(((Map) entry.getValue()).size());
            } else if (entry.getValue() instanceof List) {
                params.append(((List) entry.getValue()).size());
            } else {
                params.append(entry.getValue());
            }

            requestList.add(params.toString());
        }

        Collections.sort(requestList);

        return requestList;
    }

    String validate(JoinPoint joinPoint, String secret) {
        List<String> params = handleQueryMap(joinPoint);
        String beforeValid = secret + ":" + Base64.getEncoder().encodeToString(String.join("&", params).getBytes());
        return DigestUtils.sha1Hex(beforeValid.getBytes());
    }

    /**
     * 判断timestamp参数与当前时间戳误差是否超过3s
     * @param timeParam timestamp
     * @return
     */
    boolean validTimestamp(String timeParam) {
        Long ts = Long.parseLong(timeParam);
        Long nts = System.currentTimeMillis();

        return Math.abs(nts - ts) <= Constants.TIMESTAMP_DEVIATION;
    }
}
