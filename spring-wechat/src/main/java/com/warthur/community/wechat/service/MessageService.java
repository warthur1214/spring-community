package com.warthur.community.wechat.service;

import com.warthur.community.wechat.pojo.param.Message;

/**
 * 消息服务接口
 * @author warthur
 * @date 2018/5/10
 */
public interface MessageService {

	/**
	 * 发送短信接口
	 * @param message message object
	 */
	void sendSmsMessage(Message message);
}
