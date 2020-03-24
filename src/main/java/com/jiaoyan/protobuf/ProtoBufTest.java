package com.jiaoyan.protobuf;

public class ProtoBufTest {
    public static void main(String[] args) throws Exception{
       DateInfo.Student student= DateInfo.Student.newBuilder()
                                            .setName("吴邪")
                                            .setAge("48")
                                            .setAddress("杭州")
                                            .build();
       byte[] studentToByteArray=student.toByteArray();

       DateInfo.Student student2=DateInfo.Student.parseFrom(studentToByteArray);

        System.out.println(student2);
    }

}
