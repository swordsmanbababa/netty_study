package com.jiaoyan.nio;

import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

//buffer 的 scatterring 和 gathering
//scattering ：把来自一个channel中的数据读到多个buffer中
//gathering  :把多个buffer读到一个channel中
public class NioTeat11 {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress address=new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLenth=2+3+4;
        ByteBuffer[] buffers=new ByteBuffer[3];
        buffers[0]=ByteBuffer.allocate(2);
        buffers[1]=ByteBuffer.allocate(3);
        buffers[2]=ByteBuffer.allocate(4);

        SocketChannel socketChannel=serverSocketChannel.accept();

        while (true){
            int bytesRead=0;
            while (bytesRead<messageLenth){
                long r=socketChannel.read(buffers);
                bytesRead+=r;

                System.out.println("byteread: "+bytesRead);

                Arrays.asList(buffers).stream().map(buffer->"position "+buffer.position()+" linit: "+buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(buffer -> {
                buffer.flip();
            });

            long bytesWriten=0;
            while (bytesWriten<messageLenth){
                long r=socketChannel.write(buffers);
                bytesWriten+=r;
            }

            Arrays.asList(buffers).forEach(buffer->{
                buffer.clear();
            });
            System.out.println("bytesReadL "+bytesRead+",bytesWriten "+bytesWriten+" ,messageLength: "+messageLenth);
        }


    }
}
