package com.example.administrator.yunproject.Sqlist;

import android.content.Context;
import android.util.Log;

import com.example.administrator.yunproject.Until.GsonUtils;
import com.example.administrator.yunproject.Until.SharedPreferencesUtility;

import io.realm.Realm;

/**
 * Created by Administrator on 2018/4/4.
 */

public class videoData {
    private static final String TAG = videoData.class.getSimpleName();

    /**
     * 缓存视频确认插入
     *
     * @param readtext
     */
    public static void wordConfirm(String readtext ,Context mContext) {

//        int realmid = SharedPreferencesUtility.getRealmid(mContext);
////        finllayRealmid====
//        Log.i(TAG, "startRealmidmsg=====" + realmid);
//        final personData mPersonData = GsonUtils.jsonToBean(readtext, personData.class);
//        long userTime = mPersonData.getCreateTime();
//        Realm realm = Realm.getDefaultInstance();
////        /***写法一***/
////        final int finalRealmid = realmid;
//        final PersonSqlist mPersonSqlist = new PersonSqlist();
//        mPersonSqlist.setRealmid(realmid);
//        mPersonSqlist.setState(mPersonData.getState());  //发送状态
//        mPersonSqlist.setId(mPersonData.getId());
//        mPersonSqlist.setType(mPersonData.getType());
//        mPersonSqlist.setChannel(mPersonData.getChannel());
//        mPersonSqlist.setContent(mPersonData.getContent());   //保存发送信息文字，缩略图地址，和视频第一帧
////        mPersonSqlist.setCreateTime(mPersonData.getCreateTime());
//        mPersonSqlist.setCreateTime(userTime);
//        mPersonSqlist.setUserId(mPersonData.getUserId());
//        mPersonSqlist.setNickName(mPersonData.getNickName());
//        mPersonSqlist.setReceivedId(mPersonData.getReceivedId());
//        mPersonSqlist.setHeadBigImage(mPersonData.getUserInfo().getHeadBigImage());
//        mPersonSqlist.setHeadThumbImage(mPersonData.getUserInfo().getHeadThumbImage());
//        mPersonSqlist.setGender(mPersonData.getUserInfo().getGender());
//        mPersonSqlist.setPhone(mPersonData.getUserInfo().getPhone());
//        mPersonSqlist.setId(mPersonData.getUserInfo().getGroupInfo().getId());
//        mPersonSqlist.setGroupImage(mPersonData.getUserInfo().getGroupInfo().getGroupImage());
//        mPersonSqlist.setGroupName(mPersonData.getUserInfo().getGroupInfo().getGroupName());
//        mPersonSqlist.setGroupDescription(mPersonData.getUserInfo().getGroupInfo().getGroupDescription());
//        mPersonSqlist.setGroupMax(mPersonData.getUserInfo().getGroupInfo().getGroupMax());
//        mPersonSqlist.setUpdateTime(mPersonData.getUserInfo().getGroupInfo().getUpdateTime());
//        mPersonSqlist.setNumberPeople(mPersonData.getUserInfo().getGroupInfo().getNumberPeople());
//        mPersonSqlist.setOriginalUrl(mPersonData.getOriginalUrl());  //图片原地址和视频原地址
//        mPersonSqlist.setVideoTime(mPersonData.getVideoTime());  //视频时长
//
////        Log.i(TAG, "finalRealmid=====" + finalRealmid);
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
////                PersonSqlist mPersonSqlist = realm.createObject(PersonSqlist.class);
//                //存入数据库
//                realm.copyToRealmOrUpdate(mPersonSqlist);
//            }
//        });
//        if (realmid == 0) {
////            realmid = Long.valueOf(realmid);
//            realmid++;
//            SharedPreferencesUtility.setRealmid(mContext, realmid);
//        } else {
//            realmid++;
//            SharedPreferencesUtility.setRealmid(mContext, realmid);
//        }
//        loadData2(readtext);
    }
}
