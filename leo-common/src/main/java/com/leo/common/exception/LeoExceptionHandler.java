package com.leo.common.exception;

import com.leo.common.result.LeoResult;
import com.leo.common.result.LeoResultCode;
import com.leo.util.constant.StringConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author liujie
 */
@Slf4j
@RestControllerAdvice
public class LeoExceptionHandler {

    // 处理全局异常
    @ExceptionHandler(value = Exception.class)
    public LeoResult exceptionHandler(Exception e) {
        log.error(StringConstant.EMPTY_STR, e);
        return LeoResult.ok(LeoResultCode.SERVER_ERROR, e.getMessage());
    }

    // 处理自定义异常
    @ExceptionHandler(value = LeoException.class)
    public LeoResult leoExceptionHandler(LeoException le) {
        log.error(StringConstant.EMPTY_STR, le);
        return LeoResult.ok(le);
    }
}
