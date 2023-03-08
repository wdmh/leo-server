package com.leo.system.enums;

import com.leo.common.base.LeoEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UseStatus implements LeoEnum {

    UN_ENABLE(0, "未启用"),
    ENABLE(1, "已启用"),
    STOP(2, "已停用");

    private final int code;

    private final String text;

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String text() {
        return this.text;
    }
}
