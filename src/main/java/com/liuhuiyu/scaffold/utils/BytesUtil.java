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
        FileUtils.writeByteArrayToFile(new File(fullFileName), data);
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

    /**
     * 过滤数据
     */
    final static int FILTRATION_CODE = 0xFF;

    /**
     * 截取字节码 转换成 int
     *
     * @param bytes 字节码
     * @param begin 起始索引
     * @param len   长度
     * @param asc   0->N(true);N-0(false)
     * @return int
     */
    public static int bytesToInt(@NotNull byte[] bytes, int begin, int len, boolean asc) throws IllegalArgumentException {
        int res = 0;
        int endPointer = begin + len - 1;
        if (begin < 0 || endPointer > bytes.length) {
            throw new IllegalArgumentException("索引参数超出 bytes 有效范围。");
        }
        if (asc) {
            for (int i = endPointer; i >= begin; i--) {
                res <<= 8;
                res |= (bytes[i] & FILTRATION_CODE);
            }
        }
        else {
            for (int i = begin; i <= endPointer; i++) {
                res <<= 8;
                res |= (bytes[i] & FILTRATION_CODE);
            }
        }
        return res;
    }

    /**
     * int 转 byte[]
     *
     * @param value int
     * @param len   byte[] 长度
     * @param asc   顺序
     * @return int
     * @throws IllegalArgumentException 参数取值错误
     */
    public static @NotNull byte[] intToBytes(int value, int len, boolean asc) throws IllegalArgumentException {
        if (len > 4 || len < 1) {
            throw new IllegalArgumentException("len 取值范围(1-4)");
        }
        byte[] buf = new byte[len];
        if (asc) {
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
        else {
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
        return buf;
    }

    /**
     * 使用int 填充 byte[]
     * @param value int
     * @param buf   要填充的 byte[]
     * @param begin 开始位置
     * @param len   长度
     * @param asc   顺序
     */
    public static void intFullBytes(int value, @NotNull byte[] buf, int begin, int len, boolean asc) throws IllegalArgumentException {
        if (len > 4 || len < 1) {
            throw new IllegalArgumentException("len 取值范围(1-4)");
        }
        int endPointer = begin + len - 1;
        if(endPointer>buf.length){
            throw new IllegalArgumentException("begin + len 超出 buf最大长度");
        }
        if (asc) {
            for (int i = begin; i <= endPointer; i++) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
        else {
            for (int i = endPointer; i >= begin; i--) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
    }

    public static long bytesToLong(@NotNull byte[] bytes,int begin,int len,boolean asc)throws IllegalArgumentException{
        int res = 0;
        int endPointer = begin + len - 1;
        if (begin < 0 || endPointer > bytes.length) {
            throw new IllegalArgumentException("索引参数超出 bytes 有效范围。");
        }else if(len>8|len<1){
            throw new IllegalArgumentException("len超出可用有效范围(1-8)。");
        }
        if (asc) {
            for (int i = endPointer; i >= begin; i--) {
                res <<= 8;
                res |= (bytes[i] & FILTRATION_CODE);
            }
        }
        else {
            for (int i = begin; i <= endPointer; i++) {
                res <<= 8;
                res |= (bytes[i] & FILTRATION_CODE);
            }
        }
        return res;
    }

    /**
     * long 转 byte[]
     *
     * @param value int
     * @param len   byte[] 长度
     * @param asc   顺序
     * @return int
     * @throws IllegalArgumentException 参数取值错误
     */
    public static @NotNull byte[] longToBytes(int value, int len, boolean asc) throws IllegalArgumentException {
        if (len > 8 || len < 1) {
            throw new IllegalArgumentException("len 取值范围(1-8)");
        }
        byte[] buf = new byte[len];
        if (asc) {
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
        else {
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
        return buf;
    }
    /**
     * 使用 long 填充 byte[]
     * @param value long
     * @param buf   要填充的 byte[]
     * @param begin 开始位置
     * @param len   长度
     * @param asc   顺序
     */
    public static void longFullBytes(long value,@NotNull byte[] buf,int begin,int len,boolean asc)throws IllegalArgumentException {
        if (len > 8 || len < 1) {
            throw new IllegalArgumentException("len 取值范围(1-8)");
        }
        int endPointer = begin + len - 1;
        if(endPointer>buf.length){
            throw new IllegalArgumentException("begin + len 超出 buf最大长度");
        }
        if (asc) {
            for (int i = begin; i <= endPointer; i++) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
        else {
            for (int i = endPointer; i >= begin; i--) {
                buf[i] = (byte) (value & FILTRATION_CODE);
                value >>= 8;
            }
        }
    }
}
