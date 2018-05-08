package com.warthur.common.framework.exception;

import com.warthur.common.Error;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WechatException extends RuntimeException {

	private static final long serialVersionUID = -1535065562881117634L;
	private Integer code;

	public WechatException(Error exceptionEnum) {
		super(exceptionEnum.getMsg());
		this.code = exceptionEnum.getCode();
	}

	public WechatException(String msg) {
		this(msg, 3);
	}

	public WechatException(String message, Integer code) {
		super(message);
		this.code = code;
	}

}
