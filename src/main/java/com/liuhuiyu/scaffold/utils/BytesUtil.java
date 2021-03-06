package com.liuhuiyu.scaffold.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;

/**
 * byte数组工具类
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-18 9:08
 */
@Log4j2
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
        return bytesToInt(bytes, 0, 4, true);
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
     *
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
        if (endPointer > buf.length) {
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

    public static long bytesToLong(@NotNull byte[] bytes, int begin, int len, boolean asc) throws IllegalArgumentException {
        int res = 0;
        int endPointer = begin + len - 1;
        if (begin < 0 || endPointer > bytes.length) {
            throw new IllegalArgumentException("索引参数超出 bytes 有效范围。");
        }
        else if (len > 8 | len < 1) {
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
     *
     * @param value long
     * @param buf   要填充的 byte[]
     * @param begin 开始位置
     * @param len   长度
     * @param asc   顺序
     */
    public static void longFullBytes(long value, @NotNull byte[] buf, int begin, int len, boolean asc) throws IllegalArgumentException {
        if (len > 8 || len < 1) {
            throw new IllegalArgumentException("len 取值范围(1-8)");
        }
        int endPointer = begin + len - 1;
        if (endPointer > buf.length) {
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


    /**
     * 二进制转十六进制
     */
    public static @NotNull String bytesToHex(byte @NotNull [] bytes) {
        StringBuilder hexStr = new StringBuilder();
        int num;
        for (byte aByte : bytes) {
            num = aByte;
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }

    /**
     * Object对象转byte[]
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            //开始写入输出流
            oo.writeObject(obj);
            //输出流转byte
            bytes = bo.toByteArray();
        }
        catch (Exception e) {
            //输出到日志文件中
            log.error(ErrorUtil.errorInfoToString(e));
        }
        finally {
            //关闭流
            try {
                assert bo != null;
                bo.close();
                assert oo != null;
                oo.close();
            }
            catch (IOException e) {
                //输出到日志文件中
                log.error(ErrorUtil.errorInfoToString(e));
            }
        }
        return bytes;
    }

    /**
     * byte[]转Object对象
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            //读取输入流
            obj = oi.readObject();
        }
        catch (Exception e) {
            //输出到日志文件中
            log.error(ErrorUtil.errorInfoToString(e));
        }
        finally {
            //关流
            try {
                assert bi != null;
                bi.close();
                assert oi != null;
                oi.close();
            }
            catch (IOException e) {
                //输出到日志文件中
                log.error(ErrorUtil.errorInfoToString(e));
            }
        }
        return obj;
    }
}
