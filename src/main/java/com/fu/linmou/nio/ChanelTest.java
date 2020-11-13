package com.fu.linmou.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.SortedMap;

/**
 *
 * 通道（channel）用于源节点与目标节点的连接，
 *   在java.nio中负责缓冲区中数据的传输，
 *    本身不存数据的，需要配合缓冲区进行传输。
 *
 *  通道的一些主要实现类
 *  java.nio.Channel接口.
 *     FileChannel：
 *     SocketChannel
 *     ServerSocketChannel
 *     DatagramChannel
 *
 *  获取通道
 *    1.java针对支持通道的类提供了getChannel()方法
 *      本地IO操作
 *      FileInputStream/FileOutStream
 *      RandomAccessFile
 *      网络Io
 *        Socket
 *        ServerSocket
 *        DatagramSocket
 *   2.JDK.1.7中的nio.2针对各个通道提供了个静态方法open()
 *   3.JDK.1.7中的nio.2的一个Files工具类的newByteChannel()
 *
 *  通道之间的数据传输
 *    tranferFrom()
 *    tranferTo()
 *
 *  分散（Scatter）与聚集（Gather）
 *  分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 *  聚集写入（Gathering Writes）：将多个缓冲区的数据聚集到一个通道中
 *  
 *  字符集 charset
 *  编码：字符串转化为字节数组
 *  解码：字节数组转换为字符串
 *  
 *
 * @author linMou
 * @Description:
 * @Date: 2020/7/31 11:01
 * @Version: 1.0
 */
public class ChanelTest {

    //1.利用通道完成文件的复杂(非直接缓冲区)
    @Test
    public void Test01() throws IOException {
        FileInputStream fis = new FileInputStream("D://1.jpg");
        FileOutputStream fos = new FileOutputStream("D://2.jpg");

        //获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //将通道中的数据存入缓冲区中
        while(inChannel.read(buf)!= -1) {
            //切换成读数据的模式
            buf.flip();
            //将缓冲区中的数据写入通道
            outChannel.write(buf);
            //清空缓冲区
            buf.clear();
        }
        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();
    }

    //直接缓冲区（内存映射的方式）
    @Test
    public void Test02() throws IOException {
        FileChannel in = FileChannel.open(Paths.get("D://1.jpg"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("D://2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW,StandardOpenOption.READ);
        //内存映射文件，只有bytebuf支持
        MappedByteBuffer inMap = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
        MappedByteBuffer outMap = out.map(FileChannel.MapMode.READ_WRITE, 0, in.size());

        //直接对缓冲区进行对数据的读写操作
        byte[] by = new byte[inMap.limit()];
        inMap.get(by);
        outMap.put(by);

        out.close();
        in.close();
    }

    //通道之间的数据传输（直接缓冲区的方式
    @Test
    public void Test03() throws IOException {
        FileChannel in = FileChannel.open(Paths.get("D://1.jpg"), StandardOpenOption.READ);
        FileChannel out = FileChannel.open(Paths.get("D://2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW,StandardOpenOption.READ);

        //in.transferTo(0,in.size(),out);
        out.transferFrom(in,0,in.size());
         out.close();
         in.close();
    }

    //分散与聚集
    @Test
    public void Test04() throws IOException {
        RandomAccessFile file = new RandomAccessFile("D://1.jpg","rw");
        //得到通道
        FileChannel channel = file.getChannel();
        //分配指定大小的缓冲区
        ByteBuffer b1 = ByteBuffer.allocate(100);
        ByteBuffer b2 = ByteBuffer.allocate(1024*40);
        ByteBuffer[] bs = new ByteBuffer[]{b1,b2};
        channel.read(bs);
        for(ByteBuffer b: bs) {
            b.flip();
        }

        System.out.println(new String(bs[0].array(),0,bs[0].limit()));
        System.out.println(new String(bs[1].array(),0,bs[1].limit()));

        //聚集写入
        RandomAccessFile file2 = new RandomAccessFile("D://2.jpg","rw");
        FileChannel channel1 = file2.getChannel();
        channel1.write(bs);

        //file2.close();
        //file.close();
    }
    
    @Test
    public void test05() {
       Map<String, Charset> map = Charset.availableCharsets();
    }
    
    @Test
    public void test06() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");
        //获取编码器与解码器
        
        //编码器
        CharsetEncoder charsetEncoder = gbk.newEncoder();
        //解码器
        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer buf = CharBuffer.allocate(1024);
        buf.put("有点无脑");
        buf.flip();
        
        //编码
        ByteBuffer encode = charsetEncoder.encode(buf);
        encode.flip();
        //解码
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println("-----------");

    }
}
