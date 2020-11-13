package com.fu.linmou.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/7/31 16:35
 * @Version: 1.0
 */
public class NotBlockingNio2Test {

    @Test
    public void send() throws IOException {
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            String str = scan.next();
            buf.put((LocalDateTime.now() + ":  " + str).getBytes());
            buf.flip();
            dc.send(buf,new InetSocketAddress("localhost",9999));
        }
       dc.close();
    }

    @Test
    public void receive() throws IOException {
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        dc.bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();

        dc.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if(key.isReadable()) {
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    buf.flip();
                    System.out.println(new String(buf.array(),0,buf.limit()));
                    buf.clear();
                }
                it.remove();
            }

        }

    }
}
