package com.warthur.community.common.framework.exception;

import com.warthur.community.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author warthur
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommunityException extends BaseException {

	private static final long serialVersionUID = -1535065562881117634L;

	public CommunityException(ErrorCode exceptionEnum) {
		super(exceptionEnum.getMsg());
	}

	public CommunityException(int code, String message) {
		super(code, message);
	}
}
