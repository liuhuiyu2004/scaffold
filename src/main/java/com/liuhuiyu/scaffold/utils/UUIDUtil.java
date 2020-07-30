package com.liuhuiyu.scaffold.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-30 15:52
 */
public class UUIDUtil {
    /**
     * 生成32位UUID编码
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
