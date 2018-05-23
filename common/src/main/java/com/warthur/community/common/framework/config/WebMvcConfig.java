package com.warthur.community.common.framework.config;

import com.warthur.community.common.framework.cache.StringRedisCache;
import com.warthur.community.common.framework.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

    @Autowired
    private StringRedisCache stringRedisCache;

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        log.info("==添加错误状态处理页面==");
        // container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        // container.addErrorPages(new ErrorPage(HttpStatus.EXPECTATION_FAILED, "/error/500"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("==拦截器注册==");
        registry.addInterceptor(new JwtInterceptor(stringRedisCache)).addPathPatterns("/**");//权限验证拦截器
        super.addInterceptors(registry);
    }

}
