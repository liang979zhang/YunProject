package com.example.administrator.yunproject;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.administrator.yunproject.Until.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2018/4/4.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication mInstance;
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
//        initScreenSize();
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(Constants.DB_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
