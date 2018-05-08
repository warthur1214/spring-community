package com.warthur.wechat.controller;

import com.warthur.common.framework.annotation.AuthExclude;
import com.warthur.common.framework.exception.WechatException;
import com.warthur.wechat.common.Response;
import com.warthur.wechat.common.util.ResponseUtil;
import com.warthur.wechat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by warthur on 2018/5/8.
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Api("用户控制器")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login/{code}", method = RequestMethod.POST)
	@ApiOperation("登录接口")
	@AuthExclude
	public Response login(@PathVariable String code) {
		Response response;

		try {
			if (StringUtils.isEmpty(code)) {
				return ResponseUtil.error("参数非法");
			}
			response = userService.login(code);
		} catch (WechatException e) {
			log.error("登录失败：{}", e.getMessage());
			return ResponseUtil.error("登录失败: " + e.getMessage());
		}

		return response;
	}
}
