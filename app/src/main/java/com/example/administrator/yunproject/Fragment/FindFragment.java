package com.example.administrator.yunproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yunproject.Activity.VideoDetailsActivity;
import com.example.administrator.yunproject.Bean.FindBean;
import com.example.administrator.yunproject.Bean.LoginBean;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.google.gson.JsonParser;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.mchsdk.paysdk.mylibrary.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/27.
 */

public class FindFragment extends BaseFragment {
    public static final String FRAD_TITLE = "frag_title";
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_tilte)
    TextView tvTilte;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.titleBar)
    AppBarLayout titleBar;
    Unbinder unbinder;
    @BindView(R.id.native_web)
    WebView nativeWeb;
    @BindView(R.id.pulltorefresh)
    PullToRefreshLayout pulltorefresh;
    private Context instance;
    private String adUrl = ApiService.BASE_URL;
//    private String adUrl = "http://192.168.252.111:89/";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View friendView = inflater.inflate(R.layout.fragment_find, container, false);
        unbinder = ButterKnife.bind(this, friendView);
        instance = getActivity();
        initView();
        initpulltorefresh();
        return friendView;
    }

    public static FindFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        FindFragment fragment = new FindFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView() {
        pulltorefresh.setCanLoadMore(false);
//        ivRight.setVisibility(View.VISIBLE);
//        ivRight.setImageResource(R.drawable.head_search);
        tvTilte.setVisibility(View.VISIBLE);
        tvTilte.setText("发现");
        WebSettings settings = nativeWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        nativeWeb.loadUrl(adUrl);
        nativeWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    pulltorefresh.finishRefresh();
                }
            }
        });


        nativeWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (!url.contains("protocol://android")) {
                    return false;
                }


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

        nativeWeb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //这里处理返回键事件
                        if (nativeWeb.canGoBack()) {
                            nativeWeb.goBack();
//                            Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    }
                }
                return false;
            }
        });

    }

    private void initpulltorefresh() {

        pulltorefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                initView();
                nativeWeb.loadUrl(adUrl);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
//                        pulltorefresh.finishRefresh();


                    }
                }, 2000);
            }

            @Override
            public void loadMore() {

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_left, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                break;
        }
    }


}
