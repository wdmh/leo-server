package com.leo.system.controller;

import com.leo.system.dto.sysuser.SaveSysUserDTO;
import com.leo.system.service.SysUserService;
import com.leo.system.vo.sysuser.SysUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "系统用户管理")
@RequestMapping("/sysUser")
public class SysUserController {

    private final SysUserService sysUserService;

    @Operation(summary = "保存用户")
    @PostMapping("/save")
    public void save(@Validated @RequestBody SaveSysUserDTO saveSysUserDTO) {
        sysUserService.save(saveSysUserDTO);
    }

    @Operation(summary = "根据用户id，获取用户")
    @GetMapping(value = "/getById")
    public SysUserVO getById(@RequestParam Long id) {
        return sysUserService.getById(id);
    }
}
