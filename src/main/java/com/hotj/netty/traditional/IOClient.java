package com.hotj.netty.traditional;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/15
 */
public class IOClient {
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8088);

                while (true) {
                    try {
                        String x = new Date() + ": hello world";
                        socket.getOutputStream().write(x.getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.out.println(e
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
    }
}
