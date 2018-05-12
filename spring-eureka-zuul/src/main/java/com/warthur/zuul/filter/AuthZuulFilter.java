package com.warthur.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验Authorization, signature过滤器
 * Created by warthur on 2018/5/12.
 */
@Slf4j
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

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		String token = request.getParameter("token");
		log.info("token:"+token);
		if (!token.equals("success_token")){
			//认证失败
			log.error("token验证失败");
			HttpServletResponse response = ctx.getResponse();
			response.setCharacterEncoding("utf-8");  //设置字符集
			response.setContentType("text/html; charset=utf-8"); //设置相应格式
			response.setStatus(401);
			ctx.setSendZuulResponse(false); //不进行路由
			try {
				response.getWriter().write("token 验证失败"); //响应体
			} catch (IOException e) {
				log.error("response io异常");
				e.printStackTrace();
			}
			ctx.setResponse(response);
			return null;
		}
		log.info("token验证成功");
		return null;
	}
}
