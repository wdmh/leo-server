package com.leo.system.entity;

import com.leo.common.base.LeoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统用户类
 *
 * @author liujie
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends LeoEntity {

    // 姓名
    private String name;
    // 性别
    private Integer gender;
    // 手机号
    private String phone;
}
