package com.yifu.nightcinema.activityes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.utils.Contants;
import com.yifu.nightcinema.utils.Util;
import com.yifu.nightcinema.view.VideoView;


public class PlayerActivity extends Activity implements View.OnClickListener {
    private final String TAG = "PlayerActivity";
    //控件
    private VideoView video_view;
    private ImageView vp_iv_mark1;
    private ImageView vp_iv_mark2;
    private ImageView gold_iv_mark3;
    private ImageView gold_iv_mark4;
    private ImageView videoPlayImg;
    private RelativeLayout frame_player_view_back;
    private TextView frame_player_view_title;
    private TextView videoCurTime;
    private TextView video_tishi_buy;
    private ProgressBar frame_player_loading;
    private SeekBar videoSeekBar;
    private LinearLayout videoPauseBtn;
    private ImageView videoPauseImg;

    //控制

    private boolean isPlay;
    private int screenWidth;
    private int screenHeight;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnInfoListener mOnInfoListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;
    private Handler mHandler;
    //资源

    private String url;
    private String title;//标题
    private String dialogMessage = "开通会员可观看完整视频";//拖动弹出消息dialog
    private int PayTime = 5000;//观看多长时间弹出计费，单位毫秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        setContentView(R.layout.activity_player);
        initView();
        initData();
        setData();
        controlPlayer();

    }

    private void setData() {

        frame_player_view_title.setText(title);
    }

    @SuppressLint("NewApi")
    private void controlPlayer() {
        // video_view.setMediaController(new MediaController(this));
        video_view.setVideoSize(screenWidth, screenHeight);
        video_view.setVideoPath(Uri.parse(url).toString());
        // video_view.start();
        startLoading(true);
        isPlay = true;
        video_view.requestFocus();
        createListener();
        //监听
        video_view.setOnPreparedListener(mOnPreparedListener);
        //设置监听视频播放完成
        video_view.setOnCompletionListener(mOnCompletionListener);

        //设置监听卡
        video_view.setOnInfoListener(mOnInfoListener);

        //添加拖动完成

        //监听播放出错
        video_view.setOnErrorListener(mOnErrorListener);


        //设置视频的拖动
        videoSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        videoPauseBtn.setOnClickListener(this);

    }

    private void createListener() {
        mHandler = new Handler() {
            @SuppressLint("NewApi")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        //更新视频的进度
                        int currentPosition = (int) video_view.getCurrentPosition();

                        videoSeekBar.setProgress(currentPosition);
//                    seekbarVideo.incrementProgressBy(1);

                        //设置视频的播放进度
                        int duration = (int) video_view.getDuration() * 100;
                        videoSeekBar.setMax(duration);
                        videoCurTime.setText(Util.stringForTime(currentPosition) + "/" + Util.stringForTime(duration));

                        //更新系统时间
//        tvSystemTime.setText(getSystemTime());

//
//        //网络缓存视频效果
//
//
//
//            //网络资源
//            int buffer = video_view.getBufferPercentage();//0~100;
//            int totalBuffer = videoSeekBar.getMax()*buffer;
//            int secondaryProgress = totalBuffer/100;
//            seekbarVideo.setSecondaryProgress(secondaryProgress);
//

                        if (!PlayerActivity.this.isDestroyed()) {
                            removeMessages(0);
                            sendEmptyMessageDelayed(0, 1000);
                        }

                        break;
                }

            }
        };
        mOnPreparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                video_view.start();
//                mVideoWidth = mp.getVideoWidth();
//                mVideoHeight = mp.getVideoHeight();


                //设置视频的总时长
