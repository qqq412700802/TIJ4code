package com.lixj.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChannelPosition {
    public static final int BASE_SIZE = 8;

    public static void main(String[] args) throws IOException {
        //初始化
//        FileChannel inFileChannel = new FileInputStream("/Down By the Salley Garden.txt").getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(BASE_SIZE);


        System.out.println(byteBuffer);
//        System.out.println("读取前通道的文件指针：" + inFileChannel.position());
//        System.out.println("读取前缓冲器的指针：" + byteBuffer.position());
//        System.out.println("读取前缓冲器的限制位置：" + byteBuffer.limit());
//        System.out.println("读取前缓冲器的容量位置：" + byteBuffer.capacity());
//        System.out.println("读取前缓冲器的标记位置：" + byteBuffer.mark());


    }

    private String formatByteBuffer(ByteBuffer byteBuffer) {
//        Pattern pattern = Pattern.compile(".*\\[(.*)\\]");
        Pattern.compile(".*\\[(.*)\\]");

//        Matcher matcher = pattern.matcher(byteBuffer.toString());

        return null;
    }
}
