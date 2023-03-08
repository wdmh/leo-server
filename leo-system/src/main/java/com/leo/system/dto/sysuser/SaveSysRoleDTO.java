package com.leo.system.dto.sysuser;

import com.leo.system.enums.UseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(name = "保存角色 入参")
@Data
public class SaveSysRoleDTO {

    @Schema(description = "角色名称", example = "管理员")
    @NotBlank
    private String roleName;

    @Schema(description = "使用状态", example = "0")
    private UseStatus useStatus;
}
