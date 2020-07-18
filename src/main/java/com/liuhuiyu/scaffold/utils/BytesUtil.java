package com.liuhuiyu.scaffold.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-18 9:08
 */
public class BytesUtil {
    /**
     * 字节码 16进制输出
     *
     * @param data byte数据
     * @return 输出字符串
     */
    public static @NotNull String bytesToString(byte @NotNull [] data) {
        StringBuilder info = new StringBuilder();
        for (byte b : data) {
            String s = Integer.toHexString(b & 0xFF).toUpperCase();
            if (s.length() == 1) {
                s = "0" + s;
            }
            info.append(" ").append(s);
        }
        return info.toString();
    }

    /**
     * 字节码转换成 int
     *
     * @param bytes 要解析的字节码
     * @return 转换的数字
     */
    @Contract(pure = true)
    public static int byteArrayToInt(byte @NotNull [] bytes) {
        if (bytes.length > 4) {
            bytes = Arrays.copyOf(bytes, 4);
        }
        byte[] b1 = new byte[4];
        for (int i = bytes.length; i > 0; i--) {
            b1[b1.length - i] = bytes[bytes.length - i];
        }
        return b1[3] & 0xFF |
                (b1[2] & 0xFF) << 8 |
                (b1[1] & 0xFF) << 16 |
                (b1[0] & 0xFF) << 24;
    }
}
