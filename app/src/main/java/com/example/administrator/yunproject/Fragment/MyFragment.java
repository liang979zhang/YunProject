package com.example.administrator.yunproject.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yunproject.Activity.BaseWebActivity;
import com.example.administrator.yunproject.Activity.HelpActivity;
import com.example.administrator.yunproject.Activity.LoginActivity;
import com.example.administrator.yunproject.Activity.SetActivity;
import com.example.administrator.yunproject.Adapter.BaseFragmentAdapter;
import com.example.administrator.yunproject.Adapter.ChoicePagerAdapter;
import com.example.administrator.yunproject.Bean.CollectData;
import com.example.administrator.yunproject.Bean.HeadImageBean;
import com.example.administrator.yunproject.Bean.ImageBean;
import com.example.administrator.yunproject.Bean.MySelfBean;
import com.example.administrator.yunproject.Bean.VIdeoPalyerBean;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.ApiService;
import com.example.administrator.yunproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.administrator.yunproject.Until.GlideImageLoader;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.ImageOrViderFile;
import com.example.administrator.yunproject.Until.MessageInfo;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.mchsdk.paysdk.mylibrary.BaseFragment;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.retrofit.RxUtil;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Action;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.administrator.yunproject.Until.Constants.TAG;
import static java.lang.String.valueOf;

/**
 * Created by Administrator on 2018/3/27.
 */

public class MyFragment extends BaseFragment {
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
    @BindView(R.id.user_avatar)
    CircularImageView userAvatar;
    @BindView(R.id.self_headGround)
    RelativeLayout selfHeadGround;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.name)
    TextView nameText;
    private String imageHead;
    private String username;
    private static final int REQUEST_CALL_PHONE2 = 7;
    //    @BindView(R.id.self_web)
//    WebView selfWeb;

    String[] mTitles = new String[]{
            "学习", "缓存", "收藏", "问答"
    };
    List<Fragment> mFragments;
    private Context instance;
    private int userId;
    private static final int IMAGE_PICKER = 300;
    private String headImage;
    private AlertDialog dlg;


    private MyHandler myHandler;
    private ImageBean imageBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View friendView = inflater.inflate(R.layout.fragment_self, container, false);
        unbinder = ButterKnife.bind(this, friendView);
        instance = getActivity();
        userId = SharedPreferencesUtility.getUserId(instance);
        EventBus.getDefault().register(this);
        initView();
        initWight();
        return friendView;
    }


    private void initWight() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        imagePicker.setMultiMode(false);   //允许剪切
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragments = new ArrayList<>();
        mFragments.add(StudyFragment.newInstance(0));
        mFragments.add(CacheFragment.newInstance(1));
        mFragments.add(CollectFragment.newInstance(2));
        mFragments.add(QuestionsFragment.newInstance(3));
        viewpager.setOffscreenPageLimit(4);
        // 第二步：为ViewPager设置适配器
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(), mFragments, mTitles);
        viewpager.setAdapter(adapter);


//                tabs.post(new Runnable() {   //设置tabLayout
//            @Override
//            public void run() {
//                setIndicator(tabs, 50, 50);
//            }
//        });

        //  第三步：将ViewPager与TableLayout 绑定在一起
        tabs.setupWithViewPager(viewpager);
