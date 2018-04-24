package com.example.administrator.yunproject;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.yunproject.other.NetWorkStateReceiver;
import com.example.gsyvideoplayer.utils.NetworkUtils;
import com.mchsdk.paysdk.mylibrary.BaseActivity;

/**
 * Created by 89667 on 2018/4/16.
 */

public abstract class BaseAc extends BaseActivity {
    NetWorkStateReceiver netWorkStateReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!NetworkUtils.isAvailable(this)) {
            errnet();
        } else {
            sucnet();
        }

        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);


        if (!netWorkStateReceiver.checkNetworkInfo(this)) {
            errnet();
        } else {
            sucnet();
        }
    }

    public abstract void errnet();
    protected abstract void sucnet();



}
