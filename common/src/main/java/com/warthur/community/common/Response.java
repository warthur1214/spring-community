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
public class Response implements Serializable {

    private static final long serialVersionUID = 5690428646731343292L;

    /**
     * code 200 - 正确； 非0为错误码
     */
    private Integer status;
    private String message;

    public boolean isSuccess() {
        return this.status != null && this.status == 200 && this.status >= 1000;
    }

    public Response(ErrorCode res) {
        this(res.getCode(), res.getMsg());
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

}
