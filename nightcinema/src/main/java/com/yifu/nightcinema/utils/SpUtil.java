package com.yifu.nightcinema.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lijilei on 2017/1/19.
 */

public class SpUtil {
    public static final String SP = "nightCinema";
    public static final String IS_VIP = "isVip";
    public static final String VIP_LEVEL = "VipLevel";

//    public static SpUtil getInstance() {
//        return Inner.spUtil;
//    }
//
//    static class Inner {
//        static SpUtil spUtil = new SpUtil();
//
//    }
private static SharedPreferences.Editor getEditor(Context context) {
    SharedPreferences sharedPreferences = getSp(context);
    return sharedPreferences.edit();
}
    private static SharedPreferences getSp(Context context) {
        return context.getApplicationContext().getSharedPreferences(SP, Context.MODE_PRIVATE);
    }

    public static void updateBoolean(Context context, String key, boolean value) {

        SharedPreferences.Editor editor = getEditor(context);

        editor.putBoolean(key, value);

        editor.commit();//提交修改
    }



    public static boolean getBoolean(Context context, String key){
        SharedPreferences sharedPreferences = getSp(context);
        boolean b=sharedPreferences.getBoolean(key,false);
        return b;

    }

    public static void updateInt(Context context, String key, int value) {

        SharedPreferences.Editor editor = getEditor(context);

        editor.putInt(key, value);

        editor.commit();//提交修改
    }

    public static int getInt(Context context, String key){
        SharedPreferences sharedPreferences = getSp(context);
        int i=sharedPreferences.getInt(key,0);
        return i;

    }
}
