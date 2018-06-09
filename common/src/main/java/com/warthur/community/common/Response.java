package com.warthur.community.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * response object
 * @author warthur
 * @date 16/6/6
 */
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -5107029250213698775L;

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
