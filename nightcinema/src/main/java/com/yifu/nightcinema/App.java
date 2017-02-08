package com.yifu.nightcinema;

import android.app.Application;

import com.yifu.nightcinema.net.VolleyUtil;

/**
 * Created by lijilei on 2017/1/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.getInstance().init(this.getApplicationContext());

    }
}
