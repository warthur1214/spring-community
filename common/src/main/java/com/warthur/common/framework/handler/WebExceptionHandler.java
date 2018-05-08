package com.warthur.common.framework.handler;

import com.warthur.common.Response;
import com.warthur.common.framework.exception.ServerException;
import com.warthur.common.framework.exception.WechatException;
import com.warthur.common.util.ResponseUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName: WebExceptionHandler
 * @Description: Web层异常处理器, -- 这里可以根据不同的异常，写多个方法去处理， 可以处理跳转页面请求，跳到异常指定的错误页，
 * 也可以处理Ajax请求，根据不通过异常，在页面输出不同的提示信息 operateExp : 处理普通请求
 * operateExpAjax ： 处理Ajax请求
 */

@ControllerAdvice
@Log4j
public class WebExceptionHandler {

    @ExceptionHandler(value = WechatException.class)
    @ResponseBody
    public Response exceptionGet(WechatException e) {
        return ResponseUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = {ServerException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void serverExcptionGet(ServerException e) {
        log.error(e);
    }
}
