package com.lixj.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio类
 * @author lixj
 */
public class GetChannel {
    public static final int BASE_SIZE = 1024;

    public static void main(String[] args) throws FileNotFoundException {
//        FileChannel fileChannel = new FileOutputStream("/getChannel").getChannel();

//        System.out.println(0x00000040);

//        System.out.println(String.format(Integer.toBinaryString(64)), );

        System.out.println(String.format("%1$'0'3s", "1"));
//        System.out.println(String.format("%4$04d %3$04d %2$04d %1$04d", "a", "b", "c", "d"));
    }
}
