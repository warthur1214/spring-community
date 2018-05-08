package com.warthur.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by admin on 2017/7/7.
 */
@Getter
@AllArgsConstructor
public enum Error {

	REQUEST_SUCCESS(0, "请求成功"),
	REQUEST_ERROR(1, "请求错误"),
	UNKNOW_ERROR(-1, "未知错误"),
	REQUEST_PARAM_ERROR(-2, "请求参数错误！"),
	ILLEGAL_RERUEST(2, "非法请求！"),
	ILLEGAL_TOKEN_EXPIRE(403, "Authorization授权过期！"),
	ILLEGAL_TOKEN_ERROR(403, "Authorization签名验证失败！"),
	BIHU_API_ERROR(3, "壁虎接口请求异常！");

	private Integer code;
	private String msg;

}
