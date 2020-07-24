package com.liuhuiyu.scaffold.domain.model;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-08 11:44
 */
@Data
public class Result<T> implements Serializable {
    public static final int OK = 0;
    public static final int ERROR = -1;

    /**
     * 返回码
     */
    private Integer flag;
    /**
     * 信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public Result() {
        this.flag = OK;
        this.msg = "操作成功";
        this.data = null;
    }

    private Result(T data) {
        this();
        this.data = data;
    }

    private Result(T data, Integer flag) {
        this();
        this.data = data;
        this.flag = flag;
    }

    private Result(T data, Integer flag, String msg) {
        this();
        this.data = data;
        this.flag = flag;
        this.msg = msg;
    }

    /**
     * 通过静态方法获取实例
     */
    @Contract(value = "_ -> new", pure = true)
    public static <T> @NotNull Result<T> of(T data) {
        return new Result<>(data);
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static <T> @NotNull Result<T> of(T data, Integer flag) {
        return new Result<>(data, flag);
    }

    @Contract(value = "_, _, _ -> new", pure = true)
    public static <T> @NotNull Result<T> of(T data, Integer flag, String msg) {
        return new Result<>(data, flag, msg);
    }
    @Contract(value = "_ -> new", pure = true)
    public static <T> @NotNull Result<T> error(String msg) {
        return new Result<>(null, ERROR, msg);
    }

}