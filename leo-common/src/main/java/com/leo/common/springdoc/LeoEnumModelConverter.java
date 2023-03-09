package com.leo.common.springdoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.common.base.LeoEnum;
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
import java.util.Iterator;

/**
 * 定制LeoEnum swagger接口文档显示参数说明，返回{"code": 0,"text": "未知"}形式
 * 通过重写Schema实现类应该也可以达到同样的效果，但是并未测试
 *
 * @author liujie
 */
@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class LeoEnumModelConverter implements ModelConverter {

    private final ObjectMapper objectMapper;

    @Override
    public Schema resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        JavaType javaType = objectMapper.constructType(type.getType());
        if (javaType != null && javaType.isEnumType() && LeoEnum.class.isAssignableFrom(javaType.getRawClass())) {
            Class<LeoEnum> enumClass = (Class<LeoEnum>) javaType.getRawClass();
            if (LeoEnum.class.isAssignableFrom(enumClass)) {
                StringSchema schema = new StringSchema();
                try {
                    LeoEnum[] enumConstants = enumClass.getEnumConstants();
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
        }
        return chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
    }


    /**
     * 重写Schema信息
     * 因为针对LeoEnum信息展示进行了重写，故重新填写@Schema内example信息,如有其他需要展示，也可继续扩展
     *
     * @param type     AnnotatedType
     * @param javaType JavaType
     * @param schema   Schema
     * @throws JsonProcessingException e
     */
    private void setLeoEnumSchemaInfo(AnnotatedType type, JavaType javaType, Schema schema) throws JsonProcessingException {
        Annotation resolvedSchemaOrArrayAnnotation = AnnotationsUtils.mergeSchemaAnnotations(type.getCtxAnnotations(), javaType);
        io.swagger.v3.oas.annotations.media.Schema schemaAnnotation = resolvedSchemaOrArrayAnnotation == null ? null : (resolvedSchemaOrArrayAnnotation instanceof ArraySchema ? ((ArraySchema) resolvedSchemaOrArrayAnnotation).schema() : (io.swagger.v3.oas.annotations.media.Schema) resolvedSchemaOrArrayAnnotation);

        // 设置schema example示例值
        if (schemaAnnotation != null && !schemaAnnotation.example().isEmpty()) {
            schema.example(objectMapper.readTree(schemaAnnotation.example()));
        }
    }
}
