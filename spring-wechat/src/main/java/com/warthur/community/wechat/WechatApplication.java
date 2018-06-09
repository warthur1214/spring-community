package com.warthur.community.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * 应用启动类
 * @author warthur
 * @date 2018/5/8
 */
@SpringCloudApplication
@EnableAsync
@EnableScheduling
@ComponentScan({"com.warthur.community.common.framework", "com.warthur.community.wechat"})
public class WechatApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
}
