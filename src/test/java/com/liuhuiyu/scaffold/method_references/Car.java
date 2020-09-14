package com.liuhuiyu.scaffold.method_references;

import lombok.extern.log4j.Log4j2;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-14 14:43
 */
@Log4j2
public class Car {
    String name;

    public Car() {
        name = "宝马5866";
    }

    public Car(String name) {
        this.name = name;
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        log.debug("Collided " + car.toString());
    }

    public void follow(final Car another) {
        log.debug("Following the " + another.toString());
    }

    public void repair() {
        log.debug("Repaired " + this.toString());
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
