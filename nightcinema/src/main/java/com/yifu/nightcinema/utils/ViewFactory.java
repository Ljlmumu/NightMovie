package com.yifu.nightcinema.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.net.VolleyUtil;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @param text
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
                R.layout.view_banner, null);
        //ImageLoader.getInstance().displayImage(url, imageView);
        VolleyUtil.getInstance().showImage(url, imageView);


        return imageView;
    }
}
