package com.yifu.nightcinema.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.activityes.PlayerActivity;
import com.yifu.nightcinema.activityes.VipActivity;
import com.yifu.nightcinema.adapter.GridviewAdapter;
import com.yifu.nightcinema.bean.BaseBean;
import com.yifu.nightcinema.bean.ListBean;
import com.yifu.nightcinema.bean.VideoInfo;
import com.yifu.nightcinema.net.VolleyUtil;
import com.yifu.nightcinema.utils.Contants;
import com.yifu.nightcinema.utils.ViewFactory;
import com.yifu.nightcinema.view.CycleViewPager;
import com.yifu.nightcinema.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

public class TryFragment extends BaseFragment {
    public final String TAG = this.getClass().getSimpleName();
    private OnFragmentInteractionListener mListener;
    private ScrollGridView frg_gl_try;
    private List<VideoInfo> listGradView;
    private List<VideoInfo> listBanner;
    private CycleViewPager cycleViewPager;
    private List<ImageView> views = new ArrayList<ImageView>();

    public TryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
    }

    private void initData() {
//        String url = "http://php7.qyjuju.com/json2/visitor1.php?pay_Id=dxabwj&package=com.usye.asjye&appid=4";
        VolleyUtil.getInstance().getBean(Contants.url_try, new VolleyUtil.OnNetListener<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                ListBean listBean = (ListBean) baseBean;
                if (listBean.getList().size() != 0 || listBean.getBaners().size() != 0) {
                    listGradView = listBean.getList();
                    listBanner = listBean.getBaners();
                    Log.i(TAG, listGradView.size() + "");
                    frg_gl_try.setAdapter(new GridviewAdapter(activity, listGradView));
                    initialize();
                } else {
                    Toast.makeText(activity, "获取数据失败！", Toast.LENGTH_SHORT).show();
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
        View view = inflater.inflate(R.layout.fragment_ttr, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        frg_gl_try = (ScrollGridView) v.findViewById(R.id.frg_gl_try);
        frg_gl_try.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity,PlayerActivity.class);
                intent.putExtra("url",listGradView.get(position).getVideo());
                intent.putExtra("title",listGradView.get(position).getTitle());
                startActivity(intent);
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initialize() {

        cycleViewPager = (CycleViewPager) getChildFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(activity, listBanner.get(listBanner.size() - 1).getPic()));
        for (int i = 0; i < listBanner.size(); i++) {
            views.add(ViewFactory.getImageView(activity, listBanner.get(i).getPic()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(activity, listBanner.get(0).getPic()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, listBanner, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(VideoInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {

                if (Contants.VipLevel < 1) {
                    startActivity(new Intent(activity, VipActivity.class));

                }else{
                    Intent intent = new Intent(activity, PlayerActivity.class);
                    intent.putExtra("url", info.getVideo());
                    intent.putExtra("title", info.getTitle());
                    activity.startActivity(intent);
                }
            }

        }

    };


}
