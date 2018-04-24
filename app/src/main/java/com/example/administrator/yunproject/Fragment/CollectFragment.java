package com.example.administrator.yunproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.yunproject.Activity.VideoDetailsActivity;
import com.example.administrator.yunproject.Bean.FindBean;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.Until.Constants;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.google.gson.JsonParser;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/29.
 */

public class CollectFragment extends RecycleBaseFragment {
    @BindView(R.id.collect_web)
    WebView collectWeb;
    Unbinder unbinder;
    @BindView(R.id.pulltorefresh)
    PullToRefreshLayout pulltorefresh;
    Unbinder unbinder1;
    private Context instance;
    private String adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=mycollect";
    private int userId;

    @Override
    protected void initView(View view) {


        instance = getActivity();
        userId = SharedPreferencesUtility.getUserId(instance);
        unbinder = ButterKnife.bind(this, view);
        webset();
    }

    private void webset() {
        WebSettings settings = collectWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        collectWeb.loadUrl(adUrl + "&uid=" + userId);

        collectWeb.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    pulltorefresh.finishRefresh();
                }
            }
        });
        collectWeb.setWebViewClient(new WebViewClient() {
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
            if (isJson(data)) {
                FindBean dataBean = GsonUtils.jsonToBean(data, FindBean.class);
                int videoId = dataBean.getId();
                Log.e("Id2222+++===", String.valueOf(videoId));
                Intent VideoDetailsIntent = new Intent(instance, VideoDetailsActivity.class);
                VideoDetailsIntent.putExtra("videoId", videoId);
                VideoDetailsIntent.putExtra("price", dataBean.getPrice());
                VideoDetailsIntent.putExtra("title", dataBean.getTitle());
                startActivity(VideoDetailsIntent);
            } else {
                Uri uri = Uri.parse(data);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            return;
        }
    }

    public boolean isJson(String content) {
        try {
            new JsonParser().parse(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!SharedPreferencesUtility.getlogin(instance)) {
            collectWeb.loadUrl("about：blank");
        } else {
            webset();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void fetchData() {

    }


    public static CollectFragment newInstance(int position) {
        CollectFragment myFragment = new CollectFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        myFragment.setArguments(b);
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initpulltorefresh() {
        pulltorefresh.setCanLoadMore(false);
        pulltorefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                if (userId != 0) {
                    webset();
                    collectWeb.loadUrl(adUrl + "&uid=" + userId);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
                        pulltorefresh.finishRefresh();


                    }
                }, 2000);
            }

            @Override
            public void loadMore() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
        unbinder1.unbind();
    }
}
