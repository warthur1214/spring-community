package com.warthur.wechat.service.impl;

import com.warthur.wechat.common.Response;
import com.warthur.wechat.service.UserService;
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

		// 根据openid获取userId

		// 返回userInfo + jwtToken
		return null;
	}
}
