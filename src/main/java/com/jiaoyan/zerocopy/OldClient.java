package com.jiaoyan.zerocopy;

import java.io.*;
import java.net.Socket;

public class OldClient {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",8899);
        String fileName="/usr/?";
        InputStream inputStream=new FileInputStream(fileName);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        byte []  byteArray=new byte[4096];
        long readcount=0;
        long total=0;

        long startTime=System.currentTimeMillis();

        while ((readcount=inputStream.read(byteArray))>=0){
            total+=readcount;
            dataOutputStream.write(byteArray);
        }

        System.out.println("发送字节： "+total+"耗时： "+(System.currentTimeMillis()-startTime));

        inputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}
