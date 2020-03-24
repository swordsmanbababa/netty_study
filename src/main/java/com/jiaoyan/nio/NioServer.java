package com.jiaoyan.nio;

import javax.net.ssl.SSLContext;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {
    private static Map<String,SocketChannel> cliemtMap=new HashMap();
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket=serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                selector.select();
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if(selectionKey.isAcceptable()){
                            ServerSocketChannel server=(ServerSocketChannel)selectionKey.channel();
                            client=server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);
                            String key="["+ UUID.randomUUID().toString()+"]";
                            cliemtMap.put(key,client);
                        }else if(selectionKey.isReadable()){
                            client=(SocketChannel)selectionKey.channel();
                            ByteBuffer readbuffer=ByteBuffer.allocate(1024);
                            int count=client.read(readbuffer);
                            if(count>0){
                                readbuffer.flip();
                                Charset charset=Charset.forName("utf-8");
                                String receivedMessage=String.valueOf(charset.decode(readbuffer).array());
                                System.out.println(client +": "+receivedMessage);

                                String sendKey=null;
                                for(Map.Entry<String,SocketChannel> entry:cliemtMap.entrySet()){
                                    if(client==entry.getValue()){
                                        sendKey=entry.getKey();
                                        break;
                                    }
                                }

                                for(Map.Entry<String,SocketChannel> entry:cliemtMap.entrySet()){
                                    SocketChannel value=entry.getValue();
                                    ByteBuffer writeBuffer=ByteBuffer.allocate(124);
                                    writeBuffer.put((sendKey+=" :"+receivedMessage).getBytes());
                                    writeBuffer.flip();
                                    value.write(writeBuffer);
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    selectionKeys.clear();
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
