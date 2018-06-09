package com.warthur.community.wechat.common;


import com.warthur.community.common.BaseDTO;
import com.warthur.community.common.DataResponse;
import com.warthur.community.common.ErrorEntity;
import com.warthur.community.common.Response;
import com.warthur.community.common.framework.exception.CommunityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author warthur
 */
@AllArgsConstructor
@Getter
public enum WeChatErrorCode implements ErrorEntity {

    SMS_SEND_SUCCESS(1000, "短信发送成功！");

    private Integer code;
    private String msg;

    @Override
    public CommunityException exception() {
        return new CommunityException(this.code, this.msg);
    }

    @Override
    public Response success(BaseDTO baseDTO) {
        return new DataResponse(this.code, this.msg, baseDTO);
    }

    @Override
    public Response success() {
        return new Response(this.code, msg);
    }


}
