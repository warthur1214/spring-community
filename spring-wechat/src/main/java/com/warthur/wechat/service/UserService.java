package com.warthur.wechat.service;

import com.warthur.wechat.common.Response;
import com.warthur.wechat.pojo.param.LoginParam;

/**
 * Created by warthur on 2018/5/8.
 */
public interface UserService {

	Response login(LoginParam reqParam);
}
