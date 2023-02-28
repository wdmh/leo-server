package com.leo.common.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.leo.util.ClassUtils;

import java.util.List;

/**
 * Leo System 自定义json返回格式
 * 可针对不同类型null数据返回不同定制类结果
 *
 * @author liujie
 */
public class LeoBeanSerializerModifier extends BeanSerializerModifier {

    private static final LeoCustomizeJsonSerializer.NullArrayJsonSerializer nullArrayJsonSerializer = new LeoCustomizeJsonSerializer.NullArrayJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter beanPropertyWriter : beanProperties) {
            Class<?> clazz = beanPropertyWriter.getType().getRawClass();
            // 处理 Null 返回自定义
            if (ClassUtils.isArray(clazz)) {
                beanPropertyWriter.assignNullSerializer(nullArrayJsonSerializer);
            }
            if (ClassUtils.isMap(clazz)) {
                beanPropertyWriter.assignNullSerializer(new LeoCustomizeJsonSerializer.NullObjectJsonSerializer());
            }
            if (ClassUtils.isBoolean(clazz)) {
                beanPropertyWriter.assignNullSerializer(new LeoCustomizeJsonSerializer.NullBooleanJsonSerializer());
            }
            if (ClassUtils.isString(clazz)) {
                beanPropertyWriter.assignNullSerializer(new LeoCustomizeJsonSerializer.NullStringJsonSerializer());
            }
            if (ClassUtils.isNumber(clazz)) {
                beanPropertyWriter.assignNullSerializer(new LeoCustomizeJsonSerializer.NullNumberJsonSerializer());
            }
            if (ClassUtils.isDate(clazz)) {
                beanPropertyWriter.assignNullSerializer(new LeoCustomizeJsonSerializer.NullDateJsonSerializer());
            }
        }
        return super.changeProperties(config, beanDesc, beanProperties);
    }
}
