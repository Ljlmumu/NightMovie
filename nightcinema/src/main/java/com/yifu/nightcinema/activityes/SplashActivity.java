package com.yifu.nightcinema.activityes;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
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
    private static final int WAIT_TIME = 100;
private com.yifu.nightcinema.view.VideoView  videoView;


    Handler mHandler = new Handler() {
        public void handleMessage(Message paramAnonymousMessage) {
            switch (paramAnonymousMessage.what) {
                case WAIT_TIME:
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
      //  mHandler.sendEmptyMessageDelayed(WAIT_TIME, 3000);
        videoView = (com.yifu.nightcinema.view.VideoView) findViewById(R.id.video_view_splashing);
        init();

        getData();
    }

    private void init() {
        Contants.VipLevel = SpUtil.getInt(this,SpUtil.VIP_LEVEL);
       YFPlatform.getInstance().init(SplashActivity.this);

    }

    public void getData() {

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        videoView.setVideoSize(point.x,point.y);
        String uri = "android.resource://" + getPackageName() + "/"+R.raw.splashing_movie ;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                gotoMain();
            }
        });
    }
}
