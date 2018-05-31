package com.warthur.community.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by warthur on 2018/5/28.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponse<T> extends Response {
	private static final long serialVersionUID = -2832200841230171173L;

	private T desc;

	public ErrorResponse(ErrorCode res, T desc) {
		super(res);
		this.desc = desc;
	}
}
