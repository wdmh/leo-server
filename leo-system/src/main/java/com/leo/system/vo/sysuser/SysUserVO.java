package com.leo.system.vo.sysuser;

import com.leo.system.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "获取用户 出参")
public class SysUserVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "姓名")
    private String userName;

    @Schema(description = "性别")
    private Gender gender;

    @Schema(description = "手机号")
    private String phone;
}
