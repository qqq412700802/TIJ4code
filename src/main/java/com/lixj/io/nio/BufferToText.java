package com.lixj.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
    public static final int BASE_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        //获取中文文件输入管道
        FileChannel inFileChannel = new FileInputStream("/abc.txt").getChannel();

        //读取内容到缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(BASE_SIZE);
        inFileChannel.read(byteBuffer);

        //文件中的数据是字符形式的，字节缓冲区无法直接将数据转换成字符。
        System.out.println(byteBuffer);
        //asCharBuffer方法将byteBuffer转化成字节缓冲区。但是直接转换打印出的内容为空，
        // 因为asCharBuffer内部是将position到Limit的空间转换的。转换之后原字节缓存区不变。
        System.out.println(byteBuffer.asCharBuffer());

        //反转缓冲区，将缓冲区设置为输出状态
        byteBuffer.flip();
        System.out.println(byteBuffer);
        //TODO
        //此时asCharBuffer方法可以将缓冲区内容转化成字符并且打印出来.但是只有将源文件编码设置为UTF-16BE的时候，才能正确读取数据，否则会乱码，原因暂时未知
        System.out.println(byteBuffer.asCharBuffer());

        //写入再读
        //获取输出文件管道
        FileChannel fileChannel = new FileOutputStream("/data.txt").getChannel();

        //向缓冲区包装字节，并将缓冲区内容写入到输出管道
        byteBuffer = ByteBuffer.allocate(BASE_SIZE);
        fileChannel.write(ByteBuffer.wrap("经柳园而下，我曾遇上我的爱".getBytes()));
        //关闭管道，此时写入的文件编码与java虚拟机编码相同，都是UTF-8
        fileChannel.close();

        //重新读取前面写入的数据,由于此时文件系统编码为UTF-8。所以读取乱码
        fileChannel = new FileInputStream("/data.txt").getChannel();
        byteBuffer.clear();
        fileChannel.read(byteBuffer);

        fileChannel.close();

        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer.asCharBuffer());

        //第一种解决乱码的方法，使用NIO包中的Charset类来获得指定编码格式的字符缓冲区,由于CharBuffer的toString方法默认是将缓冲区所有位置都打印出来，所以会有大量的空格
        System.out.println(Charset.forName(System.getProperty("file.encoding")).decode(byteBuffer));

        //第二种方法，是在写入文件的时候，设置待写入数据的字符编码格式
        fileChannel = new FileOutputStream("/data.txt").getChannel();
        fileChannel.write(ByteBuffer.wrap("经柳园而下，我曾遇上我的爱".getBytes("UTF-16BE")));

        fileChannel.close();

        fileChannel = new FileInputStream("/data.txt").getChannel();

        byteBuffer.clear();

        System.out.println(byteBuffer);
        fileChannel.read(byteBuffer);
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer.asCharBuffer());

        //直接通过CharBuffer写入,由于指针关系，写入的数据会包含所有的空格。并且由于字符编码不同，写入的文件也是乱码。但是读取出来的内容是没问题的
        fileChannel = new FileOutputStream("/data.txt").getChannel();
        byteBuffer = ByteBuffer.allocate(BASE_SIZE);
//        CharBuffer charBuffer  = byteBuffer.asCharBuffer();

        CharBuffer charBuffer = byteBuffer.asCharBuffer().put("经柳园而下");

        fileChannel.write(byteBuffer);

        fileChannel.close();

        fileChannel = new FileInputStream("/data.txt").getChannel();

        byteBuffer = ByteBuffer.allocate(BASE_SIZE);

        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        System.out.println(byteBuffer.asCharBuffer());
    }
}
