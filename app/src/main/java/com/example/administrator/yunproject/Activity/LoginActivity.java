package com.example.administrator.yunproject.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yunproject.Bean.LoginBean;
import com.example.administrator.yunproject.Bean.MySelfBean;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.MessageInfo;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;

/**
 * Created by Administrator on 2018/3/29.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_web)
    WebView loginWeb;
    private LoginActivity instance;
    private String adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=login";
    private int userId;
    private String username;
    private String imageHead;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instance = this;
        addActivity(instance);
        ButterKnife.bind(this);
        userId = SharedPreferencesUtility.getUserId(instance);
        initView();
    }

    private void initView() {
        WebSettings settings = loginWeb.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        //注意第二个参数JsTest，这个是JS网页调用Android方法的一个类似ID的东西
//        selfWeb.addJavascriptInterface(new JSObject(getActivity(), 0), "hdsl");
        loginWeb.clearCache(true);
        loginWeb.loadUrl(adUrl);
        loginWeb.setWebChromeClient(new WebChromeClient());
        loginWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("protocol://android")) {
                    loginWeb.loadUrl("file:///android_asset/jingtaiye.html");
                    Map<String, String> map = getParamsMap(url, "protocol://android");
                    String code = map.get("code");
                    String data = map.get("data");
                    parseCode(code, data);

                    return false;
                }
                if (url.contains("login")) {
                    loginWeb.loadUrl(url);
                    return false;
                }

//
                return false;
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

        loginWeb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && loginWeb.canGoBack()) {
                        loginWeb.goBack();
                        return true;
                    }

                }


                return false;
            }
        });
    }

    @OnClick(R.id.login_web)
    public void onViewClicked() {
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
            LoginBean dataBean = GsonUtils.jsonToBean(data, LoginBean.class);
            int userId = Integer.valueOf(dataBean.getUid());
            Log.e("userId+++===", String.valueOf(userId));
//            try {

            SharedPreferencesUtility.setUserId(instance, userId);

            getVideoData();


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


    private void getVideoData() {
        userId = SharedPreferencesUtility.getUserId(instance);
        Log.i("userId====", String.valueOf(userId));
        if (userId == 0) {

        } else {
            RetrofitHttpUtil.getApiService()
                    .getSelfData(userId)
                    .compose(this.<MySelfBean>bindToLifecycle())
                    .compose(SchedulerTransformer.<MySelfBean>transformer())
                    .doFinally(new Action() {
                        @Override
                        public void run() throws Exception {
//                        Log.i(TAG, "--- doFinally ---");
//                        if (isRefresh)
//                            mRefreshLayout.finishRefreshing();
//                        else
//                            mRefreshLayout.finishLoadmore();
                        }
                    })
                    .subscribe(new BaseObserver<MySelfBean>() {
                        @Override
                        protected void onSuccess(MySelfBean mMySelfBean) {
                            if (mMySelfBean != null) {
//                            Log.i("video_title111====", mVideoListBean.getData().get(1).getVideo_title());
//                            Log.v("mLoginRequestBean====", mVideoListBean.getResponse());
                                if (mMySelfBean.getMsg().equals("ok")) {
//                                    nameText.setVisibility(View.VISIBLE);
                                    imageHead = mMySelfBean.getData().getAvatar_big();
                                    username = mMySelfBean.getData().getUname();
//                                    Glide.with(instance).load(imageHead).into(userAvatar);  //填写头像
                                    MessageInfo messageInfo = new MessageInfo();
                                    messageInfo.setUserHead(imageHead);
                                    messageInfo.setUserName(username);
                                    EventBus.getDefault().post(messageInfo);
                                    SharedPreferencesUtility.setLogin(instance, true);
                                    finishActivity(instance);
//                                    nameText.setText(username);
                                } else {
                                    Toast.makeText(instance, mMySelfBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        protected void onFailed(HttpResponseException responseException) {
                            super.onFailed(responseException);
                            ToastShort(instance, "error code : " + responseException.getStatus());
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
