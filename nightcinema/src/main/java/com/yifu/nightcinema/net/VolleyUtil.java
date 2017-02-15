package com.yifu.nightcinema.net;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yifu.nightcinema.R;
import com.yifu.nightcinema.bean.BaseBean;
import com.yifu.nightcinema.json.JsonPase;
import com.yifu.nightcinema.utils.Contants;

/**
 * Created by lijilei on 2017/1/17.
 */

public class VolleyUtil {
    public final String TAG = "VolleyUtil";

    ImageLoader imageLoader;

    /**
     * 外部调用api
     *
     * @return
     */
    public static VolleyUtil getInstance() {
        return inner.volleyUtil;
    }

    static class inner {
        static VolleyUtil volleyUtil = new VolleyUtil();
    }


    private RequestQueue mQueue;

    /**
     * 初始化，要在Applation中进行初始化
     *
     * @param context
     */
    public void init(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    /**
     * 请求字符串
     *
     * @param url      请求地址
     * @param listener 请求响应的监听
     */
    public void getString(String url, final OnNetListener<String> listener) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                listener.onSuccess(s);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFail();
            }
        });
        mQueue.add(stringRequest);
    }

    /**
     * 得到对象（已经解析）
     *
     * @param url
     * @param listener
     */
    public void getBean(final int tag,final String url, final OnNetListener<BaseBean> listener) {

        JsonCatchRequest josnRequest = new JsonCatchRequest(Request.Method.GET,url, null, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                Log.e(TAG, s);

                BaseBean listBean = null;
               switch (tag){
                   case Contants.TAG_VIDEOS:
                       listBean = JsonPase.toBean(s);
                       break;
                   case Contants.TAG_DETAIL:
                       listBean = JsonPase.toDetailBean(s);
                       break;
                   default:
                       break;
               }
                listener.onSuccess(listBean);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFail();
            }
        });

        josnRequest.setTag(tag);
        josnRequest.setShouldCache(true);


        mQueue.add(josnRequest);
    }


    public void getBean(final String url, final OnNetListener<BaseBean> listener) {
        getBean(Contants.TAG_VIDEOS,url,listener);

    }
    /**
     * 图片加载（带内存缓存），用于图片比较多的
     *
     * @param url
     * @param imageView
     */
    public void showImage(String url, ImageView imageView) {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(mQueue, new BitmapCatch());
        }


        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView,
                R.drawable.icon_empty, R.drawable.icon_error);
        imageLoader.get(url, listener);
    }



    /**
     * 请求结果的回调接口
     *
     * @param <T>
     */
    public interface OnNetListener<T> {

        public void onSuccess(T t);

        public void onFail();
    }

}
