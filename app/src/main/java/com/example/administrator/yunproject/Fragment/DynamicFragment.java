package com.example.administrator.yunproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.util.ArrayMap;
import android.util.Log;
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
import com.example.administrator.yunproject.Bean.LoginBean;
import com.example.administrator.yunproject.Bean.LoginRequestBean;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.mchsdk.paysdk.mylibrary.BaseFragment;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.retrofit.RxUtil;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Action;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/27.
 */

public class DynamicFragment extends BaseFragment {
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
    @BindView(R.id.native_web)
    WebView nativeWeb;
    Unbinder unbinder;
    @BindView(R.id.pulltorefresh)
    PullToRefreshLayout pulltorefresh;

    private Context instance;
    private String adUrl = ApiService.BASE_URL + "index.php?app=classroom&mod=My&act=message";
    private int userId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View friendView = inflater.inflate(R.layout.fragment_dynamic, container, false);
        unbinder = ButterKnife.bind(this, friendView);
        instance = getActivity();
        initView();
        initpulltorefresh();
        return friendView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!SharedPreferencesUtility.getlogin(instance)) {
            nativeWeb.loadUrl("about：blank");
        } else {
//            nativeWeb.loadUrl(adUrl + "&uid=" + userId);
            initView();
        }
    }

    private void initpulltorefresh() {
        pulltorefresh.setCanLoadMore(false);
        pulltorefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                initView();
                nativeWeb.loadUrl(adUrl + "&uid=" + userId);
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

    public static DynamicFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView() {
        userId = SharedPreferencesUtility.getUserId(instance);

        ivRight.setVisibility(View.VISIBLE);
//        ivRight.setImageResource(R.drawable.head_search);
        tvTilte.setVisibility(View.VISIBLE);
        tvTilte.setText("动态");

        WebSettings settings = nativeWeb.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        nativeWeb.loadUrl(adUrl + "&uid=" + userId);


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
            LoginBean dataBean = GsonUtils.jsonToBean(data, LoginBean.class);
            int videoId = Integer.valueOf(dataBean.getUid());
            Log.e("Id2222+++===", String.valueOf(videoId));
            Intent VideoDetailsIntent = new Intent(instance, VideoDetailsActivity.class);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getData() {
        String secretKey = "2d6210e8e4c8a4cd";  //秘钥
        String requestCheck = "";
//        EncryptUtils.encryptMD5ToString(2 + secretKey).toLowerCase();  //店铺id+秘钥"的 MD5加密
        Map parama = new HashMap();
        parama.put("id", "");    //店铺ID, 登录之后会获得
        parama.put("cateId", "");   //类目id 男装，大衣，风衣，女装
        parama.put("state", 0);   //0在售 1下架 2审核中
        parama.put("range", 1);   //分页
        parama.put("requestCheck", requestCheck);   //"店铺id+秘钥"的 MD5加密
        RequestBody body = RxUtil.getMapBody(parama);
        RetrofitHttpUtil.getApiService()
                .getLoginData(body)
                .compose(this.<LoginRequestBean>bindToLifecycle())
                .compose(SchedulerTransformer.<LoginRequestBean>transformer())
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
                .subscribe(new BaseObserver<LoginRequestBean>() {
                    @Override
                    protected void onSuccess(LoginRequestBean mLoginRequestBean) {
                        if (mLoginRequestBean != null) {
//                            Log.v("mLoginRequestBean====", mLoginRequestBean.getResponse());
//                            if (mLoginRequestBean.getResponse().equals("success")) {
//                                Toast.makeText(instance, "登录成功！", Toast.LENGTH_SHORT).show();
//
//                            }else if (mLoginRequestBean.getResponse().equals("error")){
//                                Toast.makeText(instance, mLoginRequestBean.getData().getMsg(), Toast.LENGTH_SHORT).show();
//                            }
                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
                        ToastShort(instance, "error code : " + responseException.getStatus());
                    }
                });
    }


    @OnClick({R.id.iv_left, R.id.tv_left, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                break;
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                break;
        }
    }
}
