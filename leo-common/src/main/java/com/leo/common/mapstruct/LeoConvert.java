package com.leo.common.mapstruct;

import com.leo.common.base.LeoEnum;
import org.mapstruct.TargetType;

/**
 * Leo System mapstruct基础转换类
 * 1. 实现LeoEnum与Integer之间的互相转换
 *
 * @author liujie
 */
public interface LeoConvert {

    /**
     * LeoEnum子类转换为Integer
     *
     * @param leoEnum LeoEnum
     * @return Integer LeoEnum.code
     */
    default Integer leoEnumToInteger(LeoEnum leoEnum) {
        return leoEnum == null ? null : leoEnum.code();
    }

    /**
     * Integer转换为LeoEnum子类
     *
     * @param code  Integer
     * @param clazz clazz extends LeoEnum
     * @return T
     */
    default <T extends LeoEnum> T IntegerToLeoEnum(Integer code, @TargetType Class<T> clazz) {
        if (code == null) {
            return null;
        }
        for (T enumConstant : clazz.getEnumConstants()) {
            if (code.equals(enumConstant.code())) {
                return enumConstant;
            }
        }
        return null;
    }
}
