package com.warthur.community.wechat.controller;

import com.warthur.community.common.Error;
import com.warthur.community.common.Response;
import com.warthur.community.common.framework.annotation.AuthExclude;
import com.warthur.community.common.framework.exception.WechatException;
import com.warthur.community.common.util.ResponseUtil;
import com.warthur.community.wechat.pojo.param.UserParam;
import com.warthur.community.wechat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by warthur on 2018/5/8.
 */
@RestController
@Slf4j
@Api(description = "用户控制器")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/users/{code}")
	@ApiOperation("登录接口")
	@AuthExclude
	public Response login(@PathVariable String code) {
		Response response;

		try {
			if (StringUtils.isEmpty(code)) {
				return ResponseUtil.error(Error.PARAMS_ERROR);
			}
			response = userService.login(code);
		} catch (WechatException e) {
			log.error("登录失败：{}", e.getMessage());
			return ResponseUtil.error("登录失败: " + e.getMessage());
		}

		return response;
	}

	@PostMapping("/users")
	@ApiOperation("注册绑定用户手机号")
	@AuthExclude
	public Response addUser(@Validated @RequestBody UserParam reqParam, BindingResult result) {
		Response response;

		try {
			if (result.hasErrors()) {
				return ResponseUtil.error(result);
			}
			response = userService.addUserByOpenId(reqParam);
		} catch (WechatException e) {
			log.error("注册失败：{}", e.getMessage());
			return ResponseUtil.error("注册失败: " + e.getMessage());
		}

		return response;
	}

}
