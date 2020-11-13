package com.fu.linmou.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/7/27 15:00
 * @Version: 1.0
 */
public class ttt {
    /**
     * io  水流
     * 阻塞式
     * io就是完成数据的传输
     *     传统io是面向流的
     *     单向的
     *     输入流
     *     输出流
     *
     *  nio 铁路
     *   非阻塞式的
     *    通道， 与目标地点原地点的连接。
     *    缓冲区：理解为火车  双向的。
     *
     *    核心在与 通道Channel和缓冲区Buffer
     *      通道负责传输，缓冲区负责存储
     */


    /**
     *    Server
     *      用户地址空间
     *      内核地址空间
     *
     *   nio非阻塞模式
     *      选择器Selector 选择器
     *
     *  完全准备就绪了以后，那么这个时候选择器才会将这个任务分配到服务端的一个或者多个线程上，再去运行
     *
     *
     */

    public static void main(String[] args) {
        String s = encryptToBase64("D://1.zip");
        System.out.println(s);

        decryptByBase64(s.replace("+","%2B"),"D://2.zip");

    }

    public static String encryptToBase64(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            byte[] b = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

        public static String decryptByBase64(String base64, String filePath) {
        if (base64 == null && filePath == null) {
            return "生成文件失败，请给出相应的数据。";
        }
        try {
            Files.write(Paths.get(filePath), Base64.getMimeDecoder().decode(base64), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "指定路径下生成文件成功！";
    }

    }
