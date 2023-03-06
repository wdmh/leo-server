package com.leo.common.result;

import lombok.AllArgsConstructor;

/**
 * Leo System 系统响应码 枚举类
 * 常用系统错误信息 前缀100
 */
@AllArgsConstructor
public enum LeoResultCode implements LeoResultInfo {

    SUCCESS(200, "请求成功"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "未经授权，无法访问"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "未找到请求资源"),
    SERVER_ERROR(500, "内部服务器异常"),

    INSERT_DATA_ERROR(1000001, "新增数据失败!"),
    UPDATE_DATA_ERROR(1000002, "更新数据失败!"),
    DELETE_DATA_ERROR(1000003, "删除数据失败!"),

    ;
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
