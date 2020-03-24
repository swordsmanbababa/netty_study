package com.jiaoyan.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOtest3 {
    public static void main(String[] args) throws Exception {

        FileOutputStream outputStream=new FileOutputStream("NioTest3.txt");
        FileChannel fileChannel=outputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(512);

        byte[] messgae="hello world welcome,nihao".getBytes();
        for (int i=0;i<messgae.length;++i){
            byteBuffer.put(messgae[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        outputStream.close();
    }
}
