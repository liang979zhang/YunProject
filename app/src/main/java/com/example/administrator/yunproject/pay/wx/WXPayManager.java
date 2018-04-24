//package com.example.administrator.yunproject.pay.wx;
//
//import android.app.Activity;
//import android.util.Xml;
//import android.widget.Toast;
//
//import com.tencent.mm.opensdk.constants.Build;
//import com.tencent.mm.opensdk.modelpay.PayReq;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//import org.xmlpull.v1.XmlPullParser;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.ConnectException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Random;
//import java.util.Set;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
///**
// * Created by Administrator on 2018/4/23.
// */
//
//public class WXPayManager {
//
//    // MARK: - 微信参数配置
//    public static String API_KEY = ""; // 商户秘钥
//    public static String MCH_ID = "";  // 商户id
//
//    public static String APPID = "";
//
//    // 微信支付成功后向咱们后台的，回调地址
//    public static String WXNotify_url = "";
//
//
//
//
//    // MARK: - 随机字符串生成
//    public static String getRandomString(int length) {
//        //length表示生成字符串的长度
//        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        Random random = new Random();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int number = random.nextInt(base.length());
//            sb.append(base.charAt(number));
//        }
//        return sb.toString();
//    }
//
//
//    // MARK: - 请求xml组装
//    public static String getRequestXml(SortedMap<String,Object> parameters){
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("<xml>");
//        Set es = parameters.entrySet();
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String key = (String)entry.getKey();
//            String value = (String)entry.getValue();
//            if ("attach".equalsIgnoreCase(key)||"body".equalsIgnoreCase(key)||"sign".equalsIgnoreCase(key)) {
//                sb.append("<"+key+">"+"<![CDATA["+value+"]]></"+key+">");
//            }else {
//                sb.append("<"+key+">"+value+"</"+key+">");
//            }
//        }
//        sb.append("</xml>");
//        return sb.toString();
//    }
//
//
//    // MARK: - 生成签名
//    public static String createSign(String characterEncoding,SortedMap<String,Object> parameters){
//        StringBuffer sb = new StringBuffer();
//
//        //所有参与传参的参数按照accsii排序（升序）
//        Set es = parameters.entrySet();
//
//        Iterator it = es.iterator();
//        while(it.hasNext()) {
//            Map.Entry entry = (Map.Entry)it.next();
//            String k = (String)entry.getKey();
//            Object v = entry.getValue();
//            if(null != v && !"".equals(v)
//                    && !"sign".equals(k) && !"key".equals(k)) {
//                sb.append(k + "=" + v + "&");
//            }
//        }
//        sb.append("key=" + API_KEY);
////        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
//        return "";
//    }
//
//
//    //请求方法
//    private static String httpsRequest(Activity activity, String requestUrl, String requestMethod, String outputStr) {
//        try {
//
//            URL url = new URL(requestUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            // 设置请求方式（GET/POST）
//            conn.setRequestMethod(requestMethod);
//
//            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//            // 当outputStr不为null时向输出流写数据
//            if (null != outputStr) {
//                OutputStream outputStream = conn.getOutputStream();
//                // 注意编码格式
//                outputStream.write(outputStr.getBytes("UTF-8"));
//                outputStream.close();
//            }
//
//
//            // 从输入流读取返回内容
//            InputStream inputStream = conn.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            String str = null;
//            StringBuffer buffer = new StringBuffer();
//            while ((str = bufferedReader.readLine()) != null) {
//                buffer.append(str);
//            }
//            // 释放资源
//            bufferedReader.close();
//            inputStreamReader.close();
//            inputStream.close();
//            inputStream = null;
//            conn.disconnect();
//            return buffer.toString();
//        } catch (ConnectException ce) {
//
//            System.out.println("连接超时：{}"+ ce);
//            Toast.makeText(activity, "连接超时", Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//            System.out.println("https请求异常：{}"+ e);
//            Toast.makeText(activity, "网络异常", Toast.LENGTH_SHORT).show();
//        }
//        return null;
//    }
//
//
//    // MARK: - 请求微信接口，生成微信后台的预支付订单
//    // 官方：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
//    public static void generatePrepaymentOrder(final Activity activity,
//                                               final String appid,
//                                               String body,
//                                               final String mch_id,
//                                               String nonce_str,
//                                               String wxnotify_url,
//                                               String out_trade_no,
//                                               String spbill_create_ip,
//                                               String total_fee,
//                                               String trade_type) {
//
//
//
//        final SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
//
//        parameterMap.put("appid", WXPayManager.APPID);
//        parameterMap.put("mch_id", WXPayManager.MCH_ID);
//        parameterMap.put("nonce_str", nonce_str);
//        parameterMap.put("body", body);
//        parameterMap.put("out_trade_no", out_trade_no);
//        parameterMap.put("total_fee", total_fee);
//        parameterMap.put("spbill_create_ip", spbill_create_ip);
//        parameterMap.put("notify_url", WXPayManager.WXNotify_url);
//        parameterMap.put("trade_type", trade_type);
//
//
//        final String sign = WXPayManager.createSign("UTF-8", parameterMap);
//        System.out.println("sign==" + sign);
//
//        parameterMap.put("sign", sign);
//
//        final String requestXML = WXPayManager.getRequestXml(parameterMap);
//        System.out.println("requestXML==" + requestXML);
//
//        new Thread(){
//
//            @Override
//            public void run() {
//                super.run();
//
//
//                String result = httpsRequest(activity,
//                        "https://api.mch.weixin.qq.com/pay/unifiedorder", "POST",
//                        requestXML);
//                System.out.println("result==" + result);
//
//
//                try {
//                    WXPrepareModel prepareModel = WXPayManager.doXMLParse(result);
//                    System.out.println("prepareModel==" + prepareModel.toString());
//
//
//                    if (prepareModel.getResult_code().equals("SUCCESS") &&
//                            prepareModel.getReturn_code().equals("SUCCESS")) {
//
//
//                        // 构造参数列表
//                        String packageStr = "Sign=WXPay";
//                        String timestamp = "" + System.currentTimeMillis() / 1000;
//
//
//                        SortedMap<String, Object> parameters = new TreeMap<String, Object>();
//                        parameters.put("appid", prepareModel.getAppid());
//                        parameters.put("partnerid", prepareModel.getMch_id());
//                        parameters.put("prepayid", prepareModel.getPrepay_id());
//                        parameters.put("noncestr", prepareModel.getNonce_str());
//                        parameters.put("package", packageStr);
//                        parameters.put("timestamp", timestamp);
//
//
//                        String signStr = createSign("UTF-8", parameters);
//                        System.out.println("signStr==" + signStr);
//
//
//                        toPay(activity,
//                                prepareModel.getAppid(),
//                                prepareModel.getMch_id(),
//                                prepareModel.getPrepay_id(),
//                                prepareModel.getNonce_str(),
//                                timestamp,
//                                packageStr,
//                                signStr);
//
//                    } else {
//
//                        System.out.println("生成微信后台预支付订单id失败");
//                        System.out.println("prepareModel.getReturn_msg==" + prepareModel.getReturn_msg());
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("生成微信后台预支付订单id请求结果的 xml解析失败");
//                }
//
//
//            }
//        }.start();
//    }
//
//
//    // MARK: - 解析 xml
//    public static WXPrepareModel doXMLParse(String strxml) throws Exception {
//
//        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
//
//        if(null == strxml || "".equals(strxml)) {
//            return null;
//        }
//
////        Map m = new HashMap();
//
//
//
//        InputStream inputStream = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
//
//
//
//        XmlPullParser pullParser = Xml.newPullParser();
//
//        // 设置 参数
//        pullParser.setInput(inputStream, "utf-8");
//
//        // 获取事件类型
//        int type = pullParser.getEventType();
//
//
//        WXPrepareModel resultModel = null;
//
//
//        while (type != XmlPullParser.END_DOCUMENT) {
//
//
//            // 正式解析
//            switch (type) {
//
//                case XmlPullParser.START_TAG: {
//                    // 解析开始标签
//
//                    // 具体判断解析到的是哪个标签
//                    if ("xml".equals(pullParser.getName())) {
//
//                        resultModel = new WXPrepareModel();
//
//                    } else if ("return_code".equals(pullParser.getName())) {
//
//                        String return_code = pullParser.nextText();
//                        System.out.println("return_code==" + return_code);
//
//                        resultModel.setReturn_code(return_code);
//
//                    } else if ("return_msg".equals(pullParser.getName())) {
//
//                        String return_msg = pullParser.nextText();
//                        resultModel.setReturn_msg(return_msg);
//
//                    } else if ("appid".equals(pullParser.getName())) {
//
//                        String appid = pullParser.nextText();
//                        resultModel.setAppid(appid);
//
//                    } else if ("mch_id".equals(pullParser.getName())) {
//
//                        String mch_id = pullParser.nextText();
//                        resultModel.setMch_id(mch_id);
//
//                    } else if ("nonce_str".equals(pullParser.getName())) {
//
//                        String nonce_str = pullParser.nextText();
//                        resultModel.setNonce_str(nonce_str);
//
//                    } else if ("sign".equals(pullParser.getName())) {
//
//                        String sign = pullParser.nextText();
//                        resultModel.setSign(sign);
//
//                    } else if ("result_code".equals(pullParser.getName())) {
//
//                        String result_code = pullParser.nextText();
//                        resultModel.setResult_code(result_code);
//                    } else if ("prepay_id".equals(pullParser.getName())) {
//
//                        String prepay_id = pullParser.nextText();
//                        resultModel.setPrepay_id(prepay_id);
//                    } else if ("trade_type".equals(pullParser.getName())) {
//
//                        String trade_type = pullParser.nextText();
//                        resultModel.setTrade_type(trade_type);
//                    }
//
//
//
//                    break;
//                }
//
//                case XmlPullParser.END_TAG: {
//                    // 解析结束标签
//
//                    if ("xml".equals(pullParser.getName())) {
//
//                        // 把对象放到集合中
////                    weatherList.add(channelModel);
//                    }
//                    break;
//                }
//
//            }
//
//
//            // 不停的向下解析
//            type = pullParser.next();
//
//        }
//
//
//
//        //关闭流
//        inputStream.close();
//
//        return resultModel;
//    }
//
//    // MARK: - 判断手机是否安装微信，微信版本是否支持支付
//    public static Boolean chechWXCanPay(Activity activity) {
//
//        IWXAPI api = WXAPIFactory.createWXAPI(activity, WXPayManager.APPID, false);
//
//        Boolean isCanPay = true;
//        if (!api.isWXAppInstalled()) {
//            Toast.makeText(activity, "您的手机没有安装微信", Toast.LENGTH_SHORT).show();
//            isCanPay = false;
//        } else {
//
//            boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
//
//            if (!isPaySupported) {
////            Toast.makeText(activity, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
//                Toast.makeText(activity, "您的微信版本过低，不支持支付", Toast.LENGTH_SHORT).show();
//                isCanPay = false;
//            }
//        }
//
//
//        return isCanPay;
//    }
//
//
//    // MARK: - 进入微信客服端去支付
//    public static void toPay(final Activity activity,
//                             final String appId,
//                             final String mch_id,
//                             final String prepayid,
//                             final String noncestr,
//                             final String timestamp,
//                             final String packageValue,
//                             final String sign) {
//
//        // 在主线程中执行
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                // IWXAPI 是第三方app和微信通信的openapi接口
//                // 通过WXAPIFactory工厂，获取IWXAPI的实例
//                IWXAPI api = WXAPIFactory.createWXAPI(activity, WXPayManager.APPID, false);
//
//                // 将该app注册到微信
//                api.registerApp(WXPayManager.APPID);
//
//
//                System.out.println("appId==" + appId);
//                System.out.println("mch_id==" + mch_id);
//                System.out.println("prepayid==" + prepayid);
//                System.out.println("noncestr==" + noncestr);
//                System.out.println("timestamp==" + timestamp);
//                System.out.println("packageValue==" + packageValue);
//                System.out.println("sign==" + sign);
//
//
//                PayReq req = new PayReq();
//
//                req.appId			= appId;
//                req.partnerId		= mch_id;
//                req.prepayId		= prepayid;
//                req.nonceStr		= noncestr;
//                req.timeStamp		= timestamp;
//                req.packageValue	= packageValue;
//                req.sign			= sign;
//
//
//                Toast.makeText(activity, "正常调起支付", Toast.LENGTH_SHORT).show();
//                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//                api.sendReq(req);
//            }
//        });
//
//    }
//
//}