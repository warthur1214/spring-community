package com.warthur.wechat.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by warthur on 2018/5/8.
 */
@RestController
public class BaseController {

	@Resource
	private HttpServletRequest request;

	@Resource
	private HttpServletResponse response;
}
