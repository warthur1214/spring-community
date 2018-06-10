package com.warthur.community.common.framework.exception;

import com.warthur.community.common.entity.Error;
import com.warthur.community.common.entity.ErrorCode;
import com.warthur.community.common.entity.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author warthur
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

    public BaseException(Error error) {
        this(error.entity().getStatus(), error.entity().getMessage());
    }

}
