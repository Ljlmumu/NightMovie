package com.yifu.nightcinema;

import android.app.Application;

import com.tendcloud.tenddata.TDGAAccount;
import com.tendcloud.tenddata.TalkingDataGA;
import com.yifu.nightcinema.net.VolleyUtil;

/**
 * Created by lijilei on 2017/1/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.getInstance().init(this.getApplicationContext());
        TalkingDataGA.init(this, "23188A0AC2E94CB6A7E7DBCA199ECC00", "1030");
        TDGAAccount.setAccount("1030");

    }
}
