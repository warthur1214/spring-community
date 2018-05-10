package com.warthur.community.common.util;


import com.warthur.community.common.Error;
import com.warthur.community.common.Response;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arthur wu
 * on 2017/3/22.
 */
public class ResponseUtil {

    public static Response success(Object object) {
        return new Response<>(Error.REQUEST_SUCCESS, object);
    }

    public static Response success() {
        return success(new HashMap<>());
    }

    public static Response error(Integer code, String msg) {
        return error(code, msg, null);
    }

    public static Response error(Integer code, String msg, Object data) {
        return new Response<>(code, msg, data);
    }

    public static Response error(Error exceptionEnum) {
        return new Response<>(exceptionEnum, null);
    }

    public static Response error(String message) {
        return error(Error.REQUEST_ERROR.getCode(), message);
    }

    public static Response error(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        return new Response<>(Error.PARAMS_ERROR.getCode(), Error.PARAMS_ERROR.getMsg(), null, errors);
    }

}
