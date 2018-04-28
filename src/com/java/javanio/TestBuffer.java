package com.java.javanio;


import java.nio.ByteBuffer;

/**
 * buffer底层就是数组
 * 根据数据类型不同，提供了对应
 * ByteBuffer
 * CharBuffer
 * IntBuffer
 * LongBuffer
 * ShortBuffer
 * DoubleBuffer
 * FloatBuffer
 * 缓冲区存入数据put
 *
 * 获取缓冲区的数据get
 */
public class TestBuffer {


    public void test1(){
        String str="abcde";
        ByteBuffer buf=ByteBuffer.allocate(1024);
        System.out.println("-------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        buf.put(str.getBytes());
        System.out.println("-------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        //
        buf.flip();
        System.out.println("-------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte [] getStr=new byte[buf.limit()];
        buf.get(getStr);
        System.out.println("-------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        buf.rewind();
        System.out.println("-------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        buf.clear();
        System.out.println("-------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println("---------");
        System.out.println(buf.isDirect());
    }

    public void test2(){
        ByteBuffer buf2=ByteBuffer.allocateDirect(1024);
        System.out.println(buf2.isDirect());
    }


    public static void main(String []args){
        TestBuffer testBuffer=new TestBuffer();
        testBuffer.test1();
        testBuffer.test2();
    }

}
