package com.liuhuiyu.scaffold.socket;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

/**
 * Socket 多线程 socket 服务器
 *
 * @author LiuHuiYu
 * @version v1.0.0.0
 * Created DateTime 2020-07-13 19:38
 */
public class SocketServer implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(SocketServer.class);
    private final int port;
    boolean isRun = false;

    /**
     * 静态多线程服务器运行
     *
     * @param post 监控端口号
     * @return 服务器
     */
    public static @NotNull SocketServer serverRun(int post) {
        SocketServer deviceServer = new SocketServer(post);
        new Thread(deviceServer).start();
        return deviceServer;
    }

    public SocketServer(int post) {
        this.port = post;
    }

    /**
     * 启动服务器(建议使用 静态 serverRun 执行)
     */
    @Override
    public void run() {
        ServerSocket serverSocket;
        this.isRun = true;
        try {
            serverSocket = new ServerSocket(this.port);
        }
        catch (IOException e) {
            logger.error("服务器启动错误：" + e.getMessage());
            this.isRun = false;
            return;
        }
        Socket socket;
        logger.debug("开启arm通信监听，端口：" + this.port + "。");
        long start = System.nanoTime();        //循环约耗时333000ns=0.333ns 1秒循环3000+
        while (this.isRun) {
            try {
                logger.debug("正在监听。耗时" + (System.nanoTime() - start) + "ns");
                socket = serverSocket.accept();
                DeviceManager deviceManager = new DeviceManager(socket);
                new Thread(deviceManager).start();
            }
            catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * 服务器停止
     * @throws IOException  IOException 交互错误
     */
    public void stop() throws IOException {
        if (this.isRun) {
            this.isRun = false;
            InetAddress locAdd = InetAddress.getLocalHost();
            Socket socket = new Socket(locAdd, this.port);
            OutputStream os = socket.getOutputStream();
            byte[] closeBytes = new byte[]{0, 2};
            os.write(closeBytes);
            os.flush();
        }
    }
}
