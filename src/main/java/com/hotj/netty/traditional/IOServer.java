package com.hotj.netty.traditional;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/15
 */
public class IOServer {
    public static void main(String[] args) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(8088);

        //监听新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    //阻塞等待新连接
                    Socket socket = serverSocket.accept();

                    new Thread(() -> {
                        try {
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while (true) {
                                int len;
                                while((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len)
                                            + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                                            + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
                                }
                            }
                        } catch (IOException e) {
                            System.out.println(e
                                    + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                                    + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
                        }
                    }).start();
                } catch (IOException e) {
                    System.out.println(e
                            + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                            + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
                }
            }
        }).start();
    }
}
