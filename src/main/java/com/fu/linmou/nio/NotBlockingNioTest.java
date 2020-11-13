package com.fu.linmou.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/7/31 15:49
 * @Version: 1.0
 */
public class NotBlockingNioTest {

    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("localhost", 9999));
        //切换成非阻塞式模式
        sChannel.configureBlocking(false);

        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //发送数据给服务端
        buf.put(LocalDateTime.now().toString().getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        sChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        ssChannel.configureBlocking(false);

        ssChannel.bind(new InetSocketAddress(9999));
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上。
        //并且指定监听接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮训式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //获取当前选择器中所有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();

                if(key.isAcceptable()) {
                    //若接收就绪，则获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();
                    //切换成非阻塞式模式
                    sChannel.configureBlocking(false);
                    //将该通道注册到选择器上
                    sChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()) {
                    //获取当前选择器读就绪状态的通道
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    if((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }

                }
                //取消选择键
                it.remove();
            }
        }
    }
}
