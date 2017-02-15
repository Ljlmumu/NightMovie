package com.yifu.nightcinema.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.activityes.DetailActivity;
import com.yifu.nightcinema.activityes.VipActivity;
import com.yifu.nightcinema.adapter.GridviewAdapter;
import com.yifu.nightcinema.bean.BaseBean;
import com.yifu.nightcinema.bean.ListBean;
import com.yifu.nightcinema.bean.VideoInfo;
import com.yifu.nightcinema.net.VolleyUtil;
import com.yifu.nightcinema.utils.Contants;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class WumaFragment extends BaseFragment {


    private OnFragmentInteractionListener mListener;
    private GridView frg_gl_wuma;
    public List<VideoInfo> listGradView = new ArrayList<VideoInfo>();

    private int page = 0;
    private GridviewAdapter mGridViewAdapter;
    private int totalPage = 10;
    private String url;

    private int vipLevel;

    public WumaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGridViewAdapter = new GridviewAdapter(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wuma, container, false);
        frg_gl_wuma = (GridView) view.findViewById(R.id.frg_gl_wuma);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        frg_gl_wuma.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == listGradView.size() - frg_gl_wuma.getNumColumns() + 1) {
                            Log.i("TAG", page + "/" + totalPage);

                            initData();

                        }
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
//设置条目点击的监听
        frg_gl_wuma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (Contants.VipLevel < 2) {
                    startVip();

                }else{
                    startDetail(listGradView.get(position));
                }


            }
        });
    }
    private void startVip() {
        startActivity(new Intent(activity, VipActivity.class));
    }
    /**
     * 打开详情界面
     * @param
     */

    private void startDetail(VideoInfo info) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("VideoInfo", info);
        startActivity(intent);

    }

    /**
     * 刷新数据
     */
    @Override
    public void refreshData() {
        super.refreshData();
        if (vipLevel != Contants.VipLevel) {
            page = 0;
            initData();

        }
    }

    private void initData() {


        vipLevel = Contants.VipLevel;
        if (Contants.VipLevel < 1) {

            url = Contants.url_wuma;
        } else {
            url = Contants.url_vr;
        }
        if (page >= totalPage) {

            return;
        }


         // 请求数据

        VolleyUtil.getInstance().getBean(url + (page + 1), new VolleyUtil.OnNetListener<BaseBean>() {


            @Override
            public void onSuccess(BaseBean baseBean) {
                ListBean listBean = (ListBean) baseBean;
                totalPage = listBean.getTotalPage();
                page = listBean.getPage();
                if (listBean.getList().size() != 0) {
                    listGradView.addAll(listBean.getList());
                    Log.i(TAG, listGradView.size() + "");
                    mGridViewAdapter.setData(listGradView);
                    if (page > 1) {
                        mGridViewAdapter.notifyDataSetChanged();
                    } else {
                        frg_gl_wuma.setAdapter(mGridViewAdapter);
                    }
                }


            }

            @Override
            public void onFail() {
                Log.e(TAG, "网络请求失败!");
                Toast.makeText(activity, "网络请求失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 没用到
     * @param uri
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


}
