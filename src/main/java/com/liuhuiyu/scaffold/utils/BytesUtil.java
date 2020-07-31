package com.liuhuiyu.scaffold.utils;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
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
                info.append(" 0");
            }
            else {
                info.append(" ");
            }
            info.append(s);
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

    /**
     * 保存byte[]到文件
     *
     * @param data         byte[]
     * @param fullFileName 文件名
     * @throws IOException IO错误
     */
    public static void bytesToFile(byte[] data, String fullFileName) throws IOException {
        FileUtils.writeByteArrayToFile(new File(fullFileName),data);
//        FileOutputStream fos = new FileOutputStream(fullFileName);
//        fos.write(data, 0, data.length);
//        fos.flush();
//        fos.close();
    }

    /**
     * 读取文件转换成 byte[]
     *
     * @param fullFileName 文件名
     * @return byte[]
     * @throws IOException IO错误
     */
    public static byte @NotNull [] fileToBytes(String fullFileName) throws IOException {
        return FileUtils.readFileToByteArray(new File(fullFileName));
//        File file = new File(fullFileName);
//        long fileSize = file.length();
//        FileInputStream fileInputStream = new FileInputStream(fullFileName);
//        byte[] buffer = new byte[(int) fileSize];
//        int offset = 0;
//        int numRead = 0;
//        while (offset < buffer.length
//                && (numRead = fileInputStream.read(buffer, offset, buffer.length - offset)) >= 0) {
//            offset += numRead;
//        }
//        return buffer;
    }
}
