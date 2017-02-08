package com.yifu.nightcinema.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.Toast;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.adapter.GridviewAdapter;
import com.yifu.nightcinema.bean.BaseBean;
import com.yifu.nightcinema.bean.ListBean;
import com.yifu.nightcinema.bean.VideoInfo;
import com.yifu.nightcinema.utils.Contants;
import com.yifu.nightcinema.net.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class AdultFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match

    private OnFragmentInteractionListener mListener;
    private GridView frg_gl_adult;
    public List<VideoInfo> listGradView = new ArrayList<VideoInfo>();
    private int page = 0;
    private GridviewAdapter mGridViewAdapter;
    private int totalPage=10;

    public AdultFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGridViewAdapter = new GridviewAdapter(activity);
    }

    public void initData() {
        if (page >= totalPage) {

            return;
        }

        VolleyUtil.getInstance().getBean(Contants.url_adult + (page+1), new VolleyUtil.OnNetListener<BaseBean>() {

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
                        frg_gl_adult.setAdapter(mGridViewAdapter);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adult, container, false);
        frg_gl_adult = (GridView) view.findViewById(R.id.frg_gl_adult);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        frg_gl_adult.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == listGradView.size() - frg_gl_adult.getNumColumns() + 1) {
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
    }


}
