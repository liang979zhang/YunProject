package com.example.administrator.yunproject.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.administrator.yunproject.BaseAc;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.Until.Constants;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/14.
 */

public class DiscussActivity extends BaseAc {
    @BindView(R.id.discuss_web)
    WebView discussWeb;
    private String adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=getAlbumsReview";
    private int videoId;
    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        ButterKnife.bind(this);
        videoId = getIntent().getIntExtra("videoId", 0);
        userId = SharedPreferencesUtility.getUserId(this);
        initView();
    }


    private void initView() {
        discussWeb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        WebSettings settings = discussWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        settings.setSupportZoom(true);
//        settings.setDomStorageEnabled(true);
        //辅助WebView处理图片上传操作
//        nativeWeb.setWebChromeClient(new MyChromeWebClient());
        discussWeb.loadUrl(adUrl + "&albumid=" + videoId + "&uid=" + userId);


        discussWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("getid+++===", url);
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
//                Map<String, String> map = getParamsMap(url, "protocol://android");
//                String code = map.get("code");
//                String data = map.get("data");
//                Log.e("data+++===" , data);
//                parseCode(code, data);
//                Toast.makeText(instance, "请到个人页面进行登录！", Toast.LENGTH_SHORT).show();
//
//                return true;
                return super.shouldOverrideUrlLoading(view, url);
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

    @Override
    public void errnet() {
        Toast.makeText(DiscussActivity.this, "网络断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void sucnet() {

    }
}
