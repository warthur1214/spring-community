package com.warthur.common.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.warthur.common.Constants;
import com.warthur.common.Error;
import com.warthur.common.framework.annotation.AuthExclude;
import com.warthur.common.util.JwtUtil;
import com.warthur.common.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import weixin.popular.bean.sns.Jscode2sessionResult;

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
		if (handlerMethod.getBean().getClass().getAnnotation(AuthExclude.class) == null ||
				handlerMethod.getMethod().getAnnotation(AuthExclude.class) == null) {
			log.info("无需校验token可直接访问!" + request.getServletPath());
			return true;
		}

		final String authorizationToken = request.getHeader(Constants.JWT_TOKEN_HEADER);
		log.info("authHeader=" + authorizationToken);
		try {
			if (StringUtils.isEmpty(authorizationToken)) {
				throw new SignatureException("请求头Authorization缺失！");
			}

			Jscode2sessionResult result = JSONObject.parseObject(JwtUtil.decryJwtToken(authorizationToken), Jscode2sessionResult.class);

			log.info("openId: {}, session_key: {}", result.getOpenid(), result.getSession_key());

			return true;
		} catch (SignatureException e) {
			log.error("授权token异常：" + e.getMessage());
			response.getWriter().print(JSON.toJSONString(ResponseUtil.error(403, e.getMessage())));
		} catch (ExpiredJwtException e) {
			log.error("授权检查过期：" + e.getMessage());
			response.getWriter().print(JSON.toJSONString(ResponseUtil.error(Error.ILLEGAL_TOKEN_ERROR)));
		}
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

	}
}
