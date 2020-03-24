package com.jiaoyan.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOCLient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);
        String fileName="/usr/?";

        FileChannel fileChannel=new FileInputStream(new File(fileName)).getChannel();
        long timeStart=System.currentTimeMillis();
        long trasfcount= fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        System.out.println("发送字节： "+trasfcount+"耗时： "+(System.currentTimeMillis()-timeStart));
    }
}
