package com.lixj.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {
    public static void main(String[] args) throws IOException {
        FileChannel inFileChannel = new FileInputStream("/柳园下.txt").getChannel();

        System.out.println(inFileChannel.size());

        FileChannel outFileChannel = new FileOutputStream("/out.txt").getChannel();

        inFileChannel.transferTo(0, inFileChannel.size(), outFileChannel);
    }
}
