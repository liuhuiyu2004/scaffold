package com.liuhuiyu.scaffold.method_references;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-14 14:43
 */
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
