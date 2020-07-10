package com.liuhuiyu.scaffold.exception;
import com.liuhuiyu.scaffold.constant.enums.ResultEnum;
import org.jetbrains.annotations.NotNull;
/**
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-08 11:43
 */
public class LhyException extends RuntimeException {
    private Integer code;
/*
    public ArchivesManagementException(Integer code, String message) {
        super(message);
        this.code = code;
    }
*/

    public LhyException(@NotNull ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public LhyException(@NotNull ResultEnum resultEnum, String message) {
        super(resultEnum.getMessage() + ":" + message);
        this.code = resultEnum.getCode();
    }

    public LhyException(String message, @NotNull ResultEnum resultEnum) {
        super(message + resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public LhyException(String message, @NotNull ResultEnum resultEnum, String message1) {
        super(message + resultEnum.getMessage() + message1);
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

