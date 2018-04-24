package com.example.administrator.yunproject.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yunproject.Activity.PlayVideoActivity;
import com.example.administrator.yunproject.Adapter.CacheAdapter;
import com.example.administrator.yunproject.Bean.CacheVideoBean;
import com.example.administrator.yunproject.Bean.VideoData;
import com.example.administrator.yunproject.Listener.OnRecyclerViewVideoClickListener;
import com.example.administrator.yunproject.R;
import com.example.administrator.yunproject.Until.MessageInfo;
import com.example.administrator.yunproject.Until.OtherPeople;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Administrator on 2018/3/29.
 */

public class CacheFragment extends RecycleBaseFragment {
    @BindView(R.id.cache_recyclerview)
    RecyclerView cacheRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.pulltorefresh)
    PullToRefreshLayout pulltorefresh;
    private Context instance;
    private Realm realm2;
    private List<CacheVideoBean> data;
    private List<VideoData> tblist = new ArrayList<>();
    private int userId;

    private RealmResults<OtherPeople> userList;
    private CacheAdapter mCacheAdapter;

    private MyHandler myHandler;

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        instance = getActivity();
        realm2 = Realm.getDefaultInstance();
        RealmQuery<CacheVideoBean> query = realm2.where(CacheVideoBean.class);
//        data = query.findAll().sort("realmid", Sort.DESCENDING);
        userId = SharedPreferencesUtility.getUserId(instance);
        myHandler = new MyHandler();

        getData();


    }

    private void getData() {
        userList = realm2.where(OtherPeople.class).equalTo("personId", userId).findAll();


        if (userList != null && userList.size() != 0) {
            if (userList != null && userList.size() != 0) {
                for (int i = 0; i < userList.size(); i++) {
                    data = userList.get(i).getmOtherChatPersonData();
                    for (int j = 0; j < data.size(); j++) {
                        VideoData mVideoData = new VideoData();
                        mVideoData.setTitle(data.get(j).getTitle());  //视频名称
                        mVideoData.setVideoPath(data.get(j).getVideoPath());  //视频地址
                        tblist.add(mVideoData);  //添加list数组
                    }

//

                }
            }
        }
    }


    private List<VideoData> getData2() {

        List<VideoData> videoData = new ArrayList<>();
        userList = realm2.where(OtherPeople.class).equalTo("personId", userId).findAll();


        if (userList != null && userList.size() != 0) {
            if (userList != null && userList.size() != 0) {
                for (int i = 0; i < userList.size(); i++) {
                    data = userList.get(i).getmOtherChatPersonData();
                    for (int j = 0; j < data.size(); j++) {
                        VideoData mVideoData = new VideoData();
                        mVideoData.setTitle(data.get(j).getTitle());  //视频名称
                        mVideoData.setVideoPath(data.get(j).getVideoPath());  //视频地址
                        videoData.add(mVideoData);  //添加list数组
                    }

//

                }
            }
        }


        return videoData;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cache;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(final MessageInfo messageInfo) {
        realm2 = Realm.getDefaultInstance();
        RealmQuery<CacheVideoBean> query = realm2.where(CacheVideoBean.class);
        data = query.findAll().sort("realmid", Sort.DESCENDING);
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                VideoData mVideoData = new VideoData();
                mVideoData.setTitle(data.get(i).getTitle());  //视频名称
                mVideoData.setVideoPath(data.get(i).getVideoPath());  //视频地址
                tblist.add(mVideoData);  //添加list数组
            }
        }

        fetchData();
    }

    @Override
    public void fetchData() {
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        cacheRecyclerview.setLayoutManager(mLinearLayoutManager);
        //        mRecyclerView.setNestedScrollingEnabled(false);
        cacheRecyclerview.addItemDecoration(new DividerItemDecoration(instance, DividerItemDecoration.VERTICAL));   //添加分割线
        //        openredRecycle.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_mileage)); //自定义分割线样式
        cacheRecyclerview.setHasFixedSize(true);
        mCacheAdapter = new CacheAdapter(instance, tblist, mRecyclerViewItemClickListener);
        cacheRecyclerview.setAdapter(mCacheAdapter);
    }


    private OnRecyclerViewVideoClickListener mRecyclerViewItemClickListener = new OnRecyclerViewVideoClickListener() {
        @Override
        public void onRecyclerViewItemClicked(int position, RecyclerView.ViewHolder viewHolder, String videourl) {
            Intent playVideoIntent = new Intent(instance, PlayVideoActivity.class);
            playVideoIntent.putExtra("videourl", videourl);
            startActivity(playVideoIntent);
        }

        @Override
        public void onRecyclerViewItemLongClicked(int position, RecyclerView.ViewHolder viewHolder) {
            new AlertDialog.Builder(instance)
                    .setTitle("提示")
                    .setMessage("是否删除该视频")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Realm mRealm = Realm.getDefaultInstance();
                            mRealm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    RealmResults<CacheVideoBean> cacheVideoBeans = realm2.where(CacheVideoBean.class).findAll();

                                    cacheVideoBeans.deleteFromRealm(position);
                                    myHandler.sendEmptyMessage(12);


                                }
                            });

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    })
                    .create()
                    .show();
        }
    };


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 12:
                    mCacheAdapter.setData(getData2());
                    mCacheAdapter.notifyDataSetChanged();
                    break;
            }


        }
    }

    public static CacheFragment newInstance(int position) {
        CacheFragment myFragment = new CacheFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        myFragment.setArguments(b);
        return myFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initpulltorefresh() {
        pulltorefresh.setCanLoadMore(false);
        pulltorefresh.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                mCacheAdapter.setData(getData2());
                mCacheAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束刷新
                        pulltorefresh.finishRefresh();


                    }
                }, 1000);
            }

            @Override
            public void loadMore() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
