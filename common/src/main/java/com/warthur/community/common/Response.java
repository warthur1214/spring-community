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
    private int code = 0;
    private String message = "请求成功";
    private T data ;
    
    public Response(Error errorCode) {
    	this.code = errorCode.getCode();
    	this.message = errorCode.getMsg();
    }

    public Response(T data) {
        this(Error.REQUEST_SUCCESS);
        this.data = data;
    }

    public Response(Error res, T data) {
        this(res.getCode(), res.getMsg(), data);
    }

    public Response(int code, String message) {
        this(code, message, null);
    }

    public static Response error(String message) {
        return new Response(1, message);
    }

    public static Response error(int code,String message) {
        return new Response(code,message);
    }
}
