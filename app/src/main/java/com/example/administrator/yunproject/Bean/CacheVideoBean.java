package com.example.administrator.yunproject.Bean;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Administrator on 2018/4/4.
 */
@RealmClass
public class CacheVideoBean implements RealmModel {
    //主键必须添加注解
//    @PrimaryKey
    private int realmid;//主键realmid
    private String title;   //视频名字
    private String videoPath;     //视频地址

//    public boolean isKey() {
//        return Key;
//    }
//
//    public void setKey(boolean key) {
//        Key = key;
//    }
//
//    private boolean Key;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }


    public int getRealmid() {
        return realmid;
    }

    public void setRealmid(int realmid) {
        this.realmid = realmid;
    }




}
