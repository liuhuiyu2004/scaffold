package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-26 11:42
 */
@Log4j2
public class LinearGradientTest extends TestCase {
    public void testLinearGradient() {
        int inValue = 70;
        LinearGradient linearGradient = new LinearGradient(new int[]{0, 0, 128, 255, 255, 70});
        int v = linearGradient.getValue(inValue);
        log.debug("in:" + inValue + ";out:" + v);
    }
}