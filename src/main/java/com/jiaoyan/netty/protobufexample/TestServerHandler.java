package com.jiaoyan.netty.protobufexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler  extends SimpleChannelInboundHandler<DateInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DateInfo.MyMessage msg) throws Exception {

        DateInfo.MyMessage.DataType dataType=msg.getDataType();
        if(dataType== DateInfo.MyMessage.DataType.PersonType){
            DateInfo.Person person=msg.getPerson();

            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        }else if(dataType== DateInfo.MyMessage.DataType.DogType){
            DateInfo.Dog dog=msg.getDog();

            System.out.println(dog.getName());
            System.out.println(dog.getAge());
        }else {
            DateInfo.Cat cat=msg.getCat();

            System.out.println(cat.getName());
            System.out.println(cat.getCity());
        }

    }
}
