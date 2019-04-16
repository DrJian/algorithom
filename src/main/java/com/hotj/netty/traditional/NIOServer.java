package com.hotj.netty.traditional;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author hongjian@youzan.com
 * @date 2019/4/15
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.socket().bind(new InetSocketAddress(8088));
                listenerChannel.configureBlocking(false);
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                    //检测是否有新连接
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();

                            if (key.isAcceptable()) {
                                try {
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e
                        + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                        + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    //批量轮询可读连接
                    Set<SelectionKey> set = clientSelector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = set.iterator();

                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();

                        if (key.isReadable()) {
                            try {
                                SocketChannel clientChannel = (SocketChannel) key.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                //以块为单位读取数据
                                clientChannel.read(byteBuffer);
                                byteBuffer.flip();
                                System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
                                        + " Class:" + new Throwable().getStackTrace()[0].getClassName()
                                        + " Line:" + new Throwable().getStackTrace()[0].getLineNumber());
                            } finally {
                                keyIterator.remove();
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
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
