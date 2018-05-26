package com.warthur.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.warthur.community.common.ErrorCode;
import com.warthur.community.common.util.ResponseUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 校验Authorization, signature过滤器
 * Created by warthur on 2018/5/12.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@Component
@RefreshScope
@ConfigurationProperties(prefix = "zuul.request")
public class AuthZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private List<String> versions;

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        // 获取Version header
        String version = request.getHeader("Version");
        log.info("Version:" + version);
        if (version == null || version.equals("") || !versions.contains(version)) {
            //Version 校验失败
            log.error("Version验证失败");
            HttpServletResponse response = ctx.getResponse();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");

            ctx.setSendZuulResponse(false); //不进行路由
            try {
                response.getWriter().write(ResponseUtil.error(ErrorCode.VERSION_ILLEGAL).toString()); //响应体
            } catch (IOException e) {
                log.error("response io异常", e);
            }
            ctx.setResponse(response);
            return null;
        }
        log.info("Version验证成功");
        return null;
    }
}
