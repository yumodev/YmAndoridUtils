package com.yumo.common.define;

/**
 * Created by yumodev on 4/11/16.
 * 该工具包下所有的常量定义
 */
public class YmDefine {

    /**
     * 文件相关的定义
     */
    public static final int FILE_SUCCESS = 0;
    /**
     * 文件未找到
     */
    public static final int FILE_NO_FOUND = -1;
    /**
     * 发生了IO异常
     */
    public static final int FILE_IO_ERROR = -2;


    /**
     * 文件的相关单位转换
     */
    public static final long BYTE_UNIT_PER_KILO = 1024;
    public static final long KILO_UNIT_PER_MEGA = 1024;
    public static final long MEGA_UNIT_PER_GIGA = 1024;
    public static final long BYTE_UNIT_PER_MEGA = BYTE_UNIT_PER_KILO * KILO_UNIT_PER_MEGA;
    public static final long BYTE_UNIT_PER_GIGA = BYTE_UNIT_PER_MEGA * MEGA_UNIT_PER_GIGA;
    public static final long BYTE_UNIT_PER_TERA = BYTE_UNIT_PER_MEGA * BYTE_UNIT_PER_GIGA;

}
