package com.liuhuiyu.scaffold.constant.enums;

/**
 * 返回信息集合
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-08 11:45
 */
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    CUSTOM_ERROR(-1,"自定义错误"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
