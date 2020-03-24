package com.jiaoyan.netty.httpexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
//netty基于http编程
//客户端可以通过crul loaclhost:8080 、 浏览器
//查看端口命令lsof -i+端口号
//netty程序流程
//1、启动bootstrap
//2、为bootstrap关联两个事件循环组,其中boosgroup获取链接，workergruop处理链接
//3、用initializer关联相应处理器
//4、添加若干个自定义处理器


public class TestServer {
    public static void main(String[] args) throws Exception{
        //服务器端存在的死循环
        //bossGroup接受链接传递给workerGroup
        //由workerGruop处理链接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture=serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
