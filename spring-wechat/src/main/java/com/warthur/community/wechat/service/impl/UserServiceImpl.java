package com.warthur.community.wechat.service.impl;

import com.warthur.community.common.Response;
import com.warthur.community.wechat.pojo.param.LoginParam;
import com.warthur.community.wechat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by warthur on 2018/5/8.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Override
	public Response login(LoginParam reqParam) {

		// 校验短信验证码

		// 根据code获取openid

		// 根据openid存储手机号

		// 生成secret

		// 返回userInfo + jwtToken
		return null;
	}

	@Override
	public Response sendSmsMessage() {
		return null;
	}
}
