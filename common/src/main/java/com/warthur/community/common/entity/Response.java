package com.warthur.community.common.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * response object
 * @author warthur
 * @date 16/6/6
 */
@Data
public class Response implements Error, Serializable {

    private static final long serialVersionUID = -5107029250213698775L;

    private boolean success;
    private int status;
    private String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        this.success = this.status == 200 || this.status >= 1000;
    }

    public Response(Error error) {
        this(((Response) error.entity()).getStatus(), ((Response) error.entity()).getMessage());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public Response entity() {
        return this;
    }

    public static Response valueOf(int code, String message) {
        return new Response(code, message);
    }
}
