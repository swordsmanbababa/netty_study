package com.jiaoyan.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile=new RandomAccessFile("NioTest3.txt","rw");

        FileChannel fileChannel=randomAccessFile.getChannel();

        //true表示共享锁  false表示排它锁
        FileLock fileLock=fileChannel.lock(3,6,true);

        System.out.println("vaild "+fileLock.isValid());
        System.out.println("lock type :"+ fileLock.isShared());
        fileLock.release();

        randomAccessFile.close();
    }
}
