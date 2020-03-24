package com.jiaoyan.nio;

import java.nio.ByteBuffer;

//只读buffer
//new 的对象在堆上
public class NioTest7 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());
        for(int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);
        }
        ByteBuffer readonlyBuffer=byteBuffer.asReadOnlyBuffer();
        System.out.println(readonlyBuffer.getClass());
    }

}
