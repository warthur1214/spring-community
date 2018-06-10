package com.warthur.community.wechat.service.impl;

import com.warthur.community.common.bean.UserInfo;
import com.warthur.community.common.framework.exception.CommunityException;
import com.warthur.community.common.util.ResponseUtil;
import com.warthur.community.wechat.WechatApplication;
import com.warthur.community.wechat.common.WeChatErrorCode;
import com.warthur.community.wechat.pojo.param.UserParam;
import com.warthur.community.wechat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * user服务接口实现类
 * @author warthur
 * @date 2018/5/8
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Override
	public void login(String code) {

		if (true) {
			throw new CommunityException(WeChatErrorCode.SMS_SEND_SUCCESS);
		}

		// 根据code获取openid

		// 根据openid查询userInfo

		// 根据userInfo查询token，有且校验jwt是否过期，否则返回userInfo+token+freshToken

		// 生成签名密钥，token，refreshToken并存储到redis

		// 返回userInfo + jwtToken

	}

	@Override
	public UserInfo addUserByOpenId(UserParam reqParam) {
		// 校验短信验证码

		// 根据code获取openId

		// 查询数据库是否注册过，有则获取token和userInfo返回

		// 存储用户信息

		// 生成签名密钥，token，refreshToken并存储到redis

		// 返回userInfo + jwtToken
		return null;
	}
}
