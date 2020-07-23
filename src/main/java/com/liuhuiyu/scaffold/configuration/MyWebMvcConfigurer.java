package com.liuhuiyu.scaffold.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-23 8:56
 */
@Configuration
@EnableWebMvc
public class MyWebMvcConfigurer  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/common/**").addResourceLocations("classpath:/static/common/");
        registry.addResourceHandler("/file/**").addResourceLocations("classpath:/static/file/");
        registry.addResourceHandler("/system/**").addResourceLocations("classpath:/static/system/");
    }
}
