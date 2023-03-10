package com.leo.common.springdoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.common.base.LeoEnum;
import com.leo.util.StringUtils;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.AnnotationsUtils;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.time.temporal.Temporal;
import java.util.Iterator;

/**
 * 定制swagger接口文档显示参数说明，
 * 1. LeoEnum 返回{"code": 0,"text": "未知"}形式
 * 2. Temporal类显示，重写
 *
 * @author liujie
 */
@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class LeoModelConverter implements ModelConverter {

    private final ObjectMapper objectMapper;

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType javaType = objectMapper.constructType(type.getType());

        StringSchema schema = new StringSchema();
        // 设置LeoEnum枚举类
        if (javaType != null && javaType.isEnumType() && LeoEnum.class.isAssignableFrom(javaType.getRawClass())) {
            Class<LeoEnum> enumClass = (Class<LeoEnum>) javaType.getRawClass();
            LeoEnum[] enumConstants = enumClass.getEnumConstants();
            try {
                for (LeoEnum leoEnum : enumConstants) {
                    String msg = objectMapper.writeValueAsString(leoEnum);
                    schema.addEnumItem(msg);
                }
                setLeoEnumSchemaInfo(type, javaType, schema);
            } catch (JsonProcessingException e) {
                log.error("LeoEnum生成Api文档转换异常", e);
            }
            return schema;
        }

        // 设置Temporal举类
        if (javaType != null && Temporal.class.isAssignableFrom(javaType.getRawClass())) {
            StringSchema stringSchema = new StringSchema();
            try {
                setLeoEnumSchemaInfo(type, javaType, stringSchema);
            } catch (JsonProcessingException e) {
                log.error("LeoEnum生成Api文档转换异常", e);
            }
            return stringSchema;
        }

        return chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
    }


    /**
     * 重写Schema信息
     * 因为针对信息展示进行了重写，故重新填写@Schema内信息,如有其他需要展示，也可继续扩展
     *
     * @param type     AnnotatedType
     * @param javaType JavaType
     * @param schema   Schema
     * @throws JsonProcessingException e
     */
    private void setLeoEnumSchemaInfo(AnnotatedType type, JavaType javaType, Schema schema) throws JsonProcessingException {
        Annotation resolvedSchemaOrArrayAnnotation = AnnotationsUtils.mergeSchemaAnnotations(type.getCtxAnnotations(), javaType);
        io.swagger.v3.oas.annotations.media.Schema schemaAnnotation = resolvedSchemaOrArrayAnnotation == null ? null : (resolvedSchemaOrArrayAnnotation instanceof ArraySchema ? ((ArraySchema) resolvedSchemaOrArrayAnnotation).schema() : (io.swagger.v3.oas.annotations.media.Schema) resolvedSchemaOrArrayAnnotation);

        if (schemaAnnotation == null) {
            return;
        }

        // 设置schema description 值
        if (StringUtils.isNotBlank(schemaAnnotation.description())) {
            schema.description(schemaAnnotation.description());
        }

        // 设置schema example 值
        if (StringUtils.isNotBlank(schemaAnnotation.example())) {
            schema.example(schemaAnnotation.example());
        }

        // 设置schema defaultValue 值
        if (StringUtils.isNotBlank(schemaAnnotation.defaultValue())) {
            schema.setDefault(schemaAnnotation.defaultValue());
        }
    }
}
