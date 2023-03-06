package com.leo.system.enums;

import com.leo.common.result.LeoResultInfo;
import lombok.AllArgsConstructor;

/**
 * System模块服务错误信息枚举类（前缀200）
 */
@AllArgsConstructor
public enum SystemErrorResult implements LeoResultInfo {

    USER_NOT_EXISTS(2000001, "系统用户不存在！");

    /**
     * 响应码
     */
    private final int code;
    /**
     * 响应信息
     */
    private final String message;

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
