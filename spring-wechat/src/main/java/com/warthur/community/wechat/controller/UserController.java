package com.warthur.community.wechat.controller;

import com.warthur.community.common.entity.ErrorCode;
import com.warthur.community.common.entity.Response;
import com.warthur.community.common.bean.UserInfo;
import com.warthur.community.common.framework.annotation.AuthExclude;
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
 * 用户控制器
 * @author warthur
 * @date 2018/5/8
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

		if (StringUtils.isEmpty(code)) {
            return ResponseUtil.error(ErrorCode.PARAMS_ERROR);
        }

		userService.login(code);
		return ResponseUtil.success();
	}

	@PostMapping("/users")
	@ApiOperation("注册绑定用户手机号")
	@AuthExclude
	public Response addUser(@Validated @RequestBody UserParam reqParam, BindingResult result) {

		if (result.hasErrors()) {
            return ResponseUtil.error(result);
        }
		UserInfo userInfo = userService.addUserByOpenId(reqParam);

		return ResponseUtil.success(userInfo);
	}

}
