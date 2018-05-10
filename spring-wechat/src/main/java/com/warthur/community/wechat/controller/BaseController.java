package com.warthur.community.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by warthur on 2018/5/8.
 */
@RestController
@Slf4j
public class BaseController {

	@Resource
	protected HttpServletRequest request;

	@Resource
	protected HttpServletResponse response;
}
