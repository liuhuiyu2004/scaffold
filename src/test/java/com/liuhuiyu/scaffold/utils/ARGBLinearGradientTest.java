package com.liuhuiyu.scaffold.utils;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import javax.swing.text.StyledEditorKit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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

    public void testMinMaxValue() {
        StringBuilder stringBuilder = new StringBuilder();
        Integer[] i = getIntList();

//        for (int outI : i) {
//            stringBuilder.append(" ").append(outI);
//        }
//        log.debug(stringBuilder);

        long start = System.currentTimeMillis();
        this.minMax01(i);
        long end = System.currentTimeMillis();
        log.debug("{}组数据耗时：{}毫秒", i.length, end - start);
//        start = System.currentTimeMillis();
//        this.minMax02(i);
//        end = System.currentTimeMillis();
//        log.debug("{}组数据耗时：{}毫秒", i.length, end - start);
        start = System.currentTimeMillis();
        this.minMax03(i);
        end = System.currentTimeMillis();
        log.debug("{}组数据耗时：{}毫秒", i.length, end - start);
    }

    private void minMax01(Integer[] intList) {
        int count = intList.length;
        Integer min = intList[0];
        Integer max = intList[0];
        for (int i = 1; i < count; i++) {
            if (intList[i] < min) {
                min = intList[i];
            }
            else if (intList[i] > max) {
                max = intList[i];
            }
        }
        log.debug("min:{};max:{};", min, max);
    }

    private void minMax02(Integer[] intList) {
        Integer min = Collections.min(Arrays.asList(intList));
        Integer max = Collections.max(Arrays.asList(intList));
        log.debug("min:{};max:{};", min, max);
    }

    private void minMax03(Integer[] intList) {
        Integer min;
        Integer max;
        int count = intList.length;
        if (intList[0] > intList[1]) {
            max = intList[0];
            min = intList[1];
        }
        else {
            min = intList[0];
            max = intList[1];
        }
        for (int i = 2; i < count; i += 2) {
            if (intList[i] > intList[i + 1]) {
                if (max < intList[i]) {
                    max = intList[i];
                }
                if (min > intList[i + 1]) {
                    min = intList[i + 1];
                }
            }
            else {
                if (max < intList[i + 1]) {
                    max = intList[i + 1];
                }
                if (min > intList[i]) {
                    min = intList[i];
                }
            }
        }
//        int lastIndex=count * 2;
//        if (lastIndex < intList.length) {
//            if(intList[lastIndex]>max){
//                max=intList[lastIndex];
//            }else if(intList[lastIndex]<min){
//                min=intList[lastIndex];
//            }
//        }
        log.debug("min:{};max:{};", min, max);
    }

    private Integer[] getIntList() {
        Random random = new Random();
//        int i = random.nextInt(100);
        int[] list = random.ints(10_000_000).toArray();
        return Arrays.stream(list).boxed().toArray(Integer[]::new);
    }
}