package com.warthur.community.wechat.service;

import com.warthur.community.common.Response;
import com.warthur.community.wechat.pojo.param.UserParam;

/**
 * Created by warthur on 2018/5/8.
 */
public interface UserService {

	Response login(String code);

	Response addUserByOpenId(UserParam reqParam);
}
