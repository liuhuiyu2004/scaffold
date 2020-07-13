package com.liuhuiyu.scaffold.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-13 19:38
 */
@Component
@ConfigurationProperties(prefix = "myConfiguration")
public class MyConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(MyConfiguration.class);

    /**
     * socket端口
     */
    private int socketPost;

    public int getSocketPost() {
        return socketPost;
    }

    public void setSocketPost(int socketPost) {
        this.socketPost = socketPost;
    }
}
