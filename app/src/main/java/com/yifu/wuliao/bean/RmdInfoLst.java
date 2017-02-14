package com.yifu.wuliao.bean;


import java.io.Serializable;
import java.util.List;

public class RmdInfoLst
        implements Serializable
{
    private static final long serialVersionUID = -6778258771021112743L;
    public int channelContentType;
    public List<DataInfoList> dataInfoList;
    public String title;

    public RmdInfoLst()
    {
    }

    public RmdInfoLst(int paramInt, List<DataInfoList> paramList, String paramString)
    {
        this.channelContentType = paramInt;
        this.dataInfoList = paramList;
        this.title = paramString;
    }

    public int getChannelContentType()
    {
        return this.channelContentType;
    }

    public List<DataInfoList> getDataInfoList()
    {
        return this.dataInfoList;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setChannelContentType(int paramInt)
    {
        this.channelContentType = paramInt;
    }

    public void setDataInfoList(List<DataInfoList> paramList)
    {
        this.dataInfoList = paramList;
    }

    public void setTitle(String paramString)
    {
        this.title = paramString;
    }

    public String toString()
    {
        return "RmdInfoLst [channelContentType=" + this.channelContentType + ", dataInfoList=" + this.dataInfoList + ", title=" + this.title + "]";
    }
}