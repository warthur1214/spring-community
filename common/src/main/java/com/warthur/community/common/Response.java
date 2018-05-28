package com.warthur.community.common;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by leo on 16/6/6.
 */
@Data
@AllArgsConstructor
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 5690428646731343292L;

    /**
     * code 0 - 正确； 非0为错误码
     */
    private Integer status;
    private String message;

    public Response(ErrorCode res) {
        this(res.getCode(), res.getMsg());
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

}
