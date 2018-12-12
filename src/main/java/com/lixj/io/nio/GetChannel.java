package com.lixj.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * nio类
 * @author lixj
 */
public class GetChannel {
    public static final int BASE_SIZE = 1024;

    public static void main(String[] args) throws IOException {

        FileChannel outFileChannel = new FileOutputStream("/Down By the Salley Garden.txt").getChannel();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Down by the salley gardens my love and I did meet;");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("She passed the salley gardens with little snow-white feet.");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("She bid me take love easy, as the leaves grow on the tree;");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("But I, being young and foolish, with her did not agree.");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("In a field by the river my love and I did stand,");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("And on my leaning shoulder she laid her snow-white hand.");
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("She bid me take life easy, as the grass grows on the weirs;");
        stringBuilder.append(System.lineSeparator());


        outFileChannel.write(ByteBuffer.wrap(stringBuilder.toString().getBytes()));
        outFileChannel.close();

        FileChannel fileChannel = new RandomAccessFile("/Down By the Salley Garden.txt","rw").getChannel();

        fileChannel.position(fileChannel.size());

        fileChannel.write(ByteBuffer.wrap("But I was young and foolish, and now am full of tears.".getBytes()));

        fileChannel.close();


    }
}
