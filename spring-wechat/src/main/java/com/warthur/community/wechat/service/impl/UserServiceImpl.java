package com.warthur.community.wechat.service.impl;

import com.warthur.community.common.Response;
import com.warthur.community.common.util.ResponseUtil;
import com.warthur.community.wechat.pojo.param.UserParam;
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
	public Response login(String code) {

		// 根据code获取openid

		// 根据openid查询userInfo

		// 根据userInfo查询token，有效则返回userInfo+token+freshToken

		// 生成签名密钥，token，refreshToken并存储到redis

		// 返回userInfo + jwtToken
		return ResponseUtil.success();
	}

	@Override
	public Response addUserByOpenId(UserParam reqParam) {
		// 校验短信验证码

		// 根据code获取openId

		// 存储用户信息

		// 生成签名密钥，token，refreshToken并存储到redis

		// 返回userInfo + jwtToken
		return ResponseUtil.success();
	}
}
