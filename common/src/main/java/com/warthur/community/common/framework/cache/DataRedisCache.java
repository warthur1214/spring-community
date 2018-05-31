package com.warthur.community.common.framework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class DataRedisCache extends AbstractRedisCache<Object> {

    @Override
    public boolean setNx(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            result = operations.setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object getSet(String key, Object value) {
        Object result = null;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            result = operations.getAndSet(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void set(String key, Object value) {
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
        } catch (Exception e) {

        }
    }

    @Override
    public Object get(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public void hSet(String key, String field, Object value) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        hash.put(key, field, value);
    }

    @Override
    public Object hGet(String key, String field) {
        if (!exists(key)) {
            return null;
        }
        if (!hKey(key, field)) {
            return null;
        }
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, field);
    }

    @Override
    public Map<String, Object> hGetAll(String key) {
        if (!exists(key)) {
            return null;
        }

        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
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
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        hash.delete(key, field);
    }

    @Override
    public Boolean hKey(String key, String field) {
        HashOperations<String, String, Object> hash = redisTemplate.opsForHash();
        return hash.hasKey(key, field);
    }

    @Override
    public void lPush(String key, Object value) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(key, value);
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key, start, end);
    }

    @Override
    public final void add(String key, Object ...value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    @Override
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    @Override
    public void zAdd(String key, Object value, double index) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, index);
    }

    @Override
    public Set<Object> rangeByScore(String key, double start, double end) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, start, end);
    }
}
