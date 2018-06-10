package com.warthur.community.common.framework.exception;

import com.warthur.community.common.entity.Error;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统异常类
 * @author warth
 * @date 2018/5/4
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerException extends BaseException {

	private static final long serialVersionUID = -1535065562881117634L;

	public ServerException(Error error) {
		super(error);
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(Integer code, String message) {
		super(code, message);
	}
}
