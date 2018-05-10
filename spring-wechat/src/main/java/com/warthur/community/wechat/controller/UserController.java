package com.warthur.community.wechat.controller;

import com.warthur.community.common.Response;
import com.warthur.community.common.framework.annotation.AuthExclude;
import com.warthur.community.common.framework.exception.WechatException;
import com.warthur.community.common.util.ResponseUtil;
import com.warthur.community.wechat.pojo.param.LoginParam;
import com.warthur.community.wechat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiOperation("登录接口")
	@AuthExclude
	public Response login(@Validated @RequestBody LoginParam reqParam, BindingResult result) {
		Response response;

		try {
			if (result.hasErrors()) {
				return ResponseUtil.error(result);
			}
			response = userService.login(reqParam);
		} catch (WechatException e) {
			log.error("登录失败：{}", e.getMessage());
			return ResponseUtil.error("登录失败: " + e.getMessage());
		}

		return response;
	}

	@RequestMapping(value = "/sms/send/{tel}")
	@ApiOperation("发送短信验证码接口")
	@AuthExclude
	public Response sendSmsMessage(@PathVariable String tel) {
		Response response;

		try {
			response = userService.sendSmsMessage();
		} catch (WechatException e) {
			log.error("发送短信失败：{}", e.getMessage());
			return ResponseUtil.error("发送短信失败: " + e.getMessage());
		}

		return response;
	}
}
