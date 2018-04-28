package com.java.javanio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    public void test2(){
        FileChannel inchannel =null;
        FileChannel outchannel =null;
        try {
            inchannel = FileChannel.open(Paths.get("/Users/zhaoxumin/java_nio/1.jpg"), StandardOpenOption.READ);
            outchannel = FileChannel.open(Paths.get("/Users/zhaoxumin/java_nio/2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            MappedByteBuffer inMappedBuf = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, inchannel.size());
            MappedByteBuffer outMappedBuf = outchannel.map(FileChannel.MapMode.READ_WRITE, 0, inchannel.size());
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(inchannel!=null) {
                try {
                    inchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outchannel!=null) {
                try {
                    outchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String []args){
        TestChannel testChannel=new TestChannel();
        testChannel.test1();
        testChannel.test2();
    }

}
