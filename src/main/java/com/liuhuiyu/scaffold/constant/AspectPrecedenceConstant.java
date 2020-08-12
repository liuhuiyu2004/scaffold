package com.liuhuiyu.scaffold.constant;

/**
 * 拦截器等级设置
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-12 8:07
 */
public class AspectPrecedenceConstant {
    public static final int SAFETY_ASPECT_PRECEDENCE = 10;
    /**
     * 登陆拦截优先级
     */
    public static final int LOGIN_ASPECT_PRECEDENCE = 30;
    /**
     * 权限拦截优先级
     */
    public static final int PURVIEW_ASPECT_PRECEDENCE = 50;
    /**
     * 日志拦截优先级
     */
    public static final int LOG_ASPECT_PRECEDENCE = 90;
    /**
     * 登陆拦截优先级
     */
    public static final int WEB_ASPECT_PRECEDENCE = 150;
}
