package com.example.administrator.yunproject.RxJavaUtils;


import com.example.administrator.yunproject.Bean.CollectData;
import com.example.administrator.yunproject.Bean.CollectVideoBean;
import com.example.administrator.yunproject.Bean.HeadImageBean;
import com.example.administrator.yunproject.Bean.ImageBean;
import com.example.administrator.yunproject.Bean.LoginRequestBean;
import com.example.administrator.yunproject.Bean.MySelfBean;
import com.example.administrator.yunproject.Bean.VIdeoPalyerBean;
import com.example.administrator.yunproject.Bean.VideoListBean;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiService {

    final String BASE_URL = "http://192.168.252.111:88/";//内网
//        public static final String BASE_URL = "http://47.100.160.168:8081/";//外网




    /**
     * 用户登录
     *
     * @param parama
     * @return
     */
    @POST("index.php?app=api&mod=Video&act=render")
    Observable<LoginRequestBean> getLoginData(@Body RequestBody parama);

    /**
     * 视频列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?app=api&mod=Album&act=getCatalog")
    Observable<VideoListBean> getVideoListData(@Field("id") int phoneNumber, @Field("uid") int userId);





    /**
     * 视频列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?app=api&mod=Video&act=videoInfo")
    Observable<VIdeoPalyerBean> getVideoData(@Field("uid") int uid, @Field("id") String id);


    /**
     * 根据id 获得资料
     *
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?app=api&mod=User&act=show")
    Observable<MySelfBean> getSelfData(@Field("uid") int id);


    /**
     * 添加视频缓存记录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?app=api&mod=Video&act=downloads")
    Observable<CollectVideoBean> getCollectData(@Field("uid") int uid, @Field("cid") int cid);


    /**
     * 头像上传
     *
     * @return
     */
//    @FormUrlEncoded
//    @POST("index.php?app=api&mod=User&act=upload_face")
//    Observable<CollectData> upLoadHeadImage(@Part("description") RequestBody  description, @Part MultipartBody.Part file);
//    , @Part MultipartBody.Part uploadFile
//    Observable<CollectData> upLoadHeadImage(@Field("uid") int uid);
    @Multipart
    @POST("upload.php")
    Observable<CollectData> upLoadHeadImage(@Part MultipartBody.Part file);

    /**
     * 图片上传接口
     *
     * @return
     * @POST("index.php?g=Api&m=UserApi&a=imgUp")
     */
    @Multipart
    @POST("upload.php")
    Observable<ImageBean> loadImage(@Header("userid") int uid, @Part MultipartBody.Part uploadFile);


    /**
     * 收藏和取消收藏
     */
    @FormUrlEncoded
    @POST("index.php?app=api&mod=Video&act=collect")
    Observable<CollectData> getcollData(@Field("uid") int id, @Field("type") int type, @Field("sctype") int sctype, @Field("source_id") int source_id);

    /**
     * 视频记录
     */
    @FormUrlEncoded
    @POST("index.php?app=api&mod=Video&act=addMessage")
    Observable<CollectData> addmsg(@Field("uid") int id, @Field("cid") String source_id);




}
