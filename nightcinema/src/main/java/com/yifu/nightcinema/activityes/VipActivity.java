package com.yifu.nightcinema.activityes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.utils.Contants;

public class VipActivity extends Activity implements View.OnClickListener {
    private TextView btn_goPay;
    private TextView tv_dele_price;
    private ImageView member_close;
    SpannableString msp = null;

    private ImageView pay_member_bg;
    private TextView tv_tip;
    private TextView tv_price;
    private TextView tv_shuoming;

    int fee = 39;
    String showfee  = "￥39";
    String del_fee = "￥78";
    String tip = "开通永久会员";
    String shuoming = "观看试看区视频权限";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        initView();
        initData();
    }

    protected void initView() {

        btn_goPay = (TextView) findViewById(R.id.btn_goPay);
        tv_dele_price = (TextView) findViewById(R.id.tv_dele_price);
        member_close = (ImageView) findViewById(R.id.member_close);

        pay_member_bg = (ImageView) findViewById(R.id.pay_member_bg);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_shuoming = (TextView) findViewById(R.id.tv_shuoming);


        btn_goPay.setOnClickListener(this);
        member_close.setOnClickListener(this);


    }

    protected void initData() {

        switch (Contants.VipLevel) {
            case 0:
                fee = 39;
                showfee = "￥39";
                del_fee = "￥78";
                pay_member_bg.setBackgroundResource(R.drawable.p_top1);
                break;
            case 1:
                fee = 30;
                showfee = "￥30";
                del_fee = "￥60";
                shuoming = "观看成人无码高清视频权限";
                tip = "开通钻石会员";
                pay_member_bg.setBackgroundResource(R.drawable.p_top2);

                break;
            case 2:
                fee = 45;
                showfee = "￥45";
                del_fee = "￥90";
                shuoming = "全部海量高清视频永久观看权限";
                tip = "开通黑金会员";
                pay_member_bg.setBackgroundResource(R.drawable.p_top3);
                break;
            case 3:
            default:
                fee = 45;
                showfee = "￥45";
                del_fee = "￥90";
                shuoming = "观看成人无码高清视频";
                tip = "开通黑金会员";
                pay_member_bg.setBackgroundResource(R.drawable.p_top3);
                break;
        }
        msp = new SpannableString(del_fee);
        msp.setSpan(new StrikethroughSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_dele_price.setText(msp);
        tv_price.setText(showfee);
        tv_tip.setText(tip);
        tv_shuoming.setText(shuoming);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goPay:
                Intent intent = new Intent(this, PayActivity.class);
                intent.putExtra("fee", fee);
                startActivity(intent);
                finish();
                break;
            case R.id.member_close:
                finish();
                break;
            default:
                break;

        }
    }
}
