/*
AuthHeaderSettingFilter 头部加入
@Order(1)
@WebFilter(urlPatterns = "/*",filterName = "AuthHeaderFilter")
在启动上加入
@ServletComponentScan
可替代此类
* */

package com.liuhuiyu.scaffold.configuration;

//import com.liuhuiyu.scaffold.filter.AuthHeaderSettingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义过滤器
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-28 15:37
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AuthHeaderSettingFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }
}
