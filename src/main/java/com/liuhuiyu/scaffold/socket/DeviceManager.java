package com.liuhuiyu.scaffold.socket;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * socket 与客户端通信处理
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-18 8:43
 */
public class DeviceManager implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(DeviceManager.class);
    private final Socket socket;

    public DeviceManager(@NotNull Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //加载头部
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            ReceiveMessage receiveMessage = new ReceiveMessage(dataInputStream);
            IDevice device = this.getDevice(socket, dataInputStream, receiveMessage);
            device.dispose();
        }
        catch (IOException e) {
            logger.error("接受数据初始化错误：" + e.getMessage());
        }
    }

    @Contract(pure = true)
    private @NotNull IDevice getDevice(Socket socket, DataInputStream dataInputStream, @NotNull ReceiveMessage receiveMessage) {
        if (receiveMessage.mainVer == 1 && receiveMessage.minorVer == 1) {
            return () -> {
                //这里插入解析分配
                logger.debug(socket.toString() + ";" + dataInputStream.toString());
            };
        }
        else {
            return () -> {
                throw new IOException("版本错误：" + receiveMessage.mainVer + "." + receiveMessage.minorVer);
            };
        }
    }
}