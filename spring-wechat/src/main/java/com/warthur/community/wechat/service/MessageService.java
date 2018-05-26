package com.warthur.community.wechat.service;

import com.warthur.community.wechat.pojo.param.Message;

/**
 * Created by warthur on 2018/5/10.
 */
public interface MessageService {

	void sendSmsMessage(Message message);
}
