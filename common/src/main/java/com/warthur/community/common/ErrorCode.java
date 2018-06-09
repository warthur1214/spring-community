package com.warthur.community.common;

import com.warthur.community.common.framework.exception.CommunityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统响应状态码
 * @author admin
 * @date 2017/7/7
 */
@Getter
@AllArgsConstructor
public enum ErrorCode implements ErrorEntity {

	REQUEST_SUCCESS(200, "请求成功"),
	PARAMS_ERROR(400, "参数错误"),
	UNAUTHORIZED_ERROR(401, "Authorization未认证"),
	FORBIDDEN_ERROR(403, "禁止访问，Authorization异常"),
	NOT_FOUND_ERROR(404, "未找到"),
	INTERNAL_ERROR(500, "系统错误"),

	REQUEST_ERROR(-1000, "处理失败"),
	VERSION_ILLEGAL(-1001, "请求头Version非法"),
	SIGNATURE_ILLEGAL(-1002, "请求参数signature非法"),
	TIMESTAMP_ILLEGAL(-1003, "请求参数timestamp非法"),
	API_OUT_LIMITS(-1004, "请求过于频繁，请稍等片刻重试");

	private Integer code;
	private String msg;

	@Override
	public CommunityException exception() {
		return new CommunityException(this.code, this.msg);
	}

	@Override
	public Response success(BaseDTO baseDTO) {
		return new DataResponse(this.code, this.msg, baseDTO);
	}

	@Override
	public Response success() {
		return new Response(this.code, this.msg);
	}


}
