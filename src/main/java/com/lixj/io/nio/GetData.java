package com.lixj.io.nio;


import java.nio.ByteBuffer;

public class GetData {
    public static final int BASE_SIZE = 1024;
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BASE_SIZE);

        byteBuffer.asCharBuffer().put("柳园下");


        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.getChar());
        }
    }
}
