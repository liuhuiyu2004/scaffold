package com.liuhuiyu.scaffold.annotation;

import java.lang.annotation.*;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-08 11:35
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserLogin {
    /**
     * 用户登录可访问
     * @return  true用户登录可访问
     */
    boolean isLogin() default false;
}