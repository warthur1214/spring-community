package com.warthur.community.common.framework.cache;

import com.warthur.community.common.framework.config.IMRedisPipeline;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Cache<T> {

    List<Object> executePipeline(IMRedisPipeline pipeline);

    void multi();

    List<Object> execute();

    Set<String> keys(final String pattern);

    boolean setNx(final String key, T value);

    T getSet(final String key, T value);

    long getExpire(final String key);

    void set(final String key, T value);

    void delete(final String... keys);

    void deletePattern(final String pattern);

    boolean exists(final String key);

    T get(final String key);

    void hSet(String key, String field, T value);

    T hGet(String key, String field);

    Map<String, T> hGetAll(String key);

    void hDel(String key, String field);

    Boolean hKey(String key, String field);

    Long hIncr(String key, String field, long needle);

    void lPush(String key, T value);

    List<T> lRange(String key, long start, long end);

    void add(String key, T ...value);

    Set<T> setMembers(String key);

    void zAdd(String key, T value, double index);

    Set<T> rangeByScore(String key, double start, double end);
}
