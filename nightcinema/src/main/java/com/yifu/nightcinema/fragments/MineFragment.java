package com.yifu.nightcinema.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.activityes.AgumentActivity;
import com.yifu.nightcinema.activityes.VipActivity;
import com.yifu.nightcinema.utils.Contants;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private TextView operation_title;
    private TextView level_title;
    private TextView privilege_title;
    private RelativeLayout user_agreement_layout;
    private TextView kefu_explain;
    private RelativeLayout tv_updata_vip;


    private String level = "普通会员";
    private String privilege = "观看体验区电影";

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        initData();
        return view;
    }

    public void initData() {
        switch (Contants.VipLevel) {
            case 0:
                level = "普通会员";
                privilege = "观看体验区电影";
                break;
            case 1:
                level = "永久会员";
                privilege = "观看会员区电影";
                operation_title.setText("升级钻石会员");
                tv_updata_vip.setVisibility(View.VISIBLE);
                tv_updata_vip.setOnClickListener(this);

                break;
            case 2:
                level = "钻石会员";
                privilege = "观看成人无码电影";
                operation_title.setText("升级黑金会员");
                kefu_explain.setText(getString(R.string.zuanshi_explain));
                tv_updata_vip.setVisibility(View.VISIBLE);
                tv_updata_vip.setOnClickListener(this);
                break;
            case 3:
                level = "黄金会员";
                privilege = "观看海量高清电影";
                operation_title.setText("您已经是黄金会员");
                kefu_explain.setText(getString(R.string.huangjin_explain));

                operation_title.setClickable(false);
                tv_updata_vip.setVisibility(View.GONE);
                break;


            default:
                break;

        }
        level_title.setText(level);
        privilege_title.setText(privilege);


    }

    private void initView(View view) {
        operation_title = (TextView) view.findViewById(R.id.operation_title);
        level_title = (TextView) view.findViewById(R.id.level_title);
        privilege_title = (TextView) view.findViewById(R.id.privilege_title);
        user_agreement_layout = (RelativeLayout) view.findViewById(R.id.user_agreement_layout);
        kefu_explain = (TextView) view.findViewById(R.id.kefu_explain);
        tv_updata_vip = (RelativeLayout) view.findViewById(R.id.tv_updata_vip);


        operation_title.setOnClickListener(this);
        user_agreement_layout.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.operation_title:
                startActivityForResult(new Intent(activity, VipActivity.class), 1003);
                break;
            case R.id.user_agreement_layout:
                startActivity(new Intent(activity, AgumentActivity.class));
                break;
            case R.id.tv_updata_vip:
                startActivityForResult(new Intent(activity, VipActivity.class), 1003);
                break;
        }
    }

    @Override
    public void refreshData() {
        initData();
    }

}
