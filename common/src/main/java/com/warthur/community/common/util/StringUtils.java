package com.warthur.community.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * 字符串处理类
 * @author warthur
 * @date 2018/5/21
 */
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public synchronized static String toId() {

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			log.error("异常！");
		}

		String uuid = UUID.randomUUID().toString();
		return DigestUtils.md5Hex(uuid);
	}
}
