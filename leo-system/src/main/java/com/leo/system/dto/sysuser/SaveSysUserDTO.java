package com.leo.system.dto.sysuser;

import com.leo.system.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "保存用户 入参")
public class SaveSysUserDTO {

    @NotBlank
    @Schema(description = "姓名", example = "张三")
    private String name;

    @NotNull
    @Schema(description = "性别", example = "0")
    private GenderEnum gender;

    @NotBlank
    @Schema(description = "手机号", example = "18212341234")
    private String phone;
}
