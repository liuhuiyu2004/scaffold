package com.liuhuiyu.scaffold.controller;

import com.liuhuiyu.scaffold.constant.enums.ResultEnum;
import com.liuhuiyu.scaffold.domain.model.Result;
import com.liuhuiyu.scaffold.exception.LhyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import javax.persistence.RollbackException;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-11 8:29
 */
@ControllerAdvice
@Log4j2
public class ExceptionController {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody   /*包装成Json*/
    public Result<String> handle(Exception e) {
        if (e instanceof LhyException) {
            LhyException fileManagementException = (LhyException) e;
            return Result.error(fileManagementException.getMessage());
        }
        else if (e instanceof MultipartException) {
            log.error("[MultipartException]" + e.getMessage());
            return Result.error(e.getMessage());
        }
        else if (e instanceof RollbackException) {
            log.error("[MultipartException]" + e.getMessage());
            return Result.error("[数据校验错误]" + e.getMessage());
        }
        else {
            log.error("[系统异常]" + e.getMessage());
            return Result.error("未知错误");
        }
    }
}
