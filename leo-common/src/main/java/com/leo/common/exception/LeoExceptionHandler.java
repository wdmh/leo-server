package com.leo.common.exception;

import com.leo.common.result.LeoResult;
import com.leo.common.result.LeoResultCode;
import com.leo.util.constant.StringConstant;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author liujie
 */
@Slf4j
@RestControllerAdvice
public class LeoExceptionHandler {

    private final static String COLON_CURLY_BRACKETS = ":{}";

    /**
     * 处理Param参数类型异常，返回格式化后的错误信息
     *
     * @param e MethodArgumentTypeMismatchException
     * @return LeoResult
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public LeoResult MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        String errMsg = e.getName() + StringConstant.COLON + "参数类型错误";
        log.error(LeoResultCode.BAD_REQUEST.message() + COLON_CURLY_BRACKETS, errMsg);
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
        String errMsg = e.getParameterName() + StringConstant.COLON + "参数缺失";
        log.error(LeoResultCode.BAD_REQUEST.message() + COLON_CURLY_BRACKETS, errMsg);
        return LeoResult.ok(LeoResultCode.BAD_REQUEST, errMsg);
    }

    /**
     * 处理Param参数校验异常，返回格式化后的错误信息
     *
     * @param e MethodArgumentNotValidException
     * @return LeoResult
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public LeoResult ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String errMsg = e.getConstraintViolations().stream()
                .map((cv) -> cv.getPropertyPath().toString().split(StringConstant.BACK_SLASH + StringConstant.DOT)[1] + StringConstant.COLON + cv.getMessage())
                .collect(Collectors.joining(StringConstant.COMMA));

        log.error(LeoResultCode.BAD_REQUEST.message() + COLON_CURLY_BRACKETS, errMsg);
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
                .map((fe) -> fe.getField() + StringConstant.COLON + fe.getDefaultMessage())
                .collect(Collectors.joining(StringConstant.COMMA));

        log.error(LeoResultCode.BAD_REQUEST.message() + COLON_CURLY_BRACKETS, errMsg);
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
        log.error(StringConstant.EMPTY_STR, e);
        return LeoResult.ok(e);
    }

    /**
     * 处理全局异常
     *
     * @param e LeoException
     * @return LeoResult
     */
    @ExceptionHandler(value = Exception.class)
    public LeoResult exceptionHandler(Exception e) {
        log.error(StringConstant.EMPTY_STR, e);
        return LeoResult.ok(LeoResultCode.SERVER_ERROR, e.getMessage());
    }
}
