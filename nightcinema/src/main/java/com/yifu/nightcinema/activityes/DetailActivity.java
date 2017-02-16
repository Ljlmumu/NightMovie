package com.yifu.nightcinema.activityes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.adapter.CommentListAdapter;
import com.yifu.nightcinema.adapter.DetailGridviewAdapter;
import com.yifu.nightcinema.adapter.GridviewAdapter;
import com.yifu.nightcinema.bean.BaseBean;
import com.yifu.nightcinema.bean.Comment;
import com.yifu.nightcinema.bean.DetailBean;
import com.yifu.nightcinema.bean.VideoInfo;
import com.yifu.nightcinema.net.VolleyUtil;
import com.yifu.nightcinema.utils.Contants;
import com.yifu.nightcinema.view.ScrollGridView;
import com.yifu.nightcinema.view.ScrollListView;

import java.util.List;

public class DetailActivity extends BaseActivity implements View.OnClickListener {
    String TAG = "DetailActivity";
    private TextView titlebar_tv_comment_name;//标题
    private ImageView comment_iv_playvideo;//视频图片
    private ImageView com_iv_send;//评论提交按钮
    private ScrollGridView mGridView;
    private ScrollListView mListView;
    private GridviewAdapter mGridviewAdapter;
    private CommentListAdapter mCommentListAdapter;
    private String dialogMessage = "只有开通会员才能提交评论哦";
    private VideoInfo videoInfo;

    private List<VideoInfo> topVideos;
    private List<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        videoInfo = (VideoInfo) getIntent().getSerializableExtra("VideoInfo");
        initView();
        initData();
    }

    @Override
    protected void initData() {

        if (videoInfo == null) {
            Log.e(TAG, "得到的videoInfo为null");
            return;
        }
        VolleyUtil.getInstance().showImage(videoInfo.getPic(), comment_iv_playvideo);
        VolleyUtil.getInstance().getBean(Contants.TAG_DETAIL, Contants.url_detail + videoInfo.getDianying_id(), new VolleyUtil.OnNetListener<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                DetailBean detailBean = (DetailBean) baseBean;
                topVideos = detailBean.getTopvideo();
                comments = detailBean.getComments();
                setData();
            }

            @Override
            public void onFail() {
                Toast.makeText(DetailActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setData() {
        titlebar_tv_comment_name.setText(videoInfo.getTitle());

        mGridviewAdapter = new DetailGridviewAdapter(this, topVideos);
        mCommentListAdapter = new CommentListAdapter(this, comments);

        mGridView.setAdapter(mGridviewAdapter);
        mListView.setAdapter(mCommentListAdapter);
    }

    @Override
    protected void initView() {

        findView();
        comment_iv_playvideo.setOnClickListener(this);
        com_iv_send.setOnClickListener(this);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VideoInfo info = topVideos.get(position);
                videoInfo = info;
                initData();
            }
        });
    }

    private void findView() {

        titlebar_tv_comment_name = (TextView) findViewById(R.id.titlebar_tv_comment_name);
        comment_iv_playvideo = (ImageView) findViewById(R.id.comment_iv_playvideo);
        com_iv_send = (ImageView) findViewById(R.id.com_iv_send);
        mGridView = (ScrollGridView) findViewById(R.id.com_gv1);
        mListView = (ScrollListView) findViewById(R.id.com_gv2);

        //监听


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_tv_comment_name:

                finish();
                break;
            case R.id.com_iv_send:
                showPayDialog();
                break;
            case R.id.comment_iv_playvideo:
                if(Contants.VipLevel>=3){
                    startPlayer();
                }else {
                    startPay();
                }
                break;
        }
    }

    private void startPlayer() {
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra("url",videoInfo.getVideo());
        intent.putExtra("title",videoInfo.getTitle());
        startActivity(intent);

    }

    private void showPayDialog() {
        Dialog dialog = new AlertDialog.Builder(this).setMessage(dialogMessage)
                .setTitle("温馨提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startPay();
                    }
                }).show();
    }

    private void startPay() {
        Intent intent = new Intent(this, VipActivity.class);
        startActivity(intent);

    }
}
