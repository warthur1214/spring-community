package com.warthur.community.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by warthur on 2018/5/28.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataResponse<T> extends Response {
	private static final long serialVersionUID = -5063369357722670417L;

	private T data;


	public DataResponse(int status, String message, T data) {
		super(status, message);
		this.data = data;
	}

	public DataResponse(ErrorCode res, T data) {
		super(res);
		this.data = data;
	}
}
