package com.warthur.community.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * data response
 * @author warthur
 * @date 2018/5/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataResponse extends Response {
	private static final long serialVersionUID = -5063369357722670417L;

	private BaseDTO data;


	public DataResponse(int status, String message, BaseDTO data) {
		super(status, message);
		this.data = data;
	}

	public DataResponse(ErrorCode res, BaseDTO data) {
		super(res);
		this.data = data;
	}
}
