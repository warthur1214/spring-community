package com.warthur.community.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by admin on 2017/7/7.
 */
@Getter
@AllArgsConstructor
public enum Error {

	REQUEST_SUCCESS(200, "请求成功"),
	HANDLE_SUCCESS(201, "创建或修改成功"),
	DELETE_SUCCESS(204, "删除成功"),
	PARAMS_ERROR(400, "参数错误"),
	UNAUTHORIZED_ERROR(401, "Authorization验证失败"),
	FORBIDDEN_ERROR(403, "禁止访问，Authorization异常"),
	NOT_FOUND_ERROR(404, "未找到"),
	INTERNAL_ERROR(500, "系统错误"),

	REQUEST_ERROR(-1000, "处理失败"),
	VERSION_NULL_ERROR(-1001, "请求Version非法");

	private Integer code;
	private String msg;

}
