package com.jiaoyan.netty.chatexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //保存服务器与客户端之间一个一个的channel对象
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel=ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel!=ch){
                ch.writeAndFlush(channel.remoteAddress()+" 发生的信息： "+msg+"\n");
            }else {
                ch.writeAndFlush("[自己] "+msg+"\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    //表示服务器与客户端已经建立链接
    //客户端与服务器每建立起一个链接，表示一个channel已经建立好了
    //channelhandlercontext可以获取channel对象
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        //向channelgroup里的每一个channel对象写数据
        channelGroup.writeAndFlush("[服务器] - "+channel.remoteAddress()+" 加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
         Channel channel=ctx.channel();
         channelGroup.writeAndFlush("[服务器] - "+channel.remoteAddress()+" 退出\n");
         channelGroup.remove(channel);//此行代码可省略，netty会自动的寻找并移除
         System.out.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        System.out.println(channel.remoteAddress()+" 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        System.out.println(channel.remoteAddress()+" 下线");
    }
}
