package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-27 8:32
 */
@Log4j2
public class ARGBLinearGradientTest extends TestCase {
    public void testARGBLinearGradient() {
        LinearGradient a = new LinearGradient(new int[]{0, 0, 255, 255});
        LinearGradient r = new LinearGradient(new int[]{0, 0, 255, 255});
        LinearGradient g = new LinearGradient(new int[]{0, 0, 255, 255});
        LinearGradient b = new LinearGradient(new int[]{0, 0, 255, 255});
        ARGBLinearGradient argbLinearGradient = new ARGBLinearGradient(a, r, g, b, 0, 255);
        for (int i = 0; i < 255; i++) {
            log.debug("i=" + i + ";out = " + argbLinearGradient.getRgb(i));
        }
        int v = 255;
        int j = 0;
        for (int i = 0; i < 4; i++) {
            j += v << (i * 8);
        }
        log.debug("16进制输出 1<<8=" + Integer.toUnsignedString(j, 16));
        log.debug("2进制输出 1<<8=" + Integer.toUnsignedString(j, 2));
        log.debug("10进制输出 1<<8=" + Integer.toUnsignedString(j));
    }
}