package com.warthur.wechat.service;

import com.warthur.wechat.common.Response;

/**
 * Created by warthur on 2018/5/8.
 */
public interface UserService {

	Response login(String code);
}
