package com.jiaoyan.nio;

import io.netty.buffer.ByteBuf;
import jdk.nashorn.internal.runtime.ECMAException;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream=new FileInputStream("NioTest2.txt");
        FileChannel fileChannel=fileInputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            byte b=byteBuffer.get();
            System.out.println("character:  "+(char)b);

        }

        fileInputStream.close();
    }
}
