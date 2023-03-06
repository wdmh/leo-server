package com.leo.common.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 数据库表基本字段，非多对多关联表，可根据需求继承此实体
 *
 * @author liujie
 */
@Data
public class LeoEntity {

    /**
     * 唯一id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 添加用户
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 添加时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改用户
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateUser;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 逻辑删除(0:未删除,1:已删除)
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Boolean deleted;
}
