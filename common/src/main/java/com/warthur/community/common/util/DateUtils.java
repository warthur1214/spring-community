package com.warthur.community.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;


public class DateUtils extends DateFormatUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";

    public static String format() {
        return format(DEFAULT_PATTERN);
    }

    public static String format(String pattern) {
        return format(new Date(), DEFAULT_PATTERN);
    }

}
