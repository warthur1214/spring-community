package com.warthur.community.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by leo on 16/6/6.
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -5107029250213698775L;
    /**
     * code 200 - 正确； 非0为错误码
     */
    private boolean success;
    private int status;
    private String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.success = this.status == 200 || this.status >= 1000;
    }

    public Response(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
