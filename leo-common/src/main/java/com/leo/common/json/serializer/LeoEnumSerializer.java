package com.leo.common.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.leo.common.base.LeoEnum;

import java.io.IOException;

/**
 * LeoEnumSerializer LeoEnum Json序列化定义
 *
 * @author liujie
 */
public class LeoEnumSerializer extends JsonSerializer<LeoEnum> {

    private static final String CODE = "code";
    private static final String TEXT = "text";

    @Override
    public void serialize(LeoEnum leoEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(CODE, leoEnum.code());
        jsonGenerator.writeStringField(TEXT, leoEnum.text());
        jsonGenerator.writeEndObject();
    }
}
