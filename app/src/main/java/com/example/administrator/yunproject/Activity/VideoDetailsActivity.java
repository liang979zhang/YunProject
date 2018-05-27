package com.example.administrator.yunproject.Activity;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.example.administrator.yunproject.Adapter.VideoDetailAdapter;
import com.example.administrator.yunproject.Bean.CacheVideoBean;
import com.example.administrator.yunproject.Bean.CollectData;
import com.example.administrator.yunproject.Bean.CollectVideoBean;
import com.example.administrator.yunproject.Bean.VIdeoPalyerBean;
import com.example.administrator.yunproject.Bean.VideoIndexInfo;
import com.example.administrator.yunproject.Bean.VideoListBean;
import com.example.administrator.yunproject.Bean.userPeople;
import com.example.administrator.yunproject.Fragment.MoreDialogFragment;
import com.example.administrator.yunproject.Listener.OnRecyclerViewItemClickListener;
import com.example.administrator.yunproject.Listener.VideoListener;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.RxJavaUtils.RetrofitHttpUtil;
import com.example.administrator.yunproject.Until.BitmapImageSave;
import com.example.administrator.yunproject.Until.DownloadUtil;
import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.OtherPeople;
import com.example.administrator.yunproject.Until.OtherUtils;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.example.administrator.yunproject.okhttp.OkHttpUtil;
import com.example.administrator.yunproject.okhttp.ProgressHelper;
import com.example.administrator.yunproject.okhttp.UIProgressResponseListener;
import com.example.administrator.yunproject.pay.AlipayConstants;
import com.example.administrator.yunproject.pay.zfb.OrderInfoUtil2_0;
import com.example.administrator.yunproject.pay.zfb.PayResult;
import com.example.administrator.yunproject.pay.zfb.ZfbPayBean;
import com.example.gsyvideoplayer.GSYVideoManager;
import com.example.gsyvideoplayer.ProgressListener2;
import com.example.gsyvideoplayer.utils.OrientationUtils;
import com.example.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.mchsdk.paysdk.mylibrary.BaseActivity;
import com.mchsdk.paysdk.retrofitutils.result.HttpResponseException;
import com.mchsdk.paysdk.retrofitutils.retrofit.RxUtil;
import com.mchsdk.paysdk.retrofitutils.rxjava.observable.SchedulerTransformer;
import com.mchsdk.paysdk.retrofitutils.rxjava.observer.BaseObserver;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static com.example.administrator.yunproject.pay.AlipayConstants.SDK_PAY_FLAG;


/**
 * Created by Administrator on 2018/3/29.
 * GSYBaseActivityDetail
 */

public class VideoDetailsActivity extends BaseActivity {
    public final static String TAG = "MomentsVideoViewHolder";
    @BindView(R.id.list_item_video_player)
    StandardGSYVideoPlayer listItemVideoPlayer;
    //    @BindView(R.id.post_detail_nested_scroll)
//    NestedScrollView postDetailNestedScroll;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    //    @BindView(R.id.load_voide)
//    TextView loadVoide;
//    @BindView(R.id.main_progress1)
//    ProgressBar mainProgress1;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.more_image)
    ImageView moreImage;
    @BindView(R.id.tv_coll)
    TextView tvColl;
    @BindView(R.id.iv_coll)
    ImageView ivColl;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.change_speed)
    TextView changeSpeed;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    private VideoDetailsActivity instance;
    private OrientationUtils orientationUtils;
    private File file;
    private float speed = 1;
    private Realm mRealm;
    private List<CacheVideoBean> data;

    private String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    private int userId;
    private MoreDialogFragment mMoreDialogFragment;
    private int videoId;
    private int collid = -1;//-1没有收藏 0 收藏
    private boolean isdialog = false;
    private int id;//专辑id，用于数据保存
    private int duration;
    private int flag;
    private double price;//专辑价格
    private String title;//专辑标题
    private NotificationCompat.Builder builderProgress;
    private NotificationManager notificationManager;
    private LinearLayout llzfb;
    private LinearLayout llwx;
    private ImageView tv_cancel;
    private RadioButton rb_zfi;
    private RadioButton rb_wx;
    private int zfstate = -1;//支付选择
    private AlertDialog alertlogpay;
    private MyHandler myHandler;
    private IWXAPI msgApi;

    String url = "http://wxpay.wxutil.com/pub_v2/app/app_pay.php";
    private LinearLayout ll_zhifu;
    private PopupWindow window2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        setContentView(R.layout.activity_videodetail);
        ButterKnife.bind(this);
        instance = this;
        userId = SharedPreferencesUtility.getUserId(instance);
