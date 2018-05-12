package com.warthur.community.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by warthur on 2018/5/8.
 */
@SpringCloudApplication
@EnableAsync
@EnableScheduling
@ComponentScan({"com.warthur.community.common.framework", "com.warthur.community.wechat"})
@MapperScan("com.warthur.community.wechat.dao")
public class WechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
}
