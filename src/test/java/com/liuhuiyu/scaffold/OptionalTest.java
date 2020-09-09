package com.liuhuiyu.scaffold;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-09 8:31
 */
@Log4j2
public class OptionalTest extends TestCase {
    public void test01() {

        OptionalTest java8Tester = new OptionalTest();
        Integer value1 = null;
        Integer value2 = 10;
        Random random = new Random();
        if (random.nextInt(15) > 10) {
            value1 = 15;
        }

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        log.debug(java8Tester.sum(a, b));
    }

    public <T extends Integer> Integer sum(Optional<T> a, Optional<T> b) {
        log.debug("第一个参数值存在: " + a.isPresent());
        log.debug("第二个参数值存在: " + b.isPresent());
        if (!a.isPresent() | !b.isPresent()) {
            return null;
        }
        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(null);
        //Optional.get - 获取值，值需要存在
        Integer value2 = b.orElse(null);
        return value1 + value2;
    }

    //    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
//    public static <T> StreamEx<T> of(Optional<? extends T> optional) {
//        return optional.isPresent() ? of(optional.get()) : empty();
//    }
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T, J extends T> T s1(Optional<J> a, J b) {
        return a.orElse(b);
    }

    static <T, V extends T> boolean isIn(T x, V[] y) {
        for (int i = 0; i < y.length; i++) {
            if (x.equals(y[i])) {
                return true;
            }
        }
        return false;
    }
}
