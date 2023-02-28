package com.leo.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.leo.util.constant.LocalDatePattern;
import com.leo.util.constant.StringConstant;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Leo System json定制序列化
 *
 * @author liujie
 */
@SuppressWarnings("unused")
public class LeoCustomizeJsonSerializer {

    private final static String NULL_DATE = new SimpleDateFormat(LocalDatePattern.DATE_TIME_PATTERN).format(new Date(0));

    /**
     * Json序列化 Null Array 处理器 null -> []
     */
    public static class NullArrayJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartArray();
            jsonGenerator.writeEndArray();
        }
    }

    /**
     * Json序列化 Null Boolean 处理器 null -> false
     */
    public static class NullBooleanJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeBoolean(false);
        }
    }

    /**
     * Json序列化空日期处理器 null -> "1970-01-01 08:00:00"
     */
    public static class NullDateJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(NULL_DATE);
        }
    }

    /**
     * Json序列化 Mull Number 处理器 null -> 0
     */
    public static class NullNumberJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(0);
        }
    }

    /**
     * Json序列化 Null Object 处理器 null -> {}
     */
    public static class NullObjectJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeEndObject();
        }
    }

    /**
     * Json序列化 Null String 处理器 null -> ""
     *
     * @author liujie
     */
    public static class NullStringJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(StringConstant.EMPTY_STR);
        }
    }
}
