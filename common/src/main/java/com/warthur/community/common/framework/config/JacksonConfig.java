package com.warthur.community.common.framework.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.warthur.community.common.BaseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.List;

/**
 * 自定义的JSON转换MAPPER
 * @author warthur
 */
@Configuration
@Order(-20)
@Slf4j
public class JacksonConfig {

	@Bean(name = "jacksonMapper")
	public CustomObjectMapper initCustomObjectMapper() {
		return new CustomObjectMapper();
	}

	public class CustomObjectMapper extends ObjectMapper {

		private static final long serialVersionUID = -2291770138128297247L;

		public CustomObjectMapper() {
			super();
			// 设置null转换""
			getSerializerProvider().setNullValueSerializer(new NullSerializer());
			// 设置日期转换yyyy-MM-dd HH:mm:ss
			// setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}

	}

	private class NullSerializer extends JsonSerializer<Object> {
		@Override
		public void serialize(Object value, JsonGenerator jgen,
							  SerializerProvider provider) throws IOException {
			if (value instanceof BaseDTO) {
				jgen.writeString("{}");
			} else if (value instanceof List) {
				jgen.writeString("[]");
			} else {
				jgen.writeString("");
			}
		}


	}
}
