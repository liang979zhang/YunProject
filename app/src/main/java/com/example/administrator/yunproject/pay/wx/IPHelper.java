//package com.example.administrator.yunproject.pay.wx;
//
//import android.util.Log;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.Inet6Address;
//import java.net.InetAddress;
//import java.net.MalformedURLException;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Enumeration;
//
///**
// * Created by Administrator on 2018/4/23.
// */
//
//public class IPHelper {
//
//
//    // MARK: - 获取内网IP地址
//    /**
//     * 获取ip地址
//     * @return
//     */
//    public static String getHostIP() {
//
//        String hostIp = null;
//        try {
//            Enumeration nis = NetworkInterface.getNetworkInterfaces();
//            InetAddress ia = null;
//            while (nis.hasMoreElements()) {
//                NetworkInterface ni = (NetworkInterface) nis.nextElement();
//                Enumeration<InetAddress> ias = ni.getInetAddresses();
//                while (ias.hasMoreElements()) {
//                    ia = ias.nextElement();
//                    if (ia instanceof Inet6Address) {
//                        continue;// skip ipv6
//                    }
//                    String ip = ia.getHostAddress();
//                    if (!"127.0.0.1".equals(ip)) {
//                        hostIp = ia.getHostAddress();
//                        break;
//                    }
//                }
//            }
//        } catch (SocketException e) {
//            Log.i("yao", "SocketException");
//            e.printStackTrace();
//        }
//
//        if (hostIp == null) {
//            hostIp = "0.0.0.0";
//        }
//
//        return hostIp;
//
//    }
//
//    // MARK: - 获取外网IP地址
//    /**
//     * 获取IP地址
//     * @return
//     */
//    public static String GetNetIp() {
//        URL infoUrl = null;
//        InputStream inStream = null;
//        String line = "";
//        try {
//            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
//            URLConnection connection = infoUrl.openConnection();
//            HttpURLConnection httpConnection = (HttpURLConnection) connection;
//            int responseCode = httpConnection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                inStream = httpConnection.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
//                StringBuilder strber = new StringBuilder();
//                while ((line = reader.readLine()) != null)
//                    strber.append(line + "\n");
//                inStream.close();
//                // 从反馈的结果中提取出IP地址
//                int start = strber.indexOf("{");
//                int end = strber.indexOf("}");
//                String json = strber.substring(start, end + 1);
//                if (json != null) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(json);
//                        line = jsonObject.optString("cip");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return line;
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return line;
//    }
//}