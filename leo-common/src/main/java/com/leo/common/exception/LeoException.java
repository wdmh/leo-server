package com.leo.common.exception;

import com.leo.common.result.LeoResultInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Leo System 统一异常类
 *
 * @author liujie
 */
@Getter
@Setter
public class LeoException extends RuntimeException implements LeoResultInfo {

    /**
     * 异常代码
     */
    private Integer errorCode;
    /**
     * 异常信息
     */
    private String errorMessage;

    public LeoException(LeoResultInfo leoResultInfo) {
        super(leoResultInfo.message());
        this.errorCode = leoResultInfo.code();
        this.errorMessage = leoResultInfo.message();
    }

    @Override
    public int code() {
        return this.errorCode;
    }

    @Override
    public String message() {
        return this.errorMessage;
    }
}
