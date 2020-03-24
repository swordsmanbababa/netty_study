package com.jiaoyan.netty.protobufexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class ClientTestHandler extends SimpleChannelInboundHandler<DateInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DateInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt=new Random().nextInt(3);
        DateInfo.MyMessage myMessage=null;
        if(0==randomInt){
            myMessage= DateInfo.MyMessage.newBuilder()
                    .setDataType(DateInfo.MyMessage.DataType.PersonType)
                    .setPerson(DateInfo.Person.newBuilder()
                            .setName("吴邪")
                            .setAge("48")
                            .setAddress("杭州")
                            .build())
                    .build();
        }else if (1==randomInt){
            myMessage= DateInfo.MyMessage.newBuilder()
                    .setDataType(DateInfo.MyMessage.DataType.DogType)
                    .setDog(DateInfo.Dog.newBuilder()
                            .setName("小哈")
                            .setAge("8")
                            .build())
                    .build();

        }else {
            myMessage= DateInfo.MyMessage.newBuilder()
                    .setDataType(DateInfo.MyMessage.DataType.CatType)
                    .setCat(DateInfo.Cat.newBuilder()
                            .setName("小喵")
                            .setCity("8")
                            .build())
                    .build();


        }


        ctx.channel().writeAndFlush(myMessage);
    }
}
