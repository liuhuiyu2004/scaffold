package com.liuhuiyu.scaffold.method_references;

import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 方法引用 测试
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-14 14:42
 */
@Log4j2
public class MethodReferencesTest extends TestCase {
    static void abc(MethodReferencesTest a){log.debug("Abc");}
    public void testMethod01() {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Collections.singletonList(car);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

    public void testMethod02() {
        final Car car = Car.create(() -> new Car("98888"));
        final List<Car> cars = Collections.singletonList(car);
        cars.forEach(Car::collide);//cars.forEach((car1) -> { Car.collide(car1); });
        cars.forEach(Car::repair);
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

    public void testMethod03() {
        List<String> names = new ArrayList<>();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
//        names.forEach(System.out::println);
        names.forEach(log::debug);
    }
}
