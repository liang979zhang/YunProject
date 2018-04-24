package com.example.administrator.yunproject.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yunproject.Listener.VideoListener;
import com.example.administrator.yunproject.R;
import com.example.gsyvideoplayer.GSYVideoManager;
import com.example.gsyvideoplayer.utils.OrientationUtils;
import com.example.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/5.
 */

public class PlayVideoActivity extends BaseActivity {
    public final static String TAG = "PlayVideoActivity";
    @BindView(R.id.video_player)
    StandardGSYVideoPlayer videoPlayer;
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
    private PlayVideoActivity instance;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        ButterKnife.bind(this);
        instance = this;
        addActivity(instance);
        initView();
    }


    private void initView() {
        rlBack.setVisibility(View.VISIBLE);
        tvLeft.setVisibility(View.GONE);
        String videourl = getIntent().getStringExtra("videourl");
        videoPlayer.setUp(videourl, true, null, "");
        initVideoUtils();
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.startPlayLogic();   //设置点击直接播放
    }


    private void initVideoUtils() {
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.setIsTouchWiget(true);
        //设置全屏按键功能
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(instance, videoPlayer);
            }
        });
        videoPlayer.setRotateViewAuto(true);
        videoPlayer.setLockLand(true);
        videoPlayer.setPlayTag(TAG);
        videoPlayer.setShowFullAnimation(false);
        //循环
        videoPlayer.setLooping(false);
        videoPlayer.setNeedLockFull(true);
//        listItemVideoPlayer.setCacheWithPlay(false);

        videoPlayer.setStandardVideoAllCallBack(new VideoListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
//                if (!holder.listItemVideoPlayer.isIfCurrentIsFullscreen()) {
                //静音
                GSYVideoManager.instance().setNeedMute(false);
//                }

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

        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {  //小屏幕返回按钮
            @Override
            public void onClick(View view) {
                finishActivity(instance);
                videoPlayer.releaseAllVideos();
            }
        });

    }


    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(Context mContext, final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(mContext, true, true);
    }

    @OnClick({R.id.tv_left, R.id.rl_back, R.id.tv_tilte})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                break;
            case R.id.rl_back:
                finishActivity(instance);
                videoPlayer.releaseAllVideos();
                break;
            case R.id.tv_tilte:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();

    }
}
