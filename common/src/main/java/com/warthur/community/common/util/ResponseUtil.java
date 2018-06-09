package com.warthur.community.common.util;


import com.warthur.community.common.*;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * response 工具类
 * @author warthur
 */
public class ResponseUtil {

    public static Response success() {
        return new Response(ErrorCode.REQUEST_SUCCESS);
    }

    public static Response success(BaseDTO dto) {
        return new DataResponse(ErrorCode.REQUEST_SUCCESS, dto);
    }

    public static Response success(String message) {
        return new Response(ErrorCode.REQUEST_SUCCESS.getCode(), message);
    }

    public static Response success(ErrorCode res, BaseDTO dto) {
        return new DataResponse(res, dto);
    }

    public static Response success(String message, BaseDTO dto) {
        return new DataResponse(ErrorCode.REQUEST_SUCCESS.getCode(), message, dto);
    }

    public static Response error(int code, String message) {
        return new Response(code, message);
    }

    public static Response error(ErrorCode exceptionEnum) {
        return new Response(exceptionEnum);
    }

    public static Response error(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        return new ErrorResponse<>(ErrorCode.PARAMS_ERROR, errors);
    }

}
