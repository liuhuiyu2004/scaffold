package com.liuhuiyu.scaffold.utils;

import com.liuhuiyu.scaffold.constant.enums.ResultEnum;
import com.liuhuiyu.scaffold.exception.LhyException;

import java.util.function.Supplier;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-09-10 8:41
 */
public class LhyAssert {


    public static void assertTrue(boolean value, String message) {
        if (!value) {
            throw new LhyException(message, ResultEnum.CUSTOM_ERROR);
        }
    }

    /**
     * 对象不为空则抛出异常
     *
     * @param value      对象
     * @param resultEnum 错误枚举
     */
    public static void assertTrue(boolean value, ResultEnum resultEnum) {
        if (!value) {
            throw new LhyException(resultEnum);
        }
    }

    /**
     * 对象为空则抛出异常
     *
     * @param object 对象
     */
    public static void assertNotNull(Object object, String message) {
        if (object == null) {
            throw new LhyException(message, ResultEnum.CUSTOM_ERROR);
        }
    }

    /**
     * 对象为空则抛出异常
     *
     * @param object     对象
     * @param resultEnum 错误枚举
     */
    public static void assertNotNull(Object object, ResultEnum resultEnum) {
        assertTrue(object != null, resultEnum);
    }

    /**
     * 对象不为空则抛出异常
     *
     * @param object 对象
     */
    public static void assertNull(Object object, String message) {
        assertTrue(object == null, message);
    }

    /**
     * 对象不为空则抛出异常
     *
     * @param object     对象
     * @param resultEnum 错误枚举
     */
    public static void assertNull(Object object, ResultEnum resultEnum) {
        assertTrue(object == null, resultEnum);
    }

    public static void assertFalse(boolean value, String message) {
        assertTrue(!value, message);
    }
}
