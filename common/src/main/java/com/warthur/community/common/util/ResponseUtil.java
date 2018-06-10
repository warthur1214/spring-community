package com.warthur.community.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.warthur.community.common.*;
import com.warthur.community.common.entity.*;
import com.warthur.community.common.entity.Error;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

/**
 * response 工具类
 * @author warthur
 */
public class ResponseUtil {

    public static Response success() {
        return Response.valueOf(ErrorCode.REQUEST_SUCCESS.getCode(), ErrorCode.REQUEST_SUCCESS.getMsg());
    }

    public static Response success(Error error) {
        return error.entity();
    }

    public static Response success(BaseDTO dto) {
        return new DataResponse(ErrorCode.REQUEST_SUCCESS, dto);
    }

    public static Response success(Error error, BaseDTO baseDTO) {
        return new DataResponse(error, baseDTO);
    }

    public static Response error(int code, String message) {
        return new Response(code, message);
    }

    public static Response error(Error error) {
        return error.entity();
    }

    public static Response error(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        return new ErrorResponse<>(ErrorCode.PARAMS_ERROR, errors);
    }

}
