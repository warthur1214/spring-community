package com.warthur.common.framework.config;

import org.springframework.data.redis.connection.RedisConnection;

/**
 * 批量执行redis命令接口
 */
public interface IMRedisPipeline {

	void executeCommand(RedisConnection connection);
}
