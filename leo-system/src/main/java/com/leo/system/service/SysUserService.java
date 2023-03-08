package com.leo.system.service;

import com.leo.common.exception.LeoException;
import com.leo.common.result.LeoResultCode;
import com.leo.system.convert.SysUserConvert;
import com.leo.system.dto.sysuser.SaveSysUserDTO;
import com.leo.system.entity.SysUser;
import com.leo.system.mapper.SysUserMapper;
import com.leo.system.vo.sysuser.SysUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserMapper userMapper;

    private final SysUserConvert userConvert = SysUserConvert.INSTANCE;

    public void save(SaveSysUserDTO saveSysUserDTO) {
        SysUser sysUser = SysUserConvert.INSTANCE.convert(saveSysUserDTO);

        int saveCount = userMapper.insert(sysUser);
        if (saveCount != 1) {
            throw new LeoException(LeoResultCode.INSERT_DATA_ERROR);
        }
    }

    public SysUserVO getById(Long id) {
        SysUser sysUser = userMapper.selectById(id);

        return userConvert.convert(sysUser);
    }
}
