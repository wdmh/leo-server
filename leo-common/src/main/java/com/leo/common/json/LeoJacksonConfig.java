package com.leo.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.leo.common.base.LeoEnum;
import com.leo.common.json.serializer.LeoEnumSerializer;
import com.leo.util.constant.LocalDatePattern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Leo System Json配置类
 * 重写ObjectMapper,对部分类型数据进行处理
 *
 * @author liujie
 */
@Configuration
public class LeoJacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // 所有字段都显示
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 反序列化时，遇到不存在的属性不报错，即json中存在但是实体类中不存在的字段
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 如果对象为空时，不抛异常，即Bean中没有任何属性
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 枚举类
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        // 定制返回结果
        // objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new LeoBeanSerializerModifier()));

        // LocalDateTime 类型默认格式化
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(LocalDatePattern.DATE_TIME_FORMAT));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(LocalDatePattern.DATE_TIME_FORMAT));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(LocalDatePattern.DATE_FORMAT));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(LocalDatePattern.DATE_FORMAT));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(LocalDatePattern.TIME_FORMAT));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(LocalDatePattern.TIME_FORMAT));
        objectMapper.registerModule(javaTimeModule);

        // Date 类型默认日期格式化
        objectMapper.setDateFormat(new SimpleDateFormat(LocalDatePattern.DATE_TIME_PATTERN));

        // 序列化处理
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(LeoEnum.class, new LeoEnumSerializer());

        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }
}
