package com.warthur.community.common.framework.exception;

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

	private ServerException(Integer code, String message) {
		super(code, message);
	}
}
