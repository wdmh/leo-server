package com.leo.common.aop;

import com.leo.common.result.LeoResult;
import lombok.SneakyThrows;
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
@RestControllerAdvice
public class LeoResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 对返回true的请求进行拦截处理
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 对LeoResult进行处理
        if (body instanceof LeoResult) {
            return body;
        }
        return LeoResult.ok(body);
    }
}
