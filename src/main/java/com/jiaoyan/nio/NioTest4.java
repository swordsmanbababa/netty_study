package com.jiaoyan.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream=new FileInputStream("NioTest2.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("NioTest3.txt");

        FileChannel inputChannel=fileInputStream.getChannel();
        FileChannel outputChannel=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (true){
            byteBuffer.clear();//"注释会发生什么"
            int read=inputChannel.read(byteBuffer);
            if(-1==read){
                break;
            }
            System.out.println("read "+read);
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
            inputChannel.close();
            outputChannel.close();
        }
    }
}
