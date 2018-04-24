package com.example.administrator.yunproject.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/29.
 */

public class QuestionsFragment extends RecycleBaseFragment {
    @BindView(R.id.question_web)
    WebView questionWeb;
    Unbinder unbinder;
    @BindView(R.id.pulltorefresh)
    PullToRefreshLayout pulltorefresh;
    Unbinder unbinder1;
    private Context instance;
    private int userId;
    private String adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=myquestion";


    @Override
    protected void initView(View view) {

        instance = getActivity();
        userId = SharedPreferencesUtility.getUserId(instance);
        unbinder = ButterKnife.bind(this, view);
        webset();
    }

    private void webset() {
        WebSettings settings = questionWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        questionWeb.loadUrl(adUrl + "&uid=" + userId);

        questionWeb.setWebChromeClient(new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);


                if (newProgress == 100) {
                    pulltorefresh.finishRefresh();
                }
            }
        });

        questionWeb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        //这里处理返回键事件
                        if (questionWeb.canGoBack()) {
                            questionWeb.goBack();
                            return true;
                        }
                    }
                }
                return false;
            }
        });
        questionWeb.setWebViewClient(new WebViewClient() {
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
//                Map<String, String> map = getParamsMap(url, "protocol://android");
//                String code = map.get("code");
//                String data = map.get("data");
//                Log.e("data+++===" , data);
//                parseCode(code, data);
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_question;
    }

    @Override
    public void fetchData() {

    }


    public static QuestionsFragment newInstance(int position) {
        QuestionsFragment myFragment = new QuestionsFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        myFragment.setArguments(b);
        return myFragment;
    }


    private void initView() {

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
                webset();
                questionWeb.loadUrl(adUrl + "&uid=" + userId);
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
    public void onResume() {
        super.onResume();
        if (!SharedPreferencesUtility.getlogin(instance)) {
            questionWeb.loadUrl("about：blank");
        } else {
            webset();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
        unbinder1.unbind();
    }
}
