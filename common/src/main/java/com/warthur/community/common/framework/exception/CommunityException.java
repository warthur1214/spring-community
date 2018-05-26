package com.warthur.community.common.framework.exception;

import com.warthur.community.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommunityException extends RuntimeException {

	private static final long serialVersionUID = -1535065562881117634L;
	private Integer code;

	public CommunityException(ErrorCode exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.code = exceptionEnum.getCode();
	}

	public CommunityException(String message) {
		this(ErrorCode.INTERNAL_ERROR.getCode(), message);
	}

	public CommunityException(Integer code, String message) {
		super(message);
		this.code = code;
	}

}
