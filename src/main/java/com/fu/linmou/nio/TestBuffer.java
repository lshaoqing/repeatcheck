package com.fu.linmou.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author linMou
 * @Description: 缓冲区(buffer)在java nio中负责数据的存储
 *  缓冲区就是数组，用于存储不同数据类型的数据。
 *  根据数据类型不同(boolean除外)，提供了对应类型的缓冲区
 *  ByteBuffer
 *  CharBuffer
 *  ...
 *  类型的缓冲区方式管理方式几乎一致的，通过allocate获取缓冲区
 *  get获取数据
 *  put存入数据
 *  缓冲区的四个属性
 *   capacity：缓冲区最大存储的容量，一旦声明不能改变
 *   limit： 界限，表示缓存区中可操作数据的大小（limit 后的数据我们不能进行读写）
 *  position：位置，表示缓冲区正在炒作数据的位置
 *   position<=limit <= capacity
 *
 *  非直接缓冲区，用过allocate()方法分配缓冲区，将缓冲区建立在jvm内存中
 *  直接缓冲器，通过allocateDiect()方法分配缓冲区，将缓冲区建立在操作系统的内存中，可以提高效率
 *
 *  目标水准，本节课咱们就来回答这些问题，  网络应用框架，处理的基本都是与网络相关的应用，
 *   html5 关于websocket  提供非常好的支撑，学习的曲线比较陡峭，无论是理论
 *
 *
 * @Date: 2020/7/27 15:08
 * @Version: 1.0
 */
public class TestBuffer {

    @Test
    public void test1() {
        ByteBuffer byf = ByteBuffer.allocate(1024);
        System.out.println("---allocate--");
        System.out.println(byf.position());
        System.out.println(byf.limit());
        System.out.println(byf.capacity());

        //利用put方法存入数据到缓冲区中去
        byf.put("abcde".getBytes());
        System.out.println("---put--");
        System.out.println(byf.position());
        System.out.println(byf.limit());
        System.out.println(byf.capacity());
        //从写数据->读数据模式
        byf.flip();
        System.out.println("---flip--");
        System.out.println(byf.position());
        System.out.println(byf.limit());
        System.out.println(byf.capacity());

        byte[] du = new byte[byf.limit()];
         byf.get(du);
        System.out.println(new String(du,0,du.length));

        //rewind 可重复读数据
        byf.rewind();

        //clear清空缓冲区

        byf.clear();
    }
    
    @Test
    public void test03() {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());

    }
}
