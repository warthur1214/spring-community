package com.warthur.community.wechat.service.impl;

import com.warthur.community.common.Response;
import com.warthur.community.common.util.ResponseUtil;
import com.warthur.community.wechat.pojo.param.Message;
import com.warthur.community.wechat.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by warthur on 2018/5/10.
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

	@Override
	public Response sendSmsMessage(Message message) {

		// 根据code查询openId

		// 随机生成6位短信验证码

		// 存储短信记录到数据库

		// 发送短信

		return ResponseUtil.success();
	}
}
