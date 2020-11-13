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
 *
 * 使用NIO完成网络通信的三个核心
 *
 *   通道（channel） 负责连接
 *      java.noi.channel接口
 *         selectableChannel
 *            socketChannel
 *            ServeSocketChannel
 *            DatagramChannel
 *            
 *            Pipe-SinkChannel
 *            Pipe-sourceChannel
 *
 *   缓冲区（Buffer）负责数据的存储
 *   选择器Selector，是SelectableChannel的多路复用器，用于监控selectableChannel的IP状况
 *
 *
 * @author linMou
 * @Description:
 * @Date: 2020/7/31 14:45
 * @Version: 1.0
 */
public class BlockingNioTest {
    
    @Test
    public void client() throws IOException {
        //1.获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9898));

        FileChannel inChannel = FileChannel.open(Paths.get("d://1.jpg"), StandardOpenOption.READ);

        //2.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //读取本地的文件，并发送到服务器
        while (inChannel.read(buf)!= -1) {
            buf.flip();
            socketChannel.write(buf);
            buf.clear();
        }
        inChannel.close();
        socketChannel.close();
    }
    
    
    @Test
    public void server() throws IOException {
        //1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定连接端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        //接受客户端的数据，并保存到本地
        FileChannel outChannel = FileChannel.open(Paths.get("d://2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (socketChannel.read(buf)!= -1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        outChannel.close();
        socketChannel.close();
        serverSocketChannel.close();
    }
}