//        Log.i("userID====", String.valueOf(userId));
        addActivity(instance);
        initView();
        msgApi = WXAPIFactory.createWXAPI(this, AlipayConstants.WX_APP_ID);


    }


    private void initView() {

        if (getIntent() != null) {
            videoId = getIntent().getIntExtra("videoId", 0);
            duration = getIntent().getIntExtra("duration", -1);
            flag = getIntent().getIntExtra("flag", -1);
            price = getIntent().getDoubleExtra("price", -1);
            title = getIntent().getStringExtra("title");


        }


        Log.i("videoId+++===", String.valueOf(videoId));

        getData(videoId);  //数据请求
//        downloadOkHttpFile();    //视屏下载

    }


    private void resolveNormalVideoUI() {
        //增加title
        listItemVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        listItemVideoPlayer.getBackButton().setVisibility(View.GONE);
    }


    private void initVideoUtils(VIdeoPalyerBean mVIdeoPalyerBean, String id) {


        this.id = Integer.valueOf(mVIdeoPalyerBean.getData().getId());
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, listItemVideoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        //设置返回键
        listItemVideoPlayer.getBackButton().setVisibility(View.VISIBLE);
        listItemVideoPlayer.setIsTouchWiget(true);
        //设置全屏按键功能
        listItemVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(instance, listItemVideoPlayer);
            }
        });
        listItemVideoPlayer.setRotateViewAuto(true);
        listItemVideoPlayer.setLockLand(true);
        listItemVideoPlayer.setPlayTag(TAG);
        listItemVideoPlayer.setShowFullAnimation(false);
        //循环
        listItemVideoPlayer.setLooping(false);
        listItemVideoPlayer.setNeedLockFull(true);
