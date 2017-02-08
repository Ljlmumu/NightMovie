package com.yifu.nightcinema.net;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by lijilei on 2017/2/6.
 * Volley内存缓存
 *
 */

public class BitmapCatch implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> lruCache;
    private int max = 5 * 1024 * 1024;

    public BitmapCatch() {
        lruCache = new LruCache<String, Bitmap>(max) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);

    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}
