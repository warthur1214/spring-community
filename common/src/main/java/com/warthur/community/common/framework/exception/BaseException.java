package com.warthur.community.common.framework.exception;

import com.warthur.community.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 异常基类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5692243177785821696L;

    private int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        this(ErrorCode.INTERNAL_ERROR.getCode(), message);
    }

    public BaseException(ErrorCode error) {
        this(error.getCode(), error.getMsg());
    }
}
