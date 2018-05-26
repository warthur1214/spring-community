package com.warthur.community.wechat.service;

import com.warthur.community.common.bean.UserInfo;
import com.warthur.community.wechat.pojo.param.UserParam;

/**
 * Created by warthur on 2018/5/8.
 */
public interface UserService {

	void login(String code);

	UserInfo addUserByOpenId(UserParam reqParam);
}
