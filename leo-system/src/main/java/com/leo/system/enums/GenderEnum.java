package com.leo.system.enums;

import com.leo.common.base.LeoEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenderEnum implements LeoEnum {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

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

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
