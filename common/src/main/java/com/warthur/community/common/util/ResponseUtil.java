package com.warthur.community.common.util;


import com.warthur.community.common.*;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arthur wu
 * on 2017/3/22.
 */
public class ResponseUtil {

    public static Response success() {
        return new Response(ErrorCode.REQUEST_SUCCESS);
    }

    public static Response success(BaseDTO dto) {
        return new DataResponse<>(ErrorCode.REQUEST_SUCCESS, dto);
    }

    public static Response success(String message) {
        return success(message, null);
    }

    public static Response success(ErrorCode res) {
        return success(res, null);
    }

    public static Response success(ErrorCode res, BaseDTO dto) {
        return new DataResponse<>(res, dto);
    }

    public static Response success(String message, BaseDTO dto) {
        return new DataResponse<>(ErrorCode.REQUEST_SUCCESS.getCode(), message, dto);
    }

    public static Response error(Integer code, String msg) {
        return error(code, msg, null);
    }

    public static Response error(Integer code, String msg, Object data) {
        return new DataResponse<>(code, msg, data);
    }

    public static Response error(ErrorCode exceptionEnum) {
        return new Response(exceptionEnum);
    }

    public static Response error(String message) {
        return error(ErrorCode.REQUEST_ERROR.getCode(), message);
    }

    public static Response error(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        return new ErrorResponse<>(ErrorCode.PARAMS_ERROR, errors);
    }

}
