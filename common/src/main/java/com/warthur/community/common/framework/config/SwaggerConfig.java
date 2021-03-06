
package com.warthur.community.common.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@Order(22)
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createTestApi() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();

		List<Parameter> aParameters = new ArrayList<>();
		aParameters.add(aParameterBuilder.name("Authorization")
				.description("auth header")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build());
		aParameters.add(aParameterBuilder.name("Version")
				.description("version header")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(true)
				.build()
		);

		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(aParameters)
				.groupName("community")
				.genericModelSubstitutes(Optional.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")// api测试请求地址
				.select()
				.paths(PathSelectors.regex("/.*"))// 过滤的接口
				.build();
	}
}
