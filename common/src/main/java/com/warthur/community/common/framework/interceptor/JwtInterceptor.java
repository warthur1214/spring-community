package com.warthur.community.common.framework.interceptor;

import com.warthur.community.common.Constants;
import com.warthur.community.common.Error;
import com.warthur.community.common.framework.annotation.AuthExclude;
import com.warthur.community.common.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		//无需授权注解标记 类级+方法级
		if (handlerMethod.getBean().getClass().getAnnotation(AuthExclude.class) != null &&
				handlerMethod.getMethod().getAnnotation(AuthExclude.class) != null) {
			log.info("无需校验token可直接访问!" + request.getServletPath());
			return true;
		}

		response.setHeader("Content-Type", "application/json;charset=UTF-8");

		final String authorizationToken = request.getHeader(Constants.JWT_TOKEN_HEADER);
		log.info("Authorization：{}", authorizationToken);
		try {
			if (StringUtils.isEmpty(authorizationToken)) {
				throw new SignatureException("请求头Authorization缺失！");
			}

			return true;
		} catch (SignatureException e) {
			log.error("授权token异常：" + e.getMessage());
			response.getWriter().print(ResponseUtil.error(Error.UNAUTHORIZED_ERROR).toString());
		} catch (ExpiredJwtException e) {
			log.error("授权检查过期：" + e.getMessage());
			response.getWriter().print(ResponseUtil.error(Error.FORBIDDEN_ERROR).toString());
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {

	}
}
