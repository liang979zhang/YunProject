package com.example.administrator.yunproject.Bean;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Administrator on 2018/4/18.
 */
@RealmClass
public class VideoIndexInfo extends RealmObject {
//    @PrimaryKey
    private int indexid;//主键realmid

    private int id;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getIndexid() {
        return indexid;
    }

    public void setIndexid(int indexid) {
        this.indexid = indexid;
    }
}
