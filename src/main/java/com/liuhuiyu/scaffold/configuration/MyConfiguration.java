package com.liuhuiyu.scaffold.configuration;

import com.liuhuiyu.scaffold.utils.SpringUtil;
import org.jetbrains.annotations.NotNull;
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
@ConfigurationProperties(prefix = "myconfiguration")
public class MyConfiguration {
    public static @NotNull MyConfiguration getInstance() {
        return SpringUtil.getBean(MyConfiguration.class);
    }
    /**
     * socket端口
     */
    private int socketPost;
    /**
     * 安全通讯
     */
    private boolean safety;


    public int getSocketPost() {
        return socketPost;
    }

    public void setSocketPost(int socketPost) {
        this.socketPost = socketPost;
    }

    public boolean isSafety() {
        return safety;
    }

    public void setSafety(boolean safety) {
        this.safety = safety;
    }
}
