package com.leo.system.convert;

import com.leo.common.mapstruct.LeoConvert;
import com.leo.system.dto.sysuser.SaveSysRoleDTO;
import com.leo.system.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysRoleConvert extends LeoConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRole convert(SaveSysRoleDTO saveSysRoleDTO);
}
