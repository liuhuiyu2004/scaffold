package com.liuhuiyu.scaffold.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * 基础拦截器
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-13 8:19
 */
public abstract class BaseAspect {
    /**
     * 当前请求时 ResponseBody 类型请求
     * @param pjp   ProceedingJoinPoint
     * @return  请求是否为 ResponseBody
     */
    protected Boolean isResponseBody (ProceedingJoinPoint pjp){
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(ResponseBody.class)!=null;
    }
}
