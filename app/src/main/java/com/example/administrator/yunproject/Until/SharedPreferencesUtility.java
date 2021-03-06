package com.example.administrator.yunproject.Until;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/10/21.
 */

public class SharedPreferencesUtility {
    private final String TAG = SharedPreferencesUtility.class.getSimpleName();
    public static final String SHARED_PREFS_FILE = "SAVE";

    /**
     * 这里把常用的UserToken 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static String getUserToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_token", "");
    }

    public static void setUserToken(Context context, String userToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_token", userToken);
        editor.commit();
    }


    public static void clearUserToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user_token");
        editor.commit();
    }


    /**
     * 世界消息主键
     *
     * @param context
     * @return
     */
    public static int getRealmid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("realmid", 0);
    }

    public static void setRealmid(Context context, int realmid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("realmid", realmid);
        editor.commit();
    }


    public static int getindexid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("indexid", 0);
    }

    public static void setindexid(Context context, int indexid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("indexid", indexid);
        editor.commit();
    }


    /**
     * layoutKey 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static boolean getLayoutKey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("layout_key", false);
    }

    public static void setLayoutKey(Context context, boolean layoutKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("layout_key", layoutKey);
        editor.commit();
    }


    /**
     * 这里把常用的UserId 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static int getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", 0);
    }

    public static void setUserId(Context context, int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", userId);
        editor.commit();
    }


    public static void clearUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user_id");
        editor.commit();
    }


    /**
     * 这里把常用的UserId 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static boolean getPayKey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("pay_key", false);
    }

    public static void setPayKey(Context context, boolean PayKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("pay_key", PayKey);
        editor.commit();
    }


    /**
     * 保存用户的手机号
     *
     * @param context
     */
    public static String getUserPhone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_phone", null);
    }


    public static void setUserPhone(Context context, String mUserPhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_phone", mUserPhone);
        editor.commit();
    }


    /**
     * 保存用户的等级
     *
     * @param context
     */
    public static int getVipLevel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("vip_Level", 0);
    }


    public static void setVipLevel(Context context, int vipLevel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("vip_Level", vipLevel);
        editor.commit();
    }


    public static void setLogin(Context context, boolean islogin) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", islogin);
        editor.commit();
    }

    public static boolean getlogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login", false);

    }

}
