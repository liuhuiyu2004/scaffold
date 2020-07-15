package com.liuhuiyu.scaffold.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.TimerTask;

/**
 * 任务 lambda 包装
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-15 14:32
 */
public class TimerTaskUtil {
    /**
     *  获取计时器任务
     * @param r lambda 表达式
     * @return 计时器任务
     */
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull TimerTask createTimerTask(Runnable r) {
        return new TimerTask() {
            @Override
            public void run() {
                r.run();
            }
        };
    }
}
