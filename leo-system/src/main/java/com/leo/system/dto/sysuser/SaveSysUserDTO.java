package com.leo.system.dto.sysuser;

import com.leo.system.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(name = "保存用户 入参")
@Data
public class SaveSysUserDTO {

    @Schema(description = "姓名", example = "张三")
    @NotBlank
    private String userName;

    @Schema(description = "性别", example = "0")
    @NotNull
    private Gender gender;

    @Schema(description = "手机号", example = "18212341234")
    @NotBlank
    private String phone;
}
