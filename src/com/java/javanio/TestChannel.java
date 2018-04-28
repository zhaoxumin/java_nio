package com.java.javanio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestChannel {
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
             fis = new FileInputStream("/Users/zhaoxumin/java_nio/1.jpg");
             fos = new FileOutputStream("/Users/zhaoxumin/java_nio/3.jpg");
             inChannel = fis.getChannel();
             outChannel = fos.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (inChannel.read(buf) != -1) {
                //切换读取数据的模式
                buf.flip();
                //将缓冲区的数据写入通道中
                outChannel.write(buf);
                //清空缓冲区
                buf.clear();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(outChannel!=null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inChannel!=null) {
                try {
                    inChannel.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try{
                    fos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(fis!=null){
                try {
                    fis.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        }

    }

    public static void main(String []args){
        TestChannel testChannel=new TestChannel();
        testChannel.test1();
    }

}
