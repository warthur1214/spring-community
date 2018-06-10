package com.warthur.community.wechat.service;

import com.warthur.community.common.bean.UserInfo;
import com.warthur.community.wechat.pojo.param.UserParam;

/**
 * user服务类
 * @author warthur
 * @date 2018/5/8
 */
public interface UserService {

	/**
	 * 登录接口
	 * @param code wechat unique code
	 */
	void login(String code);

	/**
	 * 注册接口
	 * @param reqParam userInfo
	 * @return userInfo without token
	 */
	UserInfo addUserByOpenId(UserParam reqParam);
}
