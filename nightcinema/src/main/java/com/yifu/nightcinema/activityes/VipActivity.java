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

public class VipActivity extends Activity implements View.OnClickListener {
private TextView btn_goPay;
private TextView tv_dele_price;
    private ImageView member_close;

    int fee = 29;
    String del_fee = "￥78";
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
    }

    protected void initData() {

        btn_goPay.setOnClickListener(this);
        member_close.setOnClickListener(this);

        SpannableString msp = null;
        msp = new SpannableString(del_fee);
        msp.setSpan(new StrikethroughSpan(), 0,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_dele_price.setText(msp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_goPay:
                Intent intent = new Intent(this,PayActivity.class);
                intent.putExtra("fee",fee);
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
