package com.jiaoyan.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.RandomAccess;

//内存映射文件
public class NioTest9 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile=new RandomAccessFile("NioTest3.txt","rw");

        FileChannel fileChannel=randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0,(byte)'a');
    }
}
