package com.warthur.common.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 批量执行redis命令，提高执行效率
	 * @param pipeline
	 * @return
	 */
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

	/**
	 * 开启事务
	 */

	public void multi() {
		redisTemplate.multi();
	}

	public List<Object> execut() {
		return redisTemplate.exec();
	}

	/**
	 * 模糊匹配key
	 */
	public Set<String> keys(final String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 没有key设置，有key不设置
	 */
	public boolean setNx(final String key, Object value) {
	    boolean result = false;
	    try {
		    ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		    result = operations.setIfAbsent(key, value);
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
	    return result;
    }

	/**
	 * 返回旧值，设置新值
	 */
	public Object getSet(final String key, Object value) {
	    Object result = null;
	    try {
		    ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		    result = operations.getAndSet(key, value);
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
	    return result;
    }

    public long getExpire(final String key) {
		return getExpire(key, TimeUnit.MILLISECONDS);
    }

    public long getExpire(final String key, TimeUnit unit) {
	    return redisTemplate.getExpire(key, unit);
    }

    public boolean set(final String key, Object value) {
		return set(key, value, null);
    }
    /**
     * 写入缓存设置时效时间
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String,Object> operations = redisTemplate.opsForValue();
            if (expireTime == null) {
	            operations.set(key, value);
            } else {
	            set(key, value, expireTime, TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	/**
	 * 写入缓存设置时效时间
	 */
	public boolean set(final String key, Object value, Long expireTime, TimeUnit unit) {
		boolean result = false;
		try {
			ValueOperations<String,Object> operations = redisTemplate.opsForValue();
			operations.set(key, value, expireTime, unit);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    /**
     * 批量删除对应的value
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 哈希 添加
     */
    public void hSet(String key, Object hashKey, String value) {
        HashOperations<String, Object, String> hash = stringRedisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

	/**
	 * 哈希获取数据
	 */
	public Object hGet(String key, Object hashKey) {
		if (!exists(key)) {
			return null;
		}
		if (!hKey(key, hashKey)) {
			return null;
		}
		HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
		return hash.get(key, hashKey);
	}

	public Map<Object, Object> hGetAll(String key) {
		if (!exists(key)) {
			return null;
		}

		HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
		return hash.entries(key);
	}

	public void hDel(String key, Object hashKey) {
		if (!exists(key)) {
			return;
		}
		if (!hKey(key, hashKey)) {
			return;
		}
		HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
		hash.delete(key, hashKey);
	}

	public Boolean hKey(String key, Object hashKey) {
		HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
		return hash.hasKey(key, hashKey);
	}


	/**
	 * 哈希获取数据
	 */
	public Object hIncr(String key, Object hashKey, long needle) {
		HashOperations<String, Object, Long> hash = stringRedisTemplate.opsForHash();
		return hash.increment(key, hashKey, needle);
	}


	/**
	 * 哈希获取数据
	 */
	public Object hIncr(String key, Object hashKey) {
		return hIncr(key, hashKey, 1);
	}

    /**
     * 列表添加
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表获取
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    /**
     * 集合添加
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 集合获取
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}