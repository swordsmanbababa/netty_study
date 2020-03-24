package com.jiaoyan.nio;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
//SLice Buffer 共享一个底层buffer
//起止  【)
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }

        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer sliceBuffer=byteBuffer.slice();

        for(int i=0;i<sliceBuffer.limit();i++){
            byte b=sliceBuffer.get(i);
            b*=2;
            byteBuffer.put(i,b);
        }

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }

    }
}
