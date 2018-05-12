package com.warthur.community.common.framework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class StringRedisCache extends AbstractRedisCache<String> {

    @Override
    public boolean setNx(String key, String value) {
        boolean result = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            result = operations.setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getSet(String key, String value) {
        String result = null;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            result = operations.getAndSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void set(String key, String value) {
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception e) {

        }
    }

    @Override
    public String get(String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public void hSet(String key, String field, String value) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.put(key, field, value);
    }

    @Override
    public String hGet(String key, String field) {
        if (!exists(key)) {
            return null;
        }
        if (!hKey(key, field)) {
            return null;
        }
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.get(key, field);
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        if (!exists(key)) {
            return null;
        }

        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.entries(key);
    }

    @Override
    public void hDel(String key, String field) {
        if (!exists(key)) {
            return;
        }
        if (!hKey(key, field)) {
            return;
        }
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.delete(key, field);
    }

    @Override
    public Boolean hKey(String key, String field) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.hasKey(key, field);
    }

    @Override
    public void lPush(String key, String value) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }

    @Override
    public List<String> lRange(String key, long start, long end) {
        ListOperations<String, String> list = redisTemplate.opsForList();
        return list.range(key, start, end);
    }

    @Override
    public final void add(String key, String ...value) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    @Override
    public Set<String> setMembers(String key) {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    @Override
    public void zAdd(String key, String value, double index) {
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        zset.add(key, value, index);
    }

    @Override
    public Set<String> rangeByScore(String key, double start, double end) {
        ZSetOperations<String, String> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, start, end);
    }
}
