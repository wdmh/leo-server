package com.leo.system.service;

import com.leo.common.exception.LeoException;
import com.leo.common.result.LeoResultCode;
import com.leo.system.convert.SysRoleConvert;
import com.leo.system.dto.sysuser.SaveSysRoleDTO;
import com.leo.system.entity.SysRole;
import com.leo.system.mapper.SysRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleService {

    private final SysRoleMapper roleMapper;

    private final SysRoleConvert roleConvert = SysRoleConvert.INSTANCE;

    public void saveSysRole(SaveSysRoleDTO saveSysRoleDTO) {
        SysRole sysRole = roleConvert.convert(saveSysRoleDTO);

        int saveCount = roleMapper.insert(sysRole);
        if (saveCount != 1) {
            throw new LeoException(LeoResultCode.INSERT_DATA_ERROR);
        }
    }
}
