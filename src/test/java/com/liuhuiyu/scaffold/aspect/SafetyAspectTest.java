package com.liuhuiyu.scaffold.aspect;

import junit.framework.TestCase;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-12 8:12
 */
public class SafetyAspectTest extends TestCase {
    public void testSafetyAspect(){
        new SafetyAspect().safetyAspect();
    }
}