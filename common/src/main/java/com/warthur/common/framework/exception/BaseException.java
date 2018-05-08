package com.warthur.common.framework.exception;

/**
 * @Description: 异常基类
 */
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5692243177785821696L;

    private ErrorCode error;
    private Object data = "";

    public BaseException() {
    }

    public BaseException(ErrorCode error) {
        this.error = error;
    }

    public BaseException(ErrorCode error, Object data) {
        this.error = error;
        this.data = data;
    }
    
    public BaseException(ErrorCode error, String msg, Object data) {
    	this.error = error;
    	this.error.setMessage(msg);
    	this.data = data;
    }

    public ErrorCode getError() {
        return error;
    }

	public Object getData() {
		return data;
	}

}
