

rmi:remote method invocation  :只针对于java
远程方法调用

client:stub（桩）

server:skeleton（骨架）

序列化于反序列化：编码于解码


rpc：remote procedure call ，远程方法调用，很多rpc框架都是跨语言的

1、定义一个接口说明文件：描述了对象（结构体）、对象成员、接口方法等一系列信息
2.通过RPC框架所提供的编译器，将接口说明文件编译成具体的语言
3、在客户端与服务端分别引入RPC编译器所生成的文件。即可像调用本地方法一样调用远程的方法

编解码效率


message Person{
    required string name=1;
    required int32=2;
    optional string email=3;  //1\2\3为标识符
}



编译：



命令
protoc -h
protoc -I=$SRC_DIR   --java_out=$DST_DIR    $SRC_DIR/adressbook.proto
protoc --java_out=src/main/java src/protobuf/Student.proto

