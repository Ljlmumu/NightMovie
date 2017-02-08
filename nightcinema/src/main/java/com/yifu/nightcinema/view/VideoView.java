package com.yifu.nightcinema.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by lijilei on 2017/1/18.
 */

public class VideoView extends android.widget.VideoView {

    /**
     * add by yangguangfu
     */
    private MediaPlayer.OnSeekCompleteListener mOnSeekCompleteListener;

    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener l) {
        mOnSeekCompleteListener = l;
    }

    /**
     * 根据传入的宽和高设置视频画面的大小
     * @param videoWidth
     * @param videoHeight
     * 阿福写的
     */
    public void  setVideoSize(int videoWidth,int videoHeight){
        ViewGroup.LayoutParams l = getLayoutParams();
        l.width = videoWidth;
        l.height = videoHeight;
        setLayoutParams(l);
    }
}
