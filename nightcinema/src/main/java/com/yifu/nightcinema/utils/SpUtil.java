package com.yifu.nightcinema.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lijilei on 2017/1/19.
 */

public class SpUtil {
    public static final String SP = "nightCinema";
    public static final String IS_VIP = "isVip";


//    public static SpUtil getInstance() {
//        return Inner.spUtil;
//    }
//
//    static class Inner {
//        static SpUtil spUtil = new SpUtil();
//
//    }

    public static void update(Context context, String key, boolean value) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SP, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器

        editor.putBoolean(key, value);

        editor.commit();//提交修改

    }

    public static boolean get(Context context, String key){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(SP, Context.MODE_PRIVATE);
        boolean b=sharedPreferences.getBoolean(key,false);
        return b;

    }
}
