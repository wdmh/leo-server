package com.leo.system.convert;

import com.leo.common.mapstruct.LeoConvert;
import com.leo.system.dto.sysuser.SaveSysUserDTO;
import com.leo.system.entity.SysUser;
import com.leo.system.vo.sysuser.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert extends LeoConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUser convert(SaveSysUserDTO saveSysUserDTO);

    SysUserVO convert(SysUser sysUser);
}
