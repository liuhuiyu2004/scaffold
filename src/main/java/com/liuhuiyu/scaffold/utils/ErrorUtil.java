package com.liuhuiyu.scaffold.utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 捕获报错日志处理工具类
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-24 9:05
 */
public class ErrorUtil {

    /**
     * Exception出错的栈信息转成字符串
     * @param e 错误信息
     * @return  错误信息
     */
    public static String errorInfoToString(@NotNull Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}