//        for (int i = 0; i < tabs.getTabCount(); i++) {
//            tabs.getTabAt(i).setCustomView(adapter.getTabView(i));
//        }

        //4. 设置监听TabSelectedListener事件，来动态的更新自定义Tab的显示状态
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //TODO 选中当前的Tab触发此方法
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //TODO 取消上次选中的tab会触发此方法
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见
            Log.e("tag", "setUserVisibleHint1");
            userId = SharedPreferencesUtility.getUserId(instance);
            if (userId == 0) {
                Intent loginIntent = new Intent(instance, LoginActivity.class);
                startActivity(loginIntent);
            }
        } else {
            //不可见
            Log.e("tag", "setUserVisibleHint2");



        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("tag", "fragment");
        if (!SharedPreferencesUtility.getlogin(instance)) {
            Glide.with(instance).load("")
                    .placeholder(R.drawable.head_image)
                    .into(userAvatar);  //填写头像
            nameText.setText("");
            userId = 0;
        }


    }

    public static MyFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(FRAD_TITLE, title);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    private void initView() {
        myHandler = new MyHandler();
//        ivRight.setVisibility(View.VISIBLE);
//        ivRight.setImageResource(R.drawable.head_search);
        tvRight.setVisibility(View.VISIBLE);
        tvTilte.setVisibility(View.VISIBLE);
        tvTilte.setText("我的");
        if (SharedPreferencesUtility.getlogin(instance)) {
            getVideoData();
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        nameText.setVisibility(View.VISIBLE);
        Glide.with(instance).load(messageInfo.getUserHead()).into(userAvatar);  //填写头像
        nameText.setText(messageInfo.getUserName());

//        if (messageInfo.isKey()){
//            initView();
//        }
    }


    /**
     * ViewPager Switch Fragment Listener
     * 实现滑动
     */
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // bottomNavigation.setCurrentItem(position);
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 3) {
//                ((MainActivity)getActivity()).getChoice();
//                ll_interactive_filter_search.setVisibility(View.GONE);
            } else {
//                ((MainActivity)getActivity()).getChoiceSlid();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_left, R.id.rl_back, R.id.user_avatar, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                break;
            case R.id.user_avatar:   //点击头像登录

//                String img = "/data/data/com.example.administrator.yunproject/cache/ImagePicker/cropTemp/IMG_20180416_164334.jpg";
//                LoadHeadImage(img);

                userId = SharedPreferencesUtility.getUserId(instance);
                if (userId == 0) {
                    Intent loginIntent = new Intent(instance, LoginActivity.class);
                    startActivity(loginIntent);
                } else {   //修改头像
                    if (dlg == null) {
                        dlg = new AlertDialog.Builder(instance).create();
                    }
                    dlg.show();
                    WindowManager m = getActivity().getWindowManager();
                    Display d = m.getDefaultDisplay();  //为获取屏幕宽、高

                    Window window = dlg.getWindow();
                    android.view.WindowManager.LayoutParams p = window.getAttributes();
                    p.width = (int) ((d.getWidth()) * 0.8);   //高度设置为屏幕的0.3
                    p.height = (int) ((d.getHeight()) * 0.55);
                    dlg.getWindow().setAttributes(p);     //设置生效

                    // *** 主要就是在这里实现这种效果的.
                    // 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
                    window.setContentView(R.layout.show_dialog);

                    intdiagview(window);

                }

                break;
            case R.id.tv_right:
                Intent intent = new Intent(instance, SetActivity.class);
                startActivity(intent);

                break;
        }
    }

    private void intdiagview(Window window) {


        TextView tv_ed_tx = window.findViewById(R.id.tv_ed_tx);
        TextView tv_yq = window.findViewById(R.id.tv_yq);
        TextView tv_xg = window.findViewById(R.id.tv_xg);
        TextView tv_dd = window.findViewById(R.id.tv_dd);

        tv_ed_tx.setOnClickListener(new MyDialogListener());
        tv_yq.setOnClickListener(new MyDialogListener());
        tv_xg.setOnClickListener(new MyDialogListener());
        tv_dd.setOnClickListener(new MyDialogListener());

    }


    class MyDialogListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (dlg != null) {
                dlg.dismiss();
            }

            switch (view.getId()) {
                case R.id.tv_ed_tx:
                    getImageLayout();
                    break;
                case R.id.tv_yq:
                    Intent intent = new Intent(instance, BaseWebActivity.class);
                    intent.putExtra("tag", 103);
                    startActivity(intent);
                    break;
                case R.id.tv_xg:
                    Intent intentxg = new Intent(instance, BaseWebActivity.class);
                    intentxg.putExtra("tag", 104);
                    startActivity(intentxg);
                    break;
                case R.id.tv_dd:
                    Intent intentd = new Intent(instance, BaseWebActivity.class);
                    intentd.putExtra("tag", 105);
                    startActivity(intentd);
                    break;

            }
        }
    }


    private void getImageLayout() {
        if (ContextCompat.checkSelfPermission(instance, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CALL_PHONE2);
        } else {

            if (dlg != null) {
                dlg.dismiss();
            }
            Intent intent = new Intent(instance, ImageGridActivity.class);
            startActivityForResult(intent, IMAGE_PICKER);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

                for (ImageItem datae : images) {
                    headImage = datae.path;
                    Log.i(TAG, "images1111=====" + datae.path);
//                    setLoadImage(headImage,41);

                }
                setLoadImage2(headImage, 41);



//                LoadHeadImage(headImage);

            } else {
                Toast.makeText(instance, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setLoadImage2(String headImage, int i) {
        String url = ApiService.BASE_URL + "index.php?app=api&mod=User&act=upload_face";
        Map<String, String> map = new HashMap<>();
        map.put("uid", String.valueOf(i));
        File file = new File(headImage);
        postFile(url, map, file, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("tag", response.body().string().toString());
                Message message = new Message();
                message.what = 101;
                message.obj = response.body().string().toString();
                myHandler.sendMessage(message);

            }
        });
    }

    class MyHandler extends Handler {
        public MyHandler() {
        }

        public MyHandler(Looper looper) {
            super(looper);
        }


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 101:
                    Gson gson = new Gson();
                    String json = (String) msg.obj;
                    imageBean = gson.fromJson(json, ImageBean.class);
                    Glide.with(instance).load(imageBean.getData().getBig()).into(userAvatar);
                    ToastShort(instance, "上传成功");
                    break;
            }


        }
    }


    public static void postFile(final String url, final Map<String, String> map, File file, Callback callback) {

        OkHttpClient client = new OkHttpClient();

        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            okhttp3.RequestBody body = okhttp3.RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("avata", filename, body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry entry : entries) {
                String key = valueOf(entry.getKey());
                String value = valueOf(entry.getValue());
                Log.d("HttpUtils", "key==" + key + "value==" + value);
                requestBody.addFormDataPart(key, value);
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(callback);

    }


    //    /**
//     * 上传头像
//     * .compose(this.<HeadImageBean>bindToLifecycle())
//     */
    private void LoadHeadImage(String headImage) {
        Log.e("tag", "LoadHeadImage");

        Map<String, RequestBody> params = new HashMap<>();
        //以下参数是伪代码，参数需要换成自己服务器支持的
//        params.put("uid", convertToRequestBody("title"));
        List<File> fileList = new ArrayList<>();

        fileList.add(new File(headImage));
        userId = 22;
        RetrofitHttpUtil.getApiService()
//        ImageOrViderFile.uploadFile("file", headImage)
                .upLoadHeadImage(ImageOrViderFile.uploadFile("file", headImage))
//                .upLoadHeadImage(params,fileList)
                .compose(this.<CollectData>bindToLifecycle())
                .compose(SchedulerTransformer.<CollectData>transformer())
                .subscribe(new BaseObserver<CollectData>() {
                    @Override
                    protected void onSuccess(CollectData mHeadImageBean) {
                        if (mHeadImageBean != null) {
//                            ToastShort(instance, "头像更改成功！");
//                            Log.i("asadfs", "getOriginalUrl111====" + mHeadImageBean.getData().getSmall());    //原图地址
//                            Glide.with(instance).load(mHeadImageBean.getData().getSmall()).into(userAvatar);
//
                        }
                    }

                    @Override
                    protected void onFailed(HttpResponseException responseException) {
                        super.onFailed(responseException);
                        ToastShort(instance, "error code : " + responseException.getStatus());
                    }
                });
    }


    /**
     * 设置tablayout下滑线长度
     *
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }


    private void getVideoData() {
        userId = SharedPreferencesUtility.getUserId(instance);
        Log.i("asfdasfdasdf", String.valueOf(userId));
        if (userId == 0) {

        } else {
            RetrofitHttpUtil.getApiService()
                    .getSelfData(userId)
                    .compose(this.<MySelfBean>bindToLifecycle())
                    .compose(SchedulerTransformer.<MySelfBean>transformer())
                    .doFinally(new Action() {
                        @Override
                        public void run() throws Exception {
                        }
                    })
                    .subscribe(new BaseObserver<MySelfBean>() {
                        @Override
                        protected void onSuccess(MySelfBean mMySelfBean) {
                            if (mMySelfBean != null) {
//                            Log.i("video_title111====", mVideoListBean.getData().get(1).getVideo_title());
//                            Log.v("mLoginRequestBean====", mVideoListBean.getResponse());
                                if (mMySelfBean.getMsg().equals("ok")) {
                                    nameText.setVisibility(View.VISIBLE);
                                    imageHead = mMySelfBean.getData().getAvatar_big();
                                    username = mMySelfBean.getData().getUname();
                                    Glide.with(instance).load(imageHead)
//                                            .placeholder(R.drawable.head_image)
                                            .into(userAvatar);  //填写头像
                                    nameText.setText(username);
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(this);
        EventBus.getDefault().unregister(this);
    }
}
