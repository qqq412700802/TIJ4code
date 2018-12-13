package com.lixj.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio类
 * @author lixj
 */
public class ChannelCopy {
    public static final int BASE_SIZE = 8;

    public static void main(String[] args) throws IOException {

        FileChannel inFileChannel = new FileInputStream("/Down By the Salley Garden.txt").getChannel();
        FileChannel outFileChannel = new FileOutputStream("/copy.txt").getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(BASE_SIZE);

        while (true) {
            System.out.println("--------读取前------------");
            System.out.println(byteBuffer.position());
            System.out.println(byteBuffer.limit());
            System.out.println(inFileChannel.position());
            System.out.println("--------读取前------------");
            System.out.println(inFileChannel.read(byteBuffer));
            System.out.println(byteBuffer.toString());
            byteBuffer.flip();
            System.out.println(inFileChannel.position());
            System.out.println(byteBuffer.getChar());
            System.out.println(byteBuffer.position());
            System.out.println(byteBuffer.limit());

        }

//        while (inFileChannel.read(byteBuffer) != -1) {
//            System.out.println(byteBuffer.toString());
//        }

    }
}
