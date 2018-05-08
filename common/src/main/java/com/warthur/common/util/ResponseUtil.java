package com.warthur.common.util;


import com.warthur.common.Error;
import com.warthur.common.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by arthur wu
 * on 2017/3/22.
 */
public class ResponseUtil {

    @AllArgsConstructor
    @Data
    private static class ResponseData {
        private int pageNum;
        private int totalPageSize;
        private Object data;
    }

    public static Response success(Object object) {
        return new Response<>(Error.REQUEST_SUCCESS, object);
    }

    public static Response success() {
        return success(null);
    }

    public static Response successList() {
        return success(new ArrayList<>());
    }

    public static Response sucessPageList(final Integer pageNum, final Integer totalSize) {
        return sucessPageList(pageNum, totalSize, new ArrayList<>());
    }

    public static Response sucessPageList(final Integer pageNum, final Integer totalSize, List<Object> list) {
        return success(new ResponseData(pageNum, totalSize, list));
    }

    public static Response successMap() {
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
        return error(1, message);
    }

    public static Response error(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + fieldError.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());
        return error(Error.REQUEST_ERROR.getCode(), Error.REQUEST_ERROR.getMsg(), errors);
    }

}
