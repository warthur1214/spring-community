package com.warthur.community.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by warthur on 2018/5/8.
 */
@Slf4j
public class RequestValidate {

	public static boolean validate(Object param, Object data, String secret, String signature) {

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> paramMap = new TreeMap<>();
		try {
			Map<String, Object> map1 = mapper.convertValue(data, TreeMap.class);
			Map<String, Object> map2 = mapper.convertValue(param, TreeMap.class);
			paramMap.putAll(map1);
			paramMap.putAll(map2);

			handleMap(paramMap);

			String beforeValid = secret + Base64.getEncoder()
					.encodeToString(Joiner.on("&").withKeyValueSeparator("=").join(paramMap).getBytes());
			String encry = DigestUtils.sha1Hex(beforeValid.getBytes());
			if (!encry.equals(signature)) {
				return false;
			}
		} catch (Exception e) {
			log.error("参数转换失败", e);
			return false;
		}
		return true;
	}

	public static boolean validate(Object param, String secret, String signature) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> paramMap;
		try {
			paramMap = mapper.convertValue(param, TreeMap.class);

			handleMap(paramMap);

			String beforeValid = secret + Base64.getEncoder()
					.encodeToString(Joiner.on("&").withKeyValueSeparator("=").join(paramMap).getBytes());
			String encry = DigestUtils.sha1Hex(beforeValid.getBytes());
			if (!encry.equals(signature)) {
				return false;
			}
		} catch (Exception e) {
			log.error("参数转换失败", e);
			return false;
		}

		return true;
	}

	private static void handleMap(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				entry.setValue(((Map) entry.getValue()).size());
			}
			if (entry.getValue() instanceof List) {
				entry.setValue(((List) entry.getValue()).size());
			}
		}
	}
}
