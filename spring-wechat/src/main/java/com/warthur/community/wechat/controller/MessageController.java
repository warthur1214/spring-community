package com.warthur.community.wechat.controller;

import com.warthur.community.common.entity.Response;
import com.warthur.community.common.framework.annotation.AuthExclude;
import com.warthur.community.common.util.ResponseUtil;
import com.warthur.community.wechat.pojo.param.Message;
import com.warthur.community.wechat.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息处理控制器
 * @author warthur
 * @date 2018/5/10
 */
@RestController
@Api(description = "消息控制器")
@Slf4j
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping(value = "/messages")
	@ApiOperation("发送短信验证码接口")
	@AuthExclude
	public Response sendSmsMessage(@Validated(Message.MsgSend.class) @RequestBody Message message,
								   BindingResult result) {

		messageService.sendSmsMessage(message);

		return ResponseUtil.success();
	}

}
