package com.leo.system.entity;

import com.leo.common.base.LeoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统角色类
 *
 * @author liujie
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRole extends LeoEntity {

    // 角色名称
    private String roleName;
    // 使用状态(0:未启用,1:已启用,2:已停用)
    private Integer useStatus;
}
