package com.jiaoyan.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws Exception{
        InetSocketAddress inetSocketAddress=new InetSocketAddress(8899);
        java.nio.channels.ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        ServerSocket serverSocket=serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(inetSocketAddress);

        ByteBuffer byteBuffer=ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel=serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int readcoun=0;

            while (-1!=readcoun){
                try {
                    readcoun=socketChannel.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }
    }
}
