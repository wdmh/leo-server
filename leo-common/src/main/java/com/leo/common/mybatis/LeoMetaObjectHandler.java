package com.leo.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis-Plus 自动填充字段功能
 *
 * @author liujie
 */
@Component
public class LeoMetaObjectHandler implements MetaObjectHandler {

    private final static String INSERT_FILL_CREATE_TIME = "createTime";
    private final static String INSERT_FILL_CREATE_USER = "createUser";
    private final static String INSERT_FILL_DELETE = "deleted";

    private final static String UPDATE_FILL_UPDATE_TIME = "updateTime";
    private final static String UPDATE_FILL_UPDATE_USER = "updateUser";

    /**
     * 插入时自动填充字段
     *
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, INSERT_FILL_CREATE_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, INSERT_FILL_CREATE_USER, () -> 1L, Long.class);
        this.strictInsertFill(metaObject, INSERT_FILL_DELETE, () -> Boolean.FALSE, Boolean.class);
    }

    /**
     * 更新时自动填充字段
     *
     * @param metaObject 元数据
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_FILL_UPDATE_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, UPDATE_FILL_UPDATE_USER, () -> 1L, Long.class);
    }
}
