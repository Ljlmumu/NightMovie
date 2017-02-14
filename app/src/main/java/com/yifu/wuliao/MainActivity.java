package com.yifu.wuliao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifu.wuliao.Adapter.MyAdapter;
import com.yifu.wuliao.bean.BannerInfoList;
import com.yifu.wuliao.bean.RmdInfoLst;
import com.yifu.wuliao.utils.Read;
import com.yifu.wuliao.view.SlideShowView;
import com.yifu.wuliao.view.XListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView tv_title;
    private XListView xListview;
    private MyAdapter adapter;
    private List<BannerInfoList> bannList;
    private List<String> feeRule = new ArrayList();
    private View herderView;
    private List<String> imgUrl = new ArrayList();
    private Intent intent;
    private Handler mHandler;
    private LayoutInflater mInflater;
    private int position = 0;
    private Read read;
    private List<RmdInfoLst> rmdinfoLst;
    private SlideShowView ssv;
    private List<String> videoUrl = new ArrayList();
private ImageView imgGRZX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolBar();
        getRead();

        getImgUrl();
        initData();
    }


    private void initToolBar() {
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayShowHomeEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
//        tv_title.setText("拇指影吧");
//        mToolbar.setNavigationIcon(R.drawable.person);
        imgGRZX.setOnClickListener(new MyOnclickListener());
    }

    class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //进入我的界面
            startActivity(new Intent(MainActivity.this, MyActivity.class));
        }
    }



    private void initView() {
        imgGRZX = (ImageView) findViewById(R.id.imgGRZX);
        //tv_title = (TextView) findViewById(R.id.tv_title);
        xListview = (XListView) findViewById(R.id.xListview);
    }


    private void getImgUrl()
    {
        for (int i = 0;i< bannList.size() ;i++)
        {

            this.imgUrl.add(((BannerInfoList)this.bannList.get(i)).getImgUrlThumbnail());
            this.videoUrl.add(((BannerInfoList)this.bannList.get(i)).getVideoUrl());
            this.feeRule.add(((BannerInfoList) bannList.get(i)).getFeeRule() + "");
        }
    }

    private void getRead()
    {
        read = new Read(getApplicationContext());
        try
        {
            rmdinfoLst = read.readAsset();
            bannList = read.readAssetBann();
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
            Log.e("TAG","读取assets出错");
        }
    }

    private void initData()
    {
        if (this.mInflater == null)
            mInflater = LayoutInflater.from(this);
       this.herderView =View.inflate(this,R.layout.header,null);
        ssv = ((SlideShowView)herderView.findViewById(R.id.slideshowView));
      //  ssv.setData(this.imgUrl, this.videoUrl, this);
       xListview.addHeaderView(this.herderView);


        adapter = new MyAdapter(this,rmdinfoLst);
        xListview.setAdapter(adapter);
    }
}
