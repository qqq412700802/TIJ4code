package com.lixj.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharChannelPosition {
    public static final int BASE_SIZE = 8;

    public static void main(String[] args) throws IOException {
        //初始化
        FileChannel inFileChannel = new FileInputStream("/Down By the Salley Garden.txt").getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(BASE_SIZE);
        byteBuffer.limit(4);

        String[] message = formatByteBuffer(byteBuffer);
        System.out.println("读取前通道的文件指针：" + inFileChannel.position());
        System.out.println("读取前缓冲器的指针：" + message[0]);
        System.out.println("读取前缓冲器的限制位置：" + message[1]);
        System.out.println("读取前缓冲器的容量位置：" + message[2]);

        inFileChannel.read(byteBuffer);

        message = formatByteBuffer(byteBuffer);
        System.out.println("读取后通道的文件指针：" + inFileChannel.position());
        System.out.println("读取后缓冲器的指针：" + message[0]);
        System.out.println("读取后缓冲器的限制位置：" + message[1]);
        System.out.println("读取后缓冲器的容量位置：" + message[2]);

        FileChannel outFileChannel = new FileOutputStream("/out.txt").getChannel();

        System.out.println("输出前通道的文件指针：" + outFileChannel.position());

        outFileChannel.write(byteBuffer);

        message = formatByteBuffer(byteBuffer);

        System.out.println("输出后通道的文件指针：" + outFileChannel.position());
        System.out.println("输出后缓冲器的指针：" + message[0]);
        System.out.println("输出后缓冲器的限制位置：" + message[1]);
        System.out.println("输出后缓冲器的容量位置：" + message[2]);

        System.out.println("-------------------------------------------------------");

        message = formatByteBuffer(byteBuffer);

        System.out.println("准备前缓冲器的指针：" + message[0]);
        System.out.println("准备前缓冲器的限制位置：" + message[1]);
        System.out.println("准备前缓冲器的容量位置：" + message[2]);

        byteBuffer.flip();

        byteBuffer.limit(8);
        message = formatByteBuffer(byteBuffer);

        System.out.println("准备后缓冲器的指针：" + message[0]);
        System.out.println("准备后缓冲器的限制位置：" + message[1]);
        System.out.println("准备后缓冲器的容量位置：" + message[2]);


        outFileChannel.write(byteBuffer);

        message = formatByteBuffer(byteBuffer);

        System.out.println("输出后通道的文件指针：" + outFileChannel.position());
        System.out.println("输出后缓冲器的指针：" + message[0]);
        System.out.println("输出后缓冲器的限制位置：" + message[1]);
        System.out.println("输出后缓冲器的容量位置：" + message[2]);
    }

    private static String[] formatByteBuffer(ByteBuffer byteBuffer) {
        Pattern pattern = Pattern.compile(".*\\[(.*)\\]");

        Matcher matcher = pattern.matcher(byteBuffer.toString());

        if (matcher.find()) {
            return matcher.group(1).split(" ");
        }
        return null;
    }
}
