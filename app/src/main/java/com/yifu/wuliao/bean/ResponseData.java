package com.yifu.wuliao.bean;

import java.io.Serializable;
import java.util.List;

public class ResponseData
        implements Serializable
{
    private static final long serialVersionUID = 3276268187926654946L;
    public List<RmdInfoLst> rmdInfoLst;

    public ResponseData()
    {
    }

    public ResponseData(List<RmdInfoLst> paramList)
    {
        this.rmdInfoLst = paramList;
    }

    public List<RmdInfoLst> getRmdInfoLst()
    {
        return this.rmdInfoLst;
    }

    public void setRmdInfoLst(List<RmdInfoLst> paramList)
    {
        this.rmdInfoLst = paramList;
    }

    public String toString()
    {
        return "ResponseData [rmdInfoLst=" + this.rmdInfoLst + "]";
    }
}