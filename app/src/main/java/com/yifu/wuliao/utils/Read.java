package com.yifu.wuliao.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.yifu.wuliao.bean.BannerInfoList;
import com.yifu.wuliao.bean.ResponseData;
import com.yifu.wuliao.bean.ResponseList;
import com.yifu.wuliao.bean.RmdInfoLst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Read
{
    private List<BannerInfoList> bannList;
    private Context context;
    private List<RmdInfoLst> rmdinfoLst;

    public Read(Context paramContext)
    {
        context = paramContext;
    }

    public List<RmdInfoLst> readAsset()throws IOException

    {
            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("homelist.json")));
            StringBuffer localStringBuffer = new StringBuffer();
            String str1;
            while ((str1 = localBufferedReader.readLine()) !=null)
            {
//                String str1 = localBufferedReader.readLine();
                localStringBuffer.append(str1);
            }
            String str2 = localStringBuffer.toString();
            rmdinfoLst = (new Gson().fromJson(str2, ResponseData.class)).getRmdInfoLst();


        return rmdinfoLst;
    }

    public List<BannerInfoList> readAssetBann()
            throws IOException
    {

            BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open("banner.json")));
            StringBuffer localStringBuffer = new StringBuffer();
            String str1 ;
            while ((str1= localBufferedReader.readLine()) != null)
            {
                localStringBuffer.append(str1);

            }
            String str2 = localStringBuffer.toString();
            bannList = ((ResponseList)new Gson().fromJson(str2, ResponseList.class)).getBannerlnfoLists();
           // return bannList;


        return bannList;
    }
}