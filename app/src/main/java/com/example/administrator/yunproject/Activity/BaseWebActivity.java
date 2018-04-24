package com.example.administrator.yunproject.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.yunproject.Bean.LoginBean;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseWebActivity extends BaseActivity {


    @BindView(R.id.native_web)
    WebView nativeWeb;
    private BaseWebActivity context;
    private String adUrl;
    private int userId;
    private int tag;//标记跳转路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
        context = this;
        ButterKnife.bind(context);

        if (getIntent() != null) {
            tag = getIntent().getIntExtra("tag", -1);
        }

        if (tag == 101) {//我的留言
            adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=addBbs";
        } else if (tag == 102) {//要留言
            adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=bbs";
        } else if (tag == 103) {//邀请码
            adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=mycode";

        } else if (tag == 104) {//修改密码
            adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=setPassWord";
        } else if (tag == 105) {//我的订单
            adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=myorder";
        } else if (tag == 106) {
            int videoId = getIntent().getIntExtra("videoId", -1);
            if (videoId != -1) {
                adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=albumDetail" + "&albumid=" + videoId;
            } else {
                ToastShort(context, "参数错误");
            }

        }
        initView();


    }

    private void initView() {
        userId = SharedPreferencesUtility.getUserId(context);

        WebSettings settings = nativeWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        nativeWeb.loadUrl(adUrl + "&uid=" + userId);


        nativeWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (!url.contains("protocol://android")) {
//                    Uri uri = Uri.parse(url);
//                    String scheme = uri.getScheme();
//                    String host = uri.getHost();
////                  MCLog.e("telhost++===" , host);
//                    Log.e("scheme+++===" , scheme);


                    return false;
                }


                //                nativeWeb.loadUrl("javascript:getid()");
                Log.e("getid+++===", url);
                Map<String, String> map = getParamsMap(url, "protocol://android");
                String code = map.get("code");
                String data = map.get("data");
                Log.e("data+++===", data);
                parseCode(code, data);
//                Toast.makeText(instance, "请到个人页面进行登录！", Toast.LENGTH_SHORT).show();
//
                return true;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                mProgressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                mProgressDialog.hide();
            }

        });
    }


    private Map<String, String> getParamsMap(String url, String pre) {
        ArrayMap<String, String> queryStringMap = new ArrayMap<>();
        if (url.contains(pre)) {
            int index = url.indexOf(pre);
            int end = index + pre.length();
            String queryString = url.substring(end + 1);

            String[] queryStringSplit = queryString.split("&");

            String[] queryStringParam;
            for (String qs : queryStringSplit) {
                if (qs.toLowerCase().startsWith("data=")) {
                    //单独处理data项，避免data内部的&被拆分
                    int dataIndex = queryString.indexOf("data=");
                    String dataValue = queryString.substring(dataIndex + 5);
                    queryStringMap.put("data", dataValue);
                } else {
                    queryStringParam = qs.split("=");

                    String value = "";
                    if (queryStringParam.length > 1) {
                        //避免后台有时候不传值,如“key=”这种
                        value = queryStringParam[1];
                    }
                    queryStringMap.put(queryStringParam[0].toLowerCase(), value);
                }
            }
        }
        return queryStringMap;
    }


    private void parseCode(String code, String data) {
        if (code.equals("call")) {
            try {
                JSONObject json = new JSONObject(data);
                String phone = json.optString("data");
                //执行打电话的操作，具体代码省略
//                PhoneUtils.call(this, phone);
                Log.e("phone+++===", phone);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }
        if (code.equals("toast")) {

            if (tag == 103) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, data));
                ToastShort(context, "已成功复制到粘贴板！！");

                return;
            }
            LoginBean dataBean = GsonUtils.jsonToBean(data, LoginBean.class);
            int videoId = Integer.valueOf(dataBean.getUid());
            Log.e("Id2222+++===", String.valueOf(videoId));
            Intent VideoDetailsIntent = new Intent(context, VideoDetailsActivity.class);
            VideoDetailsIntent.putExtra("videoId", videoId);
            startActivity(VideoDetailsIntent);
//            try {
//            SharedPreferencesUtility.setUserId(instance,userId);
////                JSONObject jsStr = JSONObject.parseObject(requestBody); //将字符串{“id”：1}
////                JSONObject json = new JSONObject(data);
////                Log.e("json+++===" , String.valueOf(json));
////                String toast = json.optString("data");
////                Log.e("toast+++===" , toast);
////                Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            return;
        }
    }
}
