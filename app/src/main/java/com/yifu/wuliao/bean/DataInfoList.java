package com.yifu.wuliao.bean;

import java.io.Serializable;

public class DataInfoList
        implements Serializable
{
    private static final long serialVersionUID = 4055010674497778871L;
    public String description;
    public int fee;
    public int feeRule;
    public String feeTime;
    public String huashuCode;
    public String imgUrl1;
    public String isClip;
    public int isRmd;
    public String tags;
    public String title;
    public int videoId;
    public int videoLength;
    public String videoUrl;

    public DataInfoList()
    {
    }

    public DataInfoList(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt3, String paramString6, String paramString7, int paramInt4, int paramInt5, String paramString8)
    {
        this.description = paramString1;
        this.fee = paramInt1;
        this.feeRule = paramInt2;
        this.feeTime = paramString2;
        this.huashuCode = paramString3;
        this.imgUrl1 = paramString4;
        this.isClip = paramString5;
        this.isRmd = paramInt3;
        this.tags = paramString6;
        this.title = paramString7;
        this.videoId = paramInt4;
        this.videoLength = paramInt5;
        this.videoUrl = paramString8;
    }

    public String getDescription()
    {
        return this.description;
    }

    public int getFee()
    {
        return this.fee;
    }

    public int getFeeRule()
    {
        return this.feeRule;
    }

    public String getFeeTime()
    {
        return this.feeTime;
    }

    public String getHuashuCode()
    {
        return this.huashuCode;
    }

    public String getIsClip()
    {
        return this.isClip;
    }

    public int getIsRmd()
    {
        return this.isRmd;
    }

    public String getTags()
    {
        return this.tags;
    }

    public String getTitle()
    {
        return this.title;
    }

    public int getVideoId()
    {
        return this.videoId;
    }

    public int getVideoLength()
    {
        return this.videoLength;
    }

    public String getVideoUrl()
    {
        return this.videoUrl;
    }

    public String getimgUrl1()
    {
        return this.imgUrl1;
    }

    public void setDescription(String paramString)
    {
        this.description = paramString;
    }

    public void setFee(int paramInt)
    {
        this.fee = paramInt;
    }

    public void setFeeRule(int paramInt)
    {
        this.feeRule = paramInt;
    }

    public void setFeeTime(String paramString)
    {
        this.feeTime = paramString;
    }

    public void setHuashuCode(String paramString)
    {
        this.huashuCode = paramString;
    }

    public void setIsClip(String paramString)
    {
        this.isClip = paramString;
    }

    public void setIsRmd(int paramInt)
    {
        this.isRmd = paramInt;
    }

    public void setTags(String paramString)
    {
        this.tags = paramString;
    }

    public void setTitle(String paramString)
    {
        this.title = paramString;
    }

    public void setVideoId(int paramInt)
    {
        this.videoId = paramInt;
    }

    public void setVideoLength(int paramInt)
    {
        this.videoLength = paramInt;
    }

    public void setVideoUrl(String paramString)
    {
        this.videoUrl = paramString;
    }

    public void setimgUrl1(String paramString)
    {
        this.imgUrl1 = paramString;
    }

    public String toString()
    {
        return "DataInfoList [description=" + this.description + ", fee=" + this.fee + ", feeRule=" + this.feeRule + ", feeTime=" + this.feeTime + ", huashuCode=" + this.huashuCode + ", imgUrl1=" + this.imgUrl1 + ", isClip=" + this.isClip + ", isRmd=" + this.isRmd + ", tags=" + this.tags + ", title=" + this.title + ", videoId=" + this.videoId + ", videoLength=" + this.videoLength + ", videoUrl=" + this.videoUrl + "]";
    }
}