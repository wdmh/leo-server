package com.leo.common.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.common.result.LeoResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Leo System 统一返回结果处理
 * 对返回结果进行统一封装为统一格式
 * 如果不需拦截，可以在这里进行处理
 *
 * @author liujie
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class LeoResponseBodyAdvice implements ResponseBodyAdvice {

    private final static String SPRINGDOC_CLASS = "org.springdoc";
    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return checkNotSupports(returnType);
    }

    /**
     * 判断是否对返回结果进行拦截处理,之后有无需处理请求，可定制修改此方法
     * 1. swagger请求，不进行返回处理
     *
     * @param returnType MethodParameter
     * @return boolean
     */
    private boolean checkNotSupports(MethodParameter returnType) {
        return !returnType.getDeclaringClass().getName().contains(SPRINGDOC_CLASS);
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 对String类型进行处理
        if (body instanceof String) {
            // 需要将返回值类型设置为json,否则会是text/plain
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return objectMapper.writeValueAsString(LeoResult.ok(body));
        }
        // 对LeoResult进行处理
        if (body instanceof LeoResult) {
            return body;
        }
        return LeoResult.ok(body);
    }
}