//        listItemVideoPlayer.setCacheWithPlay(false);

        listItemVideoPlayer.setStandardVideoAllCallBack(new VideoListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
//                if (!holder.listItemVideoPlayer.isIfCurrentIsFullscreen()) {
                //静音
                GSYVideoManager.instance().setNeedMute(false);


            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                //全屏不静音
                GSYVideoManager.instance().setNeedMute(false);
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {
                super.onEnterFullscreen(url, objects);
                GSYVideoManager.instance().setNeedMute(false);
            }
        });

        listItemVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {  //小屏幕返回按钮
            @Override
            public void onClick(View view) {
                finishActivity(instance);
                listItemVideoPlayer.releaseAllVideos();
            }
        });

        int freetime = mVIdeoPalyerBean.getData().getFreetime() * 1000;
        if (mVIdeoPalyerBean.getData().getIsBuy() == -1) {
            isdialog = true;
            if (isdialog) {
                tvBuy.setVisibility(View.VISIBLE);
                tvCancel.setVisibility(View.VISIBLE);
            } else {
                tvBuy.setVisibility(View.GONE);
                tvCancel.setVisibility(View.GONE);
            }
        } else if (mVIdeoPalyerBean.getData().getIsBuy() == 1) {
            isdialog = false;
        }
        listItemVideoPlayer.progress(new ProgressListener2() {
            @Override
            public void progress(int pro) {
//                Log.e("tagg", "当前进度" + pro);
                if (isdialog) {
                    if (freetime - 200 < pro && pro < freetime + 200) {//312的误差
                        VideoDetailsActivity.this.listItemVideoPlayer.onVideoPause();
                        VideoDetailsActivity.this.listItemVideoPlayer.onVideoReset();
//                        showMyDialog();

                    }
                }

            }

            @Override
            public void proDuration(int duration) {
//                Log.e("tagg", "总时长" + duration);

            }

        });

    }

    private void showMyDialog() {

        View mPopView = getLayoutInflater().inflate(R.layout.popwindow_layout, null);
        PopupWindow mPopupWindow = new PopupWindow(mPopView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 点击popuwindow外让其消失
        mPopupWindow.setOutsideTouchable(true);

        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            // 设置PopupWindow 显示的形式 底部或者下拉等
            // 在某个位置显示
            mPopupWindow.showAtLocation(mPopView, Gravity.TOP, 0, 30);
            // 作为下拉视图显示
            // mPopupWindow.showAsDropDown(mPopView, Gravity.CENTER, 200, 300);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoDetailsActivity.this.listItemVideoPlayer.onVideoPause();
        VideoDetailsActivity.this.listItemVideoPlayer.onVideoReset();

//        mRealm.removeAllChangeListeners();
//        mRealm.close();
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(Context mContext, final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(mContext, true, true);
    }


    private void getData(int videoId) {
        Map parama = new HashMap();
        parama.put("id", videoId);    //店铺ID, 登录之后会获得
        Log.i("videoId2323===", String.valueOf(videoId));
        RequestBody body = RxUtil.getMapBody(parama);
        RetrofitHttpUtil.getApiService()
                .getVideoListData(videoId, userId)
                .compose(this.<VideoListBean>bindToLifecycle())
                .compose(SchedulerTransformer.<VideoListBean>transformer())
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
                .subscribe(new BaseObserver<VideoListBean>() {
                    @Override
                    protected void onSuccess(VideoListBean mVideoListBean) {
                        if (mVideoListBean != null) {
//                            Log.i("video_title111====", mVideoListBean.getData().get(1).getVideo_title());
//                            Log.v("mLoginRequestBean====", mVideoListBean.getResponse());
                            if (mVideoListBean.getMsg().equals("ok")) {
                                final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
                                recyclerview.setLayoutManager(mLinearLayoutManager);
                                //        mRecyclerView.setNestedScrollingEnabled(false);
//                                recyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
                                //        openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式

                                recyclerview.setHasFixedSize(true);
                                VideoDetailAdapter mVideoDetailAdapter = new VideoDetailAdapter(instance, mVideoListBean.getData().getCatalog(), mRecyclerViewItemClickListener);
                                recyclerview.setAdapter(mVideoDetailAdapter);
                                if (!mVideoListBean.getData().getCatalog().isEmpty() && mVideoListBean.getData().getCatalog().get(0).getId() != null) {
                                    getVideoData(0, mVideoListBean.getData().getCatalog().get(0).getId(), tvShow);
                                    Log.i("hausdhasd====", mVideoListBean.getData().getCatalog().get(0).getId());
                                }


                                if (mVideoListBean.getData().getIs_collect() == 0) {
                                    collUI(0);
                                    collid = 0;
                                } else if (mVideoListBean.getData().getIs_collect() == -1) {
                                    collUI(2);
                                    collid = -1;
                                }


                            } else {
                                Toast.makeText(instance, mVideoListBean.getMsg(), Toast.LENGTH_SHORT).show();
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


    private OnRecyclerViewItemClickListener mRecyclerViewItemClickListener = new OnRecyclerViewItemClickListener() {

        @Override
        public void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder, String id, TextView updataVideo) {
            Log.i("video_title====", id);
            getVideoData(position, id, updataVideo);
        }

        @Override
        public void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder) {

        }
    };


    private void getVideoData(final int position, String id, TextView updataVideo) {
        RetrofitHttpUtil.getApiService()
                .getVideoData(userId, id)
                .compose(this.<VIdeoPalyerBean>bindToLifecycle())
                .compose(SchedulerTransformer.<VIdeoPalyerBean>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new BaseObserver<VIdeoPalyerBean>() {
                    @Override
                    protected void onSuccess(VIdeoPalyerBean mVIdeoPalyerBean) {
                        if (mVIdeoPalyerBean != null) {
                            if (mVIdeoPalyerBean.getMsg().equals("ok")) {
                                if (position == -50) {
                                    if (userId != 0) {
                                        if (mVIdeoPalyerBean.getData().getIsBuy() == -1) {
                                            ToastShort(instance, "您没有购买该专辑，无法下载");
                                        } else {
                                            downloadPic2(mVIdeoPalyerBean.getData().getVideo_address(), mVIdeoPalyerBean.getData().getVideo_title(), updataVideo, id);
                                        }
                                    } else {
                                        Toast.makeText(VideoDetailsActivity.this, "请先到个人页面登录", Toast.LENGTH_SHORT).show();
                                    }

                                } else {

                                    listItemVideoPlayer.setUp(mVIdeoPalyerBean.getData().getVideo_address(), true, null, mVIdeoPalyerBean.getData().getVideo_title());
                                    initVideoUtils(mVIdeoPalyerBean, id);

                                    addMsg(id);

//                                //增加title
                                    listItemVideoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
                                    if (duration != -1 && duration != 0) {
                                        listItemVideoPlayer.setSeekOnStart(duration);
                                    }

                                    listItemVideoPlayer.startPlayLogic();   //设置点击直接播放
                                }

                            } else {
                                Toast.makeText(instance, mVIdeoPalyerBean.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void addMsg(String id) {

        RetrofitHttpUtil.getApiService()
                .addmsg(userId, id)
                .compose(this.<CollectData>bindToLifecycle())
                .compose(SchedulerTransformer.<CollectData>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<CollectData>() {
                    @Override
                    protected void onSuccess(CollectData collectData) {
                        if (collectData != null) {
                            if (collectData.getMsg().equals("ok")) {

                            } else {
                                Toast.makeText(instance, collectData.getMsg(), Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.change_speed, R.id.more_image, R.id.tv_coll, R.id.iv_coll, R.id.tv_buy, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_speed: //加速播放
                resolveTypeUI();
                break;
            case R.id.more_image:   //弹窗
                FragmentManager fm = getSupportFragmentManager();
                mMoreDialogFragment = new MoreDialogFragment(listItemVideoPlayer);
                Bundle args = new Bundle();
                args.putInt("videoId", videoId);
                mMoreDialogFragment.setArguments(args);
                mMoreDialogFragment.show(fm, "mFriendMulttimeDialogFragment");
//                VideoDetailsActivity.this.listItemVideoPlayer.onVideoPause();
//                VideoDetailsActivity.this.listItemVideoPlayer.onVideoReset();
                break;
            case R.id.iv_coll:
            case R.id.tv_coll:
                //收藏或者取消
                if (userId != 0) {
                    if (collid == -1) {
                        collectReft(0);//0是收藏1是取消
                    } else if (collid == 0) {
                        collectReft(1);//0是收藏1是取消
                    }

                } else {
                    ToastShort(instance, "您还未登录，请登录！！");
                }

                break;

            case R.id.tv_buy:

                WindowManager wm = (WindowManager) this
                        .getSystemService(Context.WINDOW_SERVICE);
                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();
                LayoutInflater inflater2 = getLayoutInflater();
                View view3 = inflater2.inflate(R.layout.view_alert_pay, null);
                // 创建PopupWindow对象，其中：
                // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
                // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
                window2 = new PopupWindow(view3, width, height - 200, true);
                // 设置PopupWindow的背景
                window2.setBackgroundDrawable(getResources().getDrawable(R.drawable.beijing));
                // 设置PopupWindow是否能响应外部点击事件
                window2.setOutsideTouchable(true);
                // 设置PopupWindow是否能响应点击事件
                window2.setTouchable(true);
                // 显示PopupWindow，其中：
                // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
                window2.showAtLocation(rlMain, Gravity.BOTTOM, 0, -500);

                setpayView(view3);


                break;

            case R.id.tv_cancel:
                listItemVideoPlayer.onVideoReset();
                listItemVideoPlayer.startPlayLogic();
                break;
        }
    }

    /**
     * 支付弹出
     *
     * @param view2
     */
    private void setpayView(View view2) {
        tv_cancel = view2.findViewById(R.id.iv_guanbi);
        rb_zfi = view2.findViewById(R.id.rb_zfi);
        rb_wx = view2.findViewById(R.id.rb_wx);

        ll_zhifu = view2.findViewById(R.id.ll_zhifu);
        tv_cancel.setOnClickListener(new MyClick());
        rb_zfi.setOnClickListener(new MyClick());
        rb_wx.setOnClickListener(new MyClick());
        ll_zhifu.setOnClickListener(new MyClick());


    }

    private void collectReft(int i) {

        RetrofitHttpUtil.getApiService()
                .getcollData(userId, i, 1, videoId)
                .compose(this.<CollectData>bindToLifecycle())
                .compose(SchedulerTransformer.<CollectData>transformer())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new BaseObserver<CollectData>() {
                    @Override
                    protected void onSuccess(CollectData collectData) {
                        if (collectData != null) {
//                            Log.i("video_title111====", mVideoListBean.getData().get(1).getVideo_title());
//                            Log.v("mLoginRequestBean====", mVideoListBean.getResponse());
                            if (collectData.getMsg().equals("ok")) {
                                if (collectData.getData() == 0) {//收藏成功是0 ，取消成功是2
                                    collUI(collectData.getData());
                                    collid = 0;
                                } else if (collectData.getData() == 2) {
                                    collUI(collectData.getData());
                                    collid = -1;
                                }
                            } else {
                                Toast.makeText(instance, collectData.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void collUI(int data) {
        if (data == 0) {
            tvColl.setText("取消收藏");
            Glide.with(VideoDetailsActivity.this).load(R.drawable.shoucang).into(ivColl);
        } else if (data == 2) {
            tvColl.setText("收藏");
            Glide.with(VideoDetailsActivity.this).load(R.drawable.shoucang_1).into(ivColl);

        }
    }


    /**
     * 显示比例
     * 注意，GSYVideoType.setShowType是全局静态生效，除非重启APP。
     */
    private void resolveTypeUI() {
        if (speed == 1) {
            speed = 1.5f;
        } else if (speed == 1.5f) {
            speed = 2f;
        } else if (speed == 2) {
            speed = 0.5f;
        } else if (speed == 0.5f) {
            speed = 0.25f;
        } else if (speed == 0.25f) {
            speed = 1;
        }
        changeSpeed.setText(speed + "X");
        listItemVideoPlayer.setSpeedPlaying(speed, true);
    }


    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("按下了back键   onKeyDown()");
            finishActivity(instance);
            listItemVideoPlayer.releaseAllVideos();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();

//        Log.e("tag", "当前进度=======" + listItemVideoPlayer.getCurrentPositionWhenPlaying());
//        Log.e("tag", "总时长" + listItemVideoPlayer.getDuration2());
        int currentPositionWhenPlaying = listItemVideoPlayer.getCurrentPositionWhenPlaying();
        int duration2 = listItemVideoPlayer.getDuration2();

        if (currentPositionWhenPlaying < 312 && duration2 == 0) {
            return;
        }

        if (flag == 108) {
            return;
        }

        mRealm = Realm.getDefaultInstance();

        RealmResults<userPeople> userPeoples = mRealm.where(userPeople.class).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                if (userPeoples.size() > 0 && userPeoples != null) {
                    for (int i = 0; i < userPeoples.size(); i++) {
                        RealmList<VideoIndexInfo> videoIndexInfos = userPeoples.get(i).getmOtherChatPersonData();
                        if (videoIndexInfos.size() > 0 && videoIndexInfos != null) {
                            for (int j = 0; j < videoIndexInfos.size(); j++) {
                                if (videoIndexInfos.get(j).getId() == videoId) {
                                    videoIndexInfos.deleteFromRealm(j);//如果是点击相同的视频的话删除数据，下面重新创建
                                }
                            }
                        }


                    }
                }

            }
        });


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                int indexid = SharedPreferencesUtility.getindexid(instance);
                //存入数据库
                userPeople user = realm.createObject(userPeople.class);
                user.setPersonId(userId);
                VideoIndexInfo mVideoIndexInfo = realm.createObject(VideoIndexInfo.class);
                mVideoIndexInfo.setIndexid(indexid);
                mVideoIndexInfo.setId(videoId);
                mVideoIndexInfo.setDuration(currentPositionWhenPlaying);
                user.getmOtherChatPersonData().add(mVideoIndexInfo);
                if (indexid == 0) {
                    indexid++;
                    SharedPreferencesUtility.setindexid(instance, indexid);
                } else {
                    indexid++;
                    SharedPreferencesUtility.setindexid(instance, indexid);
                }
            }
        });

    }


    /**
     * 视频下载
     */
    private void downloadPic2(String videourl, String title, TextView updataVideo, String videoId) {
        DownloadUtil.getInstance().download(instance, videourl, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String path) {
                getCollectData(videoId);
                updataVideo.setText("下载完成");
                updataVideo.setTextColor(Color.parseColor("#000000"));

                if (userId != 0) {
                    String videoUrl = String.valueOf(new File(path));
                    setDataStorage(videoUrl, title);   //数据存储
                }
            }

            @Override
            public void onDownloading(int progress) {

                updataVideo.setText("下载进度" + progress + "%");
//                    builderProgress.setProgress(100, (int) ((100 * bytesRead) / contentLength), false);
//                    //再次通知
//                    notificationManager.notify(2, builderProgress.build());

                createNotification(progress);


            }

            @Override
            public void onDownloadFailed() {
                DownloadUtil.getInstance().cancel(videourl);
            }
        });


    }


    public void createNotification(long pro) {
        //进度条通知
        builderProgress = new NotificationCompat.Builder(this);
        builderProgress.setContentTitle("下载中");
        builderProgress.setSmallIcon(R.mipmap.ic_launcher);
        builderProgress.setTicker("进度条通知");
        builderProgress.setProgress(100, (int) pro, false);
//        final Notification notification = builderProgress.build();
        notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        //发送一个通知
        //再次通知
        notificationManager.notify(2, builderProgress.build());
    }

    /**
     * 数据存储
     *
     * @param videoUrl
     * @param videoTitle
     */
    private void setDataStorage(String videoUrl, String videoTitle) {


        mRealm = Realm.getDefaultInstance();
        RealmResults<OtherPeople> otherPeoples = mRealm.where(OtherPeople.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//
//                        //将之前的mRealm改成realm参数对象就可以了
//                        final RealmResults<OtherPeople> realmResults = realm.where(OtherPeople.class).findAll();
//

//                otherPeoples.deleteAllFromRealm();

                //存入数据库
                OtherPeople user = realm.createObject(OtherPeople.class);
                user.setPersonId(userId);

                CacheVideoBean mCacheVideoBean = realm.createObject(CacheVideoBean.class);
                int realmid = SharedPreferencesUtility.getRealmid(instance);
                mCacheVideoBean.setRealmid(realmid);   //视频主键
                mCacheVideoBean.setVideoPath(videoUrl);    //视频地址
                mCacheVideoBean.setTitle(videoTitle);    //视频名称
//                        mCacheVideoBean.setKey(true);
                user.getmOtherChatPersonData().add(mCacheVideoBean);
                if (realmid == 0) {
                    realmid++;
                    SharedPreferencesUtility.setRealmid(instance, realmid);
                } else {
                    realmid++;
                    SharedPreferencesUtility.setRealmid(instance, realmid);
                }
            }
        });
//            }
//        });

    }

    /**
     * 获取是否缓存的记录
     */
    private void getCollectData(String videoId) {
        Log.i("asasfdsf=====", String.valueOf(userId));
        Log.i("videoId=====", videoId);
        RetrofitHttpUtil.getApiService()
                .getCollectData(userId, Integer.parseInt(videoId))
                .compose(this.<CollectVideoBean>bindToLifecycle())
                .compose(SchedulerTransformer.<CollectVideoBean>transformer())
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
                .subscribe(new BaseObserver<CollectVideoBean>() {
                    @Override
                    protected void onSuccess(CollectVideoBean mCollectVideoBean) {
                        if (mCollectVideoBean != null) {
//                            Log.i("video_title111====", mVideoListBean.getData().get(1).getVideo_title());
//                            Log.v("mLoginRequestBean====", mVideoListBean.getResponse());
                            if (mCollectVideoBean.getMsg().equals("ok")) {
                                Toast.makeText(VideoDetailsActivity.this, "下载成功！", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(instance, mCollectVideoBean.getMsg(), Toast.LENGTH_SHORT).show();
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

    //弹出框点击
    private class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (myHandler == null) {
                myHandler = new MyHandler();
            }

            switch (view.getId()) {
                case R.id.rb_zfi:
                    rb_zfi.setChecked(true);
                    rb_wx.setChecked(false);
                    zfstate = 1;

                    break;
                case R.id.rb_wx:
                    rb_zfi.setChecked(false);
                    rb_wx.setChecked(true);
                    zfstate = 2;
                    break;
                case R.id.iv_guanbi:
                    if (window2 != null) {
                        window2.dismiss();
                    }
                    break;

                case R.id.ll_zhifu:
                    if (window2 != null) {
                        window2.dismiss();
                    }
                    //TODO
                    if (zfstate == 1) {
                        payAli();
                    } else if (zfstate == 2) {
                        wxpay();
                    }

                    break;
            }
        }
    }

    //微信支付
    private void wxpay() {
        if (!OtherUtils.isWXAppInstalledAndSupported(instance)) {
            Toast.makeText(instance, "没有安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(instance, "获取订单中...", Toast.LENGTH_SHORT).show();
        try {
            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                                    Log.e("tag", response.body().string());
                    String body = response.body().string();
                    PayReq payReq = GsonUtils.jsonToBean(body, PayReq.class);

                    Message message = new Message();
                    message.what = 1003;
                    message.obj = payReq;
                    myHandler.sendMessage(message);


                }
            });

        } catch (Exception e) {
            Log.e("PAY_GET", "异常：" + e.getMessage());
            Toast.makeText(instance, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //支付宝支付
    private void payAli() {
        ZfbPayBean zfbPayBean = new ZfbPayBean();
        zfbPayBean.setSubject(title);
        zfbPayBean.setTotal_amount(String.valueOf(price));
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(instance, AlipayConstants.APPID, false, zfbPayBean);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        boolean rsa2 = (AlipayConstants.RSA2_PRIVATE.length() > 0);
        String privateKey = rsa2 ? AlipayConstants.RSA2_PRIVATE : AlipayConstants.RSA_PRIVATE;

        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(instance);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                myHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SDK_PAY_FLAG:
                    if (alertlogpay != null) {
                        alertlogpay.dismiss();
                    }
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        isdialog = true;
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(instance, "支付成功", Toast.LENGTH_SHORT).show();
                        tvBuy.setVisibility(View.GONE);
                        tvCancel.setVisibility(View.GONE);
                        listItemVideoPlayer.onVideoResume();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(instance, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 1003:
                    PayReq payReq = (PayReq) msg.obj;
                    msgApi.sendReq(payReq);

                    break;

            }

        }
    }




}
