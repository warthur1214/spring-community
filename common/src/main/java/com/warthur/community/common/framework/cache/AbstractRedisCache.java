package com.warthur.community.common.framework.cache;

import com.warthur.community.common.framework.config.IMRedisPipeline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public abstract class AbstractRedisCache<T> implements Cache<T> {

    @Autowired
    protected RedisTemplate<String, T> redisTemplate;

    @Override
    public List<Object> executePipeline(IMRedisPipeline pipeline) {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection connection = RedisConnectionUtils.getConnection(factory);
        List<Object> result = null;
        try {
            connection.openPipeline();
            pipeline.executeCommand(connection);
            result = connection.closePipeline();
        } catch (Exception e) {
            log.error("批量执行redis命令失败！", e);
        } finally {
            RedisConnectionUtils.releaseConnection(connection, factory);
        }

        return result;
    }

    @Override
    public void multi() {
        redisTemplate.multi();
    }

    @Override
    public List<Object> execute() {
        return redisTemplate.exec();
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public void delete(String... keys) {
        for (String key : keys) {
            if (key != null && exists(key)) {
                redisTemplate.delete(key);
            }
        }
    }

    @Override
    public void deletePattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public boolean exists(String key) {
        if (key == null || key.equals("")) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    public Long hIncr(String key, String field) {
        return hIncr(key, field, 1);
    }

    @Override
    public Long hIncr(String key, String field, long needle) {
        HashOperations<String, String, Long> hash = redisTemplate.opsForHash();
        return hash.increment(key, field, needle);
    }

    @Override
    public abstract boolean setNx(String key, T value);

    @Override
    public abstract T getSet(String key, T value);


    @Override
    public abstract void set(String key, T value);

    @Override
    public abstract T get(String key);

    @Override
    public abstract void hSet(String key, String field, T value);

    @Override
    public abstract T hGet(String key, String field);

    @Override
    public abstract Map<String, T> hGetAll(String key);

    @Override
    public abstract void hDel(String key, String field);

    @Override
    public abstract Boolean hKey(String key, String field);

    @Override
    public abstract void lPush(String key, T value);

    @Override
    public abstract List<T> lRange(String key, long start, long end);

    @Override
    public abstract void add(String key, T ...value);

    @Override
    public abstract Set<T> setMembers(String key);

    @Override
    public abstract void zAdd(String key, T value, double index);

    @Override
    public abstract Set<T> rangeByScore(String key, double start, double end);
}
