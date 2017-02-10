package com.yifu.nightcinema.activityes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.utils.Contants;
import com.yifu.nightcinema.utils.SpUtil;
import com.yifu.platform.single.YFPlatform;


public class SplashActivity extends AppCompatActivity {
    Handler mHandler = new Handler() {
        public void handleMessage(Message paramAnonymousMessage) {
            switch (paramAnonymousMessage.what) {
                case 1001:
                    gotoMain();
                    break;

                default:
                    return;

            }

        }
    };

    public void gotoMain() {
        Intent localIntent = new Intent();
        localIntent.setClass(this, MainActivity.class);
        startActivity(localIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*set it to be no title*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        mHandler.sendEmptyMessageDelayed(1001, 3000);

        init();
    }

    private void init() {
        Contants.isVip = SpUtil.get(this,SpUtil.IS_VIP);
       YFPlatform.getInstance().init(SplashActivity.this);

    }
}
