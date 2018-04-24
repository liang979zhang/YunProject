package com.example.administrator.yunproject.Until;


import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/11/9.
 */

public class GsonUtils {
    //将JSON数据解析生成指定的类
    public static <T> T jsonToBean(String jsonResult, Class<T> clz) {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonResult, clz);
        return t;
    }

    //将一个javaBean生成对应的Json数据
    public static String beanToJson(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}
