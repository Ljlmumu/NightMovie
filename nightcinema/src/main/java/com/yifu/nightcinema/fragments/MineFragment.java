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
private String level ="普通会员";
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
        if (Contants.isVip){
            level = "永久会员";
            privilege = "观看海量电影";
            operation_title.setVisibility(View.INVISIBLE);
        }
        level_title.setText(level);
        privilege_title.setText(privilege);
    }

    private void initView(View view) {
        operation_title = (TextView) view.findViewById(R.id.operation_title);
        level_title = (TextView) view.findViewById(R.id.level_title);
        privilege_title = (TextView) view.findViewById(R.id.privilege_title);
        user_agreement_layout = (RelativeLayout) view.findViewById(R.id.user_agreement_layout);

        operation_title.setOnClickListener(this);
        user_agreement_layout.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.operation_title:
                startActivityForResult(new Intent(activity, VipActivity.class),1003);
                break;
            case R.id.user_agreement_layout:
                startActivity(new Intent(activity,AgumentActivity.class));
                break;
        }
    }

    @Override
    public void refreshData() {
        initData();
    }

}
