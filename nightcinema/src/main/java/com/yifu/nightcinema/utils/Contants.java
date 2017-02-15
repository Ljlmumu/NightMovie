package com.yifu.nightcinema.utils;

/**
 * Created by lijilei on 2017/1/18.
 */

public class Contants {
//体验区
    public static final String url_try = "http://php7.qyjuju.com/json2/visitor1.php?pay_Id=dxabwj&package=com.usye.asjye&appid=4";
    public static final String url_adult = "http://php7.qyjuju.com/json2/visitor2.php?pay_Id=dxabwj&package=com.usye.asjye&appid=4&page=";
    public static final String url_wuma = "http://php7.qyjuju.com/json2/visitor3.php?pay_Id=dxabwj&package=com.usye.asjye&appid=4&page=";
    public static final String url_detail = "http://php7.qyjuju.com/json2/comments.php?id=";

    public static final String url_zuanshi = "http://php7.qyjuju.com/json2/gold2.php?pay_Id=dxabwj&package=com.usye.asjye&appid=4&page=";
    public static final String url_vr = "http://php7.qyjuju.com/json2/vr.php?pay_Id=dxabwj&package=com.usye.asjye&appid=4&page=";
    /**
     * 请求的tag
     */
    public static final int TAG_VIDEOS = 0;//video列表



    public static final int TAG_DETAIL = 1;//详情




//    public static boolean isVip = false;
//    public static boolean isDiamondVip = false;
//    public static boolean isGoldVip = false;
    /**
     * 0为非会员
     * 1为永久会员
     * 2为钻石会员
     * 3为黑金会员
     *
     */
    public static int VipLevel;
}