//                videoCurTime.setText(Util.stringForTime(duration));
                startLoading(false);


                mHandler.sendEmptyMessage(0);
            }
        };
        mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startPay();
                PlayerActivity.this.finish();

            }
        };
        mOnInfoListener = new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START://开始卡了，拖动卡
                        Log.e(TAG, "开始卡了，拖动卡...");

                        startLoading(true);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END://卡结束了，拖动卡结束了
                        Log.e(TAG, "卡结束了，拖动卡结束了...");
                        startLoading(false);
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
        mOnErrorListener = new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        };
        mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (Contants.VipLevel >= 3) {
                        video_view.seekTo(progress);

                    } else {
                        showDialog();
                    }
                } else {
                    if (progress >= PayTime) {
                        startPay();

                    }
                }

            }

            /**
             * 当手指开始按下拖动的时候回调这个方法
             *
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // mHandler.removeMessages(HIDE_MEDIACONTROLER);

            }

            /**
             * 当手指离开拖动的时候回调这个方法
             *
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // mHandler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLER,5000);
            }
        };
    }

    private void startLoading(boolean openLoading) {
        RotateAnimation an = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        an.setInterpolator(new LinearInterpolator());//不停顿  
        an.setRepeatCount(1);//重复次数  
        an.setFillAfter(true);//停在最后  
        an.setDuration(1500);
        if (openLoading) {
            frame_player_loading.setVisibility(View.VISIBLE);
            frame_player_loading.startAnimation(an);
        } else {
            frame_player_loading.clearAnimation();
            frame_player_loading.setVisibility(View.INVISIBLE);
        }

    }


    public void showDialog() {
        Dialog dialog = new AlertDialog.Builder(this).setMessage(dialogMessage)
                .setTitle("温馨提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startPay();
                    }
                }).show();
    }

    private void startPay() {
        this.finish();
        Intent intent = new Intent(this, VipActivity.class);
        startActivity(intent);
    }

    private void initData() {


        Intent intent = getIntent();

        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
//得到屏幕宽高
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
    }

    private void initView() {
        video_view = (VideoView) findViewById(R.id.video_view);
        vp_iv_mark1 = (ImageView) findViewById(R.id.vp_iv_mark1);
        vp_iv_mark2 = (ImageView) findViewById(R.id.vp_iv_mark2);
        gold_iv_mark3 = (ImageView) findViewById(R.id.gold_iv_mark3);
        gold_iv_mark4 = (ImageView) findViewById(R.id.gold_iv_mark4);
        videoPlayImg = (ImageView) findViewById(R.id.videoPlayImg);
        frame_player_view_back = (RelativeLayout) findViewById(R.id.frame_player_view_back);
        frame_player_view_title = (TextView) findViewById(R.id.frame_player_view_title);
        videoCurTime = (TextView) findViewById(R.id.videoCurTime);
        video_tishi_buy = (TextView) findViewById(R.id.video_tishi_buy);
        frame_player_loading = (ProgressBar) findViewById(R.id.frame_player_loading);
        videoSeekBar = (SeekBar) findViewById(R.id.videoSeekBar);
        videoPauseBtn = (LinearLayout) findViewById(R.id.videoPauseBtn);
        videoPauseImg = (ImageView) findViewById(R.id.videoPauseImg);


        switch (Contants.VipLevel) {

            case 1:
                vp_iv_mark1.setImageResource(R.drawable.zuan_mark);
                vp_iv_mark2.setImageResource(R.drawable.zuan_mark);
                gold_iv_mark3.setImageResource(R.drawable.zuan_mark);
                gold_iv_mark3.setImageResource(R.drawable.zuan_mark);
                dialogMessage = "开通钻石会员可观看更多哦！";
                PayTime = 30000;
                break;
            case 2:
                vp_iv_mark1.setImageResource(R.drawable.heijin_mark);
                vp_iv_mark2.setImageResource(R.drawable.heijin_mark);
                gold_iv_mark3.setImageResource(R.drawable.heijin_mark);
                gold_iv_mark3.setImageResource(R.drawable.heijin_mark);
                dialogMessage = "开通黑金会员可观看全部高清视频哦！";
                PayTime = 30000;
                break;
            case 3:

                PayTime = 60000;
                vp_iv_mark1.setVisibility(View.GONE);
                vp_iv_mark2.setVisibility(View.GONE);
                gold_iv_mark3.setVisibility(View.GONE);
                gold_iv_mark4.setVisibility(View.GONE);
                break;
            default:
                break;
        }

//监听退出
        frame_player_view_back.setOnClickListener(this);
        video_tishi_buy.setOnClickListener(this);
        video_view.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.frame_player_view_back:
                startActivity(new Intent(this, ExitActivity.class));
                this.finish();
                break;
            case R.id.videoPauseBtn:
                //暂停or播放
                pause_play();
                break;
            case R.id.video_tishi_buy:
                startPay();

                break;
            case R.id.video_view:
                pause_play();
                break;
            default:
                break;
        }
    }

    /**
     * 暂停——继续控制
     */
    private void pause_play() {

        if (video_view.isPlaying()) {
            //播放状态
            //设置暂停
            video_view.pause();
            isPlay = false;
            videoPlayImg.setVisibility(View.VISIBLE);
            //按钮的状态设置-播放
            videoPauseImg.setBackgroundResource(android.R.drawable.ic_media_play);
        } else {
            //暂停状态
            isPlay = true;
            //设置播放
            video_view.start();
            videoPlayImg.setVisibility(View.INVISIBLE);
            //按钮状态设置-暂停
            videoPauseImg.setBackgroundResource(android.R.drawable.ic_media_pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(this, ExitActivity.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
