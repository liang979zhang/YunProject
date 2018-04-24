package com.example.administrator.yunproject.Until;

import com.example.administrator.yunproject.Bean.CacheVideoBean;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator on 2018/4/10.
 */

public class OtherPeople extends RealmObject {
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public RealmList<CacheVideoBean> getmOtherChatPersonData() {
        return mOtherChatPersonData;
    }

    public void setmOtherChatPersonData(RealmList<CacheVideoBean> mOtherChatPersonData) {
        this.mOtherChatPersonData = mOtherChatPersonData;
    }

    private int personId;   //分表主键
    private RealmList<CacheVideoBean> mOtherChatPersonData;
}
