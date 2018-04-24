package com.example.administrator.yunproject.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/13.
 */

public class InterlocutionActivity extends BaseActivity {
    private String TAG = InterlocutionActivity.class.getSimpleName();
    @BindView(R.id.interlocution_web)
    WebView interlocutionWeb;
    private int videoId;
    private String adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=interlocution&";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interlocution);
        ButterKnife.bind(this);
        videoId = getIntent().getIntExtra("videoId", 0);
        initView();
    }


    private void initView() {
//        interlocutionWeb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        WebSettings settings = interlocutionWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);//对H5支持
//        settings.setDomStorageEnabled(true);
        //辅助WebView处理图片上传操作
        int userId = SharedPreferencesUtility.getUserId(this);
        interlocutionWeb.loadUrl(adUrl + "albumid=" + videoId + "&uid=" + userId);
        interlocutionWeb.setWebChromeClient(new WebChromeClient());
        Log.e("tag", adUrl + "albumid=" + videoId + "uid=" + userId);


        interlocutionWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("getid+++===", url);
                if (!url.contains("protocol://android")) {
                    return false;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("tag", "aaa");
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
}
