package com.liuhuiyu.scaffold.utils;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-24 16:50
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(SpringUtil.class);
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        logger.info("--------------------------------------------------------------");
        logger.info("----------------------.util.SpringUtil\"----------------------");
        logger.info("===ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext=%1$s==={}",SpringUtil.applicationContext);
        logger.info("--------------------------------------------------------------");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static @NotNull Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> @NotNull T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> @NotNull T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
