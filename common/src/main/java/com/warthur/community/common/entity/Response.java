package com.warthur.community.common.entity;

import com.alibaba.fastjson.JSON;
import com.warthur.community.common.framework.exception.CommunityException;
import com.warthur.community.common.framework.exception.ServerException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * response object
 * @author warthur
 * @date 16/6/6
 */
@Data
@Slf4j
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public static Response valueOf(int code, String message) {
        return new Response(code, message);
    }
}
