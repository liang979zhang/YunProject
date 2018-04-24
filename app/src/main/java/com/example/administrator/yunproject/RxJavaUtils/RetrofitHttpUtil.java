package com.example.administrator.yunproject.RxJavaUtils;


public class RetrofitHttpUtil {
    public static ApiService getApiService() {
        return RetrofitHelper.getRetrofit().create(ApiService.class);
    }
}
