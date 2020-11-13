package com.fu.linmou.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/7/31 15:35
 * @Version: 1.0
 */
public class BlockingNio2Test {

    @Test
    public void Client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",9999));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        FileChannel channel = FileChannel.open(Paths.get("d:/1.jpg"), StandardOpenOption.READ);
        while(channel.read(buf) != -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }

        socketChannel.shutdownOutput();

        //接受服务端的反
        int len = 0;
        while((len = socketChannel.read(buf))!= -1) {
            buf.flip();
            System.out.println(new String(buf.array(),0,len));
            buf.clear();
        }
        channel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9999));
        SocketChannel sChannel = channel.accept();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        FileChannel fileChannel = FileChannel.open(Paths.get("d://2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        while (sChannel.read(buf) != -1) {
            buf.flip();
            fileChannel.write(buf);
            buf.clear();
        }

        //发送反馈给客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        sChannel.write(buf);

        fileChannel.close();
        sChannel.close();
        channel.close();
    }
}
