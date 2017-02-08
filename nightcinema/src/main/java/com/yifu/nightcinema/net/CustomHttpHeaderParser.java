package com.yifu.nightcinema.net;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * Created by lijilei on 2017/2/6.
 */

public class CustomHttpHeaderParser extends HttpHeaderParser {
    /**
     * Extracts a {@link com.android.volley.Cache.Entry} from a {@link com.android.volley.NetworkResponse}.
     *
     * @param response The network response to parse headers from
     * @param cacheTime 缓存时间，如果设置了这个值，不管服务器返回是否可以缓存，都会缓存,一天为1000*60*60*24
     * @return a cache entry for the given response, or null if the response is not cacheable.
     */
    public static Cache.Entry parseCacheHeaders(NetworkResponse response, long cacheTime) {
        Cache.Entry entry=parseCacheHeaders(response);
        long now = System.currentTimeMillis();
        long softExpire=now+cacheTime;
        entry.softTtl = softExpire;
        entry.ttl = entry.softTtl;
        return entry;
    }
}