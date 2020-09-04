package com.liuhuiyu.scaffold;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.util.function.*;

/**
 * 函数式接口测试
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-04 14:11
 */
@Log4j2
public class FunctionalInterfaceTest extends TestCase {

    public void test01() {
        GreetingService greetingService1 = log::debug;
        GreetingService greetingService2 = message -> log.debug("message.length=" + message.length() + ";message=" + message);
        GreetingService2 greetingService21 = (message, id) -> log.debug("ID=" + id + ";Message = " + message);
        GreetingService2 greetingService22 = (message, id) -> log.debug("ID2=" + id + ";Message2 = " + message);
        greetingService1.sayMessage("测试接口1");
        greetingService2.sayMessage("测试接口2");
        greetingService21.sayMessage("测试接口1", 11);
        greetingService22.sayMessage("测试接口2", 12);
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    @FunctionalInterface
    interface GreetingService2 {
        void sayMessage(String message, int id);
    }

    public void test2() {
        BiConsumer<Integer, Integer> biConsumer = (a, b) -> log.debug(a + b);
        BiFunction<Integer, String, Boolean> booleanBiFunction = FunctionalInterfaceTest::apply;
        BinaryOperator<String> binaryOperator = FunctionalInterfaceTest::apply;
        BiPredicate<String, Integer> biPredicate = FunctionalInterfaceTest::test;
        Consumer<String> consumer = (a) -> {
        };
        biConsumer.accept(1, 2);
        boolean b1 = booleanBiFunction.apply(1, "abc");
        String s1 = binaryOperator.apply("a1", "a2");
        boolean b2 = biPredicate.test("a", 1);
        consumer.accept(s1);
        if (b1 && b2) {
            log.debug("b1 == b2");
        }
    }

    private static Boolean apply(Integer a, String b) {
        return b.length() + 1 == a;
    }

    private static String apply(String s, String s1) {
        return s + s1;
    }


    private static boolean test(String a, Integer b) {
        return a.length() == b;
    }
}
