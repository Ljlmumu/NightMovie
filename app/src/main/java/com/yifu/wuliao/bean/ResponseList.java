package com.yifu.wuliao.bean;

import java.io.Serializable;
import java.util.List;

public class ResponseList
        implements Serializable
{
    private static final long serialVersionUID = 4326496683545415861L;
    private List<BannerInfoList> bannerInfoList;

    public ResponseList()
    {
    }

    public ResponseList(List<BannerInfoList> paramList)
    {
        this.bannerInfoList = paramList;
    }

    public List<BannerInfoList> getBannerlnfoLists()
    {
        return this.bannerInfoList;
    }

    public void setBannerlnfoLists(List<BannerInfoList> paramList)
    {
        this.bannerInfoList = paramList;
    }

    public String toString()
    {
        return "RseponseList [bannerlnfoLists=" + this.bannerInfoList + "]";
    }
}