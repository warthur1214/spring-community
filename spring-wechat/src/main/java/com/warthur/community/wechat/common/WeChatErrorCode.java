package com.warthur.community.wechat.common;


import com.warthur.community.common.entity.Error;
import com.warthur.community.common.entity.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author warthur
 */
@AllArgsConstructor
@Getter
public enum WeChatErrorCode implements Error {

    SMS_SEND_SUCCESS(1000, "短信发送成功！");

    private Integer code;
    private String msg;

    @Override
    public Response entity() {
        return Response.valueOf(this.code, this.msg);
    }
}
