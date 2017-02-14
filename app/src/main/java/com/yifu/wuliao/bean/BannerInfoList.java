package com.yifu.wuliao.bean;

import java.io.Serializable;

public class BannerInfoList
        implements Serializable
{
    private static final long serialVersionUID = -3180507243319525670L;
    private int bannerType;
    private int fee;
    private int feeRule;
    private String feeTime;
    private String huashuCode;
    private String imgGroupFirstUrl;
    private String imgUrlThumbnail;
    private String isClip;
    private String newDesc;
    private String softName;
    private String softSize;
    private String summary;
    private int targetId;
    private String targetUrl;
    private String title;
    private int videoLength;
    private String videoUrl;

    public BannerInfoList()
    {
    }

    public BannerInfoList(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, int paramInt4, String paramString10, String paramString11, int paramInt5, String paramString12)
    {
        this.bannerType = paramInt1;
        this.fee = paramInt2;
        this.feeRule = paramInt3;
        this.feeTime = paramString1;
        this.imgUrlThumbnail = paramString2;
        this.huashuCode = paramString3;
        this.imgGroupFirstUrl = paramString4;
        this.isClip = paramString5;
        this.newDesc = paramString6;
        this.softName = paramString7;
        this.softSize = paramString8;
        this.summary = paramString9;
        this.targetId = paramInt4;
        this.targetUrl = paramString10;
        this.title = paramString11;
        this.videoLength = paramInt5;
        this.videoUrl = paramString12;
    }

    public static long getSerialversionuid()
    {
        return -3180507243319525670L;
    }

    public int getBannerType()
    {
        return this.bannerType;
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

    public String getImgGroupFirstUrl()
    {
        return this.imgGroupFirstUrl;
    }

    public String getImgUrlThumbnail()
    {
        return this.imgUrlThumbnail;
    }

    public String getIsClip()
    {
        return this.isClip;
    }

    public String getNewDesc()
    {
        return this.newDesc;
    }

    public String getSoftName()
    {
        return this.softName;
    }

    public String getSoftSize()
    {
        return this.softSize;
    }

    public String getSummary()
    {
        return this.summary;
    }

    public int getTargetId()
    {
        return this.targetId;
    }

    public String getTargetUrl()
    {
        return this.targetUrl;
    }

    public String getTitle()
    {
        return this.title;
    }

    public int getVideoLength()
    {
        return this.videoLength;
    }

    public String getVideoUrl()
    {
        return this.videoUrl;
    }

    public void setBannerType(int paramInt)
    {
        this.bannerType = paramInt;
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

    public void setImgGroupFirstUrl(String paramString)
    {
        this.imgGroupFirstUrl = paramString;
    }

    public void setImgUrlThumbnail(String paramString)
    {
        this.imgUrlThumbnail = paramString;
    }

    public void setIsClip(String paramString)
    {
        this.isClip = paramString;
    }

    public void setNewDesc(String paramString)
    {
        this.newDesc = paramString;
    }

    public void setSoftName(String paramString)
    {
        this.softName = paramString;
    }

    public void setSoftSize(String paramString)
    {
        this.softSize = paramString;
    }

    public void setSummary(String paramString)
    {
        this.summary = paramString;
    }

    public void setTargetId(int paramInt)
    {
        this.targetId = paramInt;
    }

    public void setTargetUrl(String paramString)
    {
        this.targetUrl = paramString;
    }

    public void setTitle(String paramString)
    {
        this.title = paramString;
    }

    public void setVideoLength(int paramInt)
    {
        this.videoLength = paramInt;
    }

    public void setVideoUrl(String paramString)
    {
        this.videoUrl = paramString;
    }

    public String toString()
    {
        return "BannerInfoList [bannerType=" + this.bannerType + ", fee=" + this.fee + ", feeRule=" + this.feeRule + ", feeTime=" + this.feeTime + ", imgUrlThumbnail=" + this.imgUrlThumbnail + ", huashuCode=" + this.huashuCode + ", imgGroupFirstUrl=" + this.imgGroupFirstUrl + ", isClip=" + this.isClip + ", newDesc=" + this.newDesc + ", softName=" + this.softName + ", softSize=" + this.softSize + ", summary=" + this.summary + ", targetId=" + this.targetId + ", targetUrl=" + this.targetUrl + ", title=" + this.title + ", videoLength=" + this.videoLength + ", videoUrl=" + this.videoUrl + "]";
    }
}