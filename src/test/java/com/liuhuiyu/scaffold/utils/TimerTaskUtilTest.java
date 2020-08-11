package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-11 14:40
 */
@Log4j2
public class TimerTaskUtilTest extends TestCase {
    /**
     * 测试延时循环执行任务
     * @throws InterruptedException error
     */
    public void testCreateTimerTask() throws InterruptedException {
        Timer testTimer = new Timer();
        TimerTask timerTask = TimerTaskUtil.createTimerTask(() -> log.debug("测试"));
        testTimer.schedule(timerTask, 0, 500);
        Thread.sleep(10000);
    }
}