//package com.example.administrator.yunproject.pay.wx;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//
///**
// * Created by Administrator on 2018/4/23.
// */
//
//public class StreamTools {
//
//    // 把一个 inputStream 转换为一个 String
//    public static String readStream(InputStream inputStream) throws Exception {
//
//        // 定义一个内存输出流
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        int len = -1;
//        byte[] buffer = new byte[1024]; //1kb
//
//        while ((len = inputStream.read(buffer)) != -1) {
//
//            baos.write(buffer, 0, len);
//        }
//        inputStream.close();
//
//        String content = new String(baos.toByteArray());
//
//
//        // 如果服务器是以 gbk 方式编码的，则这个地方写 gbk；否则返回的是乱码
////        String content = new String(baos.toByteArray(), "gbk");
//
//        return content;
//    }
//}
