package com.warthur.community.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by leo on 16/6/6.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /**
     * code 0 - 正确； 非0为错误码
     */
    private int status = 200;
    private String message = "请求成功";
    private T data = null;
    private T desc = null;

    public Response(int code, String message) {
        this.status = code;
        this.message = message;
    }

    public Response(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(Error res, T data) {
        this(res.getCode(), res.getMsg(), data);
    }

}
