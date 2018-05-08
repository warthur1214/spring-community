package com.warthur.common.framework.exception;

import com.warthur.common.Error;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by warth on 2018/5/4.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends RuntimeException {

	private static final long serialVersionUID = -1535065562881117634L;
	private Integer code;

	public ServerException(Error exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.code = exceptionEnum.getCode();
	}

	public ServerException(String msg) {
		this(msg, 500);
	}

	public ServerException(String message, Integer code) {
		super(message);
		this.code = code;
	}
}
