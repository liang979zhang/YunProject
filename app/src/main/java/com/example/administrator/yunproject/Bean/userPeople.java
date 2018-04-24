package com.example.administrator.yunproject.Bean;

import com.example.administrator.yunproject.Bean.VideoIndexInfo;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Administrator on 2018/4/18.
 */

public class userPeople extends RealmObject {
    private int personId;   //分表主键
    private RealmList<VideoIndexInfo> mOtherChatPersonData;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public RealmList<VideoIndexInfo> getmOtherChatPersonData() {
        return mOtherChatPersonData;
    }

    public void setmOtherChatPersonData(RealmList<VideoIndexInfo> mOtherChatPersonData) {
        this.mOtherChatPersonData = mOtherChatPersonData;
    }
}
