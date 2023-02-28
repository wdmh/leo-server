package com.leo.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Leo System 请求返回结果
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
public class LeoResult<T> {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public static <T> LeoResult<T> ok() {
        return new LeoResult<>(LeoResultCode.SUCCESS.getCode(), LeoResultCode.SUCCESS.getMessage(), null);
    }

    public static <T> LeoResult<T> ok(T t) {
        return new LeoResult<>(LeoResultCode.SUCCESS.getCode(), LeoResultCode.SUCCESS.getMessage(), t);
    }

    public static <T> LeoResult<T> ok(LeoResultInfo lri) {
        return new LeoResult<>(lri.getCode(), lri.getMessage(), null);
    }

    public static <T> LeoResult<T> ok(LeoResultInfo lri, T t) {
        return new LeoResult<>(lri.getCode(), lri.getMessage(), t);
    }
}
