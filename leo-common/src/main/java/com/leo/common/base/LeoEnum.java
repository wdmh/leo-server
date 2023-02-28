package com.leo.common.base;

/**
 * LeoEnum
 * 继承此类的枚举类，在进行Json序列化时，会转换为 {code": 0,"text": "未知"} 对象形式
 *
 * @author liujie
 */
public interface LeoEnum {

    /**
     * 获取code
     *
     * @return int code
     */
    int getCode();

    /**
     * 获取text
     *
     * @return String text
     */
    String getText();
}
