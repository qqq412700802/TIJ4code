package com.lixj.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * nio类
 * @author lixj
 */
public class ChannelCopy {
    public static final int BASE_SIZE = 1024;

    public static void main(String[] args) throws IOException {

        FileChannel inFileChannel = new FileInputStream("/Down By the Salley Garden.txt").getChannel();
        FileChannel outFileChannel = new FileOutputStream("/copy.txt").getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(BASE_SIZE);

        inFileChannel.read(byteBuffer);

        System.out.println(byteBuffer);
        byteBuffer.rewind();

        System.out.println(byteBuffer);

        System.out.println(Charset.forName("GBK").decode(byteBuffer));

        System.out.println(byteBuffer);


    }
}
