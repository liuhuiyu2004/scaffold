package com.liuhuiyu.scaffold.aspect;

import com.liuhuiyu.scaffold.annotation.UserLogin;
import com.liuhuiyu.scaffold.constant.AspectPrecedenceConstant;
import com.liuhuiyu.scaffold.constant.enums.ResultEnum;
import com.liuhuiyu.scaffold.exception.LhyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-08-13 8:21
 */
//@Aspect
//@Component
//@Order(AspectPrecedenceConstant.LOGIN_ASPECT_PRECEDENCE)
public class LoginAspect extends BaseAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Around(value = "within(com.liuhuiyu.scaffold.controller.web.login..*) && @annotation(log)")
    public Object logAround(ProceedingJoinPoint pjp, UserLogin login) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) {
            logger.error("ServletRequestAttributes = null");
            throw new LhyException(ResultEnum.CUSTOM_ERROR, "ServletRequestAttributes 空指针异常。");
        }
        HttpServletRequest request = attributes.getRequest();
        logger.debug(request.getRequestURL().toString());
        HttpServletResponse response = attributes.getResponse();
//        if(login.isLogin() && !SessionUtil.isLogin()) {//未登陆用户申请登陆用户能访问的页面
//            if(isResponseBody(pjp)) {
//                //ajax模式
//                return ResultUtil.error(ResultEnum.USER_LOGGED_ERROR.getCode(), "用户未登录");
//            }
//            else {
//                //网页模式
//                Objects.requireNonNull(response).sendRedirect("/login/login");
//                return PageAddressConstant.LOGIN_PAGE;
//            }
//        }
//        else if(!login.isLogin() && SessionUtil.isLogin()) {//已登陆用户申请未登录用户能访问的页面
//            if(isResponseBody(pjp)) {
//                //ajax模式
//                return ResultUtil.error(ResultEnum.USER_LOGGED_ERROR.getCode(), "用户已经登录");
//            }
//            else {
//                if(SessionUtil.getUser().isRootAuthority()) {
//                    Objects.requireNonNull(response).sendRedirect(ManageHomeController.getIndexAddress());
//                }
//                else {
//                    Objects.requireNonNull(response).sendRedirect(UserHomeController.getIndexAddress());
//                }
//                return false;
//            }
//        }
//        else {
            return pjp.proceed();
//        }
    }
}
