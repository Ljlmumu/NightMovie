package com.yifu.nightcinema.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by lijilei on 2017/2/6.
 */

public class JsonCatchRequest extends JsonRequest<String>{


    public JsonCatchRequest(int method, String url, String requestBody, Response.Listener<String> listener,
                       Response.ErrorListener errorListener) {
        super(method, url,requestBody,listener, errorListener);
    }

    // volley缓存是根据URl进行文件缓存的，有时候url一样但参数不一样，所以要进行变化
//    @Override
//    public String getCacheKey() {
//        // TODO Auto-generated method stub
//        if (jsonObject != null) {
//
//            Log.e("nnnnmmmm", jsonObject.toString().hashCode() + "");
//            return getUrl() + jsonObject.toString().hashCode();
//        } else {
//            return getUrl();
//        }
//    }



    /**
     * 强制缓存替换HttpHeaderParser.parseCacheHeaders(response));
     */
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, "UTF-8");
            return Response.success(jsonString, CustomHttpHeaderParser.parseCacheHeaders(response,1000*60*60*24*7));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));

        }

    }


}
