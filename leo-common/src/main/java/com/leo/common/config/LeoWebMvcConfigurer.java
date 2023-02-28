package com.leo.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Leo System LeoWebMvcConfigurer
 * 处理统一返回结果时，json异常问题
 *
 * @author liujie
 */
@Configuration
public class LeoWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
        // 处理统一返回结果封装时，String类型异常
        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }
}
