package com.liuhuiyu.scaffold.socket;

import java.io.IOException;

/**
 * 设备通讯接口
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-18 10:20
 */
public interface IDevice {
    /**
     * 通讯处理
     */
    void dispose() throws IOException;
}
