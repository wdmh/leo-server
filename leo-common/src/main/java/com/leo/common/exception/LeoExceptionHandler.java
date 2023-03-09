package com.leo.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.leo.common.result.LeoResult;
import com.leo.common.result.LeoResultCode;
import com.leo.util.StringUtils;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author liujie
 */
@Slf4j
@RestControllerAdvice
public class LeoExceptionHandler {

    /**
     * 处理部分请求404问题，返回格式化后的错误信息
     * 1. 需添加@EnableWebMvc注解
     * 2. 需配置spring.mvc.throw-exception-if-no-handler-found: true
     * 后续改造通过网关路由拦截非法请求，故作废
     *
     * @param e NoHandlerFoundException
     * @return LeoResult
     */
    @Deprecated
    // @ExceptionHandler(value = NoHandlerFoundException.class)
    public LeoResult NoHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        String errMsg = e.getHttpMethod() + " " + e.getRequestURL();
        return LeoResult.ok(LeoResultCode.NOT_FOUND, errMsg);
    }

    /**
     * 处理RequestParam、PathVariable参数类型异常，返回格式化后的错误信息
     *
     * @param e MethodArgumentTypeMismatchException
     * @return LeoResult
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public LeoResult MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        String errMsg = e.getName() + ": 参数类型错误";
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);
    }

    /**
     * 处理Param参数缺失异常，返回格式化后的错误信息
     *
     * @param e MissingServletRequestParameterException
     * @return LeoResult
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public LeoResult MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        String errMsg = e.getParameterName() + ": 参数缺失";
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);
    }

    /**
     * 处理Param参数校验异常，返回格式化后的错误信息
     *
     * @param e ConstraintViolationException
     * @return LeoResult
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public LeoResult ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String errMsg = e.getConstraintViolations().stream()
                .map((cv) -> cv == null ? "null" : cv.getPropertyPath().toString().split("\\.")[1] + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);
    }

    /**
     * 处理body参数类型异常，返回格式化后的错误信息
     * 通过转换为JsonMappingException进行处理，可以获取到错误字段信息
     *
     * @param e HttpMessageNotReadableException
     * @return LeoResult
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public LeoResult HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errMsg = "参数类型错误";
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof JsonMappingException) {
            List<JsonMappingException.Reference> references = ((JsonMappingException) rootCause).getPath();
            errMsg = references.stream()
                    .map((re) -> re == null ? "null" : re.getFieldName() + ": 参数类型错误")
                    .collect(Collectors.joining(", "));
        }
        if (rootCause instanceof DateTimeParseException) {
            String time = ((DateTimeParseException) rootCause).getParsedString();
            if (StringUtils.isNotBlank(time)) {
                errMsg = time + ": 时间格式错误";
            }
        }
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);

    }

    /**
     * 处理body参数校验异常，返回格式化后的错误信息
     *
     * @param e MethodArgumentNotValidException
     * @return LeoResult
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public LeoResult MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errMsg = e.getFieldErrors().stream()
                .map((fe) -> fe == null ? "null" : fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);
    }

    /**
     * 处理请求方法类型异常，如GET调用POST请求，返回格式化后的错误信息
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return LeoResult
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public LeoResult HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        String errMsg = e.getMethod() + "请求类型不支持";
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);
    }

    /**
     * 处理自定义异常
     *
     * @param e LeoException
     * @return LeoResult
     */
    @ExceptionHandler(value = LeoException.class)
    public LeoResult leoExceptionHandler(LeoException e) {
        log.error("LeoException: {}({})", e.getErrorMessage(), e.getErrorCode());
        return LeoResult.ok(e);
    }

    /**
     * 处理全局异常
     *
     * @param e Exception
     * @return LeoResult
     */
    @ExceptionHandler(value = Exception.class)
    public LeoResult exceptionHandler(Exception e) {
        log.error("ExceptionStackTrace", e);
        return LeoResult.ok(LeoResultCode.SERVER_ERROR, e.getMessage());
    }
}
