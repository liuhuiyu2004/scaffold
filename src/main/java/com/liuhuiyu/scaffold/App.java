package com.liuhuiyu.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-08 11:33
 */
//@EnableAutoConfiguration
@SpringBootApplication
//@ServletComponentScan   //可以替代 configuration.FilterConfig
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}