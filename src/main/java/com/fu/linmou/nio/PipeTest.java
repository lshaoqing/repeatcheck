package com.fu.linmou.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/7/31 16:46
 * @Version: 1.0
 */
public class PipeTest {

    @Test
    public void test01() throws IOException {
        Pipe pipe = Pipe.open();
        
        //将缓冲区中的数据写入管道
        Pipe.SinkChannel sink = pipe.sink();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("通过管道发送数据".getBytes());
        buffer.flip();
        sink.write(buffer);

        Pipe.SourceChannel source = pipe.source();
        buffer.flip();
        int len = source.read(buffer);
        System.out.println(new String(buffer.array(),0,len));


    }
}
