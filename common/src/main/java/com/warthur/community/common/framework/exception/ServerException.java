package com.warthur.community.common.framework.exception;

import com.warthur.community.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by warth on 2018/5/4.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends BaseException {

	private static final long serialVersionUID = -1535065562881117634L;

	public ServerException(ErrorCode exceptionEnum) {
		super(exceptionEnum.getMsg());
	}

	private ServerException(Integer code, String message) {
		super(message);
	}
}
