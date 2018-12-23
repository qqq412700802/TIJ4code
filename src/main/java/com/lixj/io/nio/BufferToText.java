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
        // 因为asCharBuffer内部是将position到Limit的空间转换的。
        System.out.println(byteBuffer.asCharBuffer());

        //反转缓冲区，将缓冲区设置为输出状态
        byteBuffer.flip();
        System.out.println(byteBuffer);
        //此时asCharBuffer方法可以将缓冲区内容转化成字符并且打印出来.但是只有将源文件编码设置为UTF-16BE的时候，才能正确读取数据，否则会乱码
        System.out.println(byteBuffer.asCharBuffer());


//
        System.out.println(System.getProperty("file.encoding"));
//
//        byteBuffer.flip();
//
        System.out.println(byteBuffer.asCharBuffer());

//        System.out.println(Charset.forName(encoding).decode(byteBuffer));
        //获取输出文件管道
        FileChannel fileChannel = new FileOutputStream("/data2.txt").getChannel();

        //向缓冲区包装字节，并将缓冲区内容写入到输出管道
        byteBuffer = ByteBuffer.allocate(BASE_SIZE);
        fileChannel.write(ByteBuffer.wrap("柳园下".getBytes()));
        //关闭管道，此时写入之后，由于操作系统编码是GBK，而java运行环境编码是UTF-8,所以在文件中打开是乱码。
        fileChannel.close();

        //重新读取前面写入的数据,由于
        fileChannel = new FileInputStream("/data2.txt").getChannel();
        byteBuffer.clear();
        fileChannel.read(byteBuffer);
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer.asCharBuffer());

        byteBuffer = ByteBuffer.allocate(BASE_SIZE);

        byteBuffer.asCharBuffer().put("柳园下");
        fileChannel = new FileOutputStream("/data2.txt").getChannel();

        fileChannel.write(byteBuffer);

        fileChannel.close();
    }
}
