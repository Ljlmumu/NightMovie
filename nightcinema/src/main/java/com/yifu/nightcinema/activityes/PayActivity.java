package com.yifu.nightcinema.activityes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tendcloud.tenddata.TDGAVirtualCurrency;
import com.yifu.nightcinema.R;
import com.yifu.nightcinema.utils.Contants;
import com.yifu.nightcinema.utils.SpUtil;
import com.yifu.platform.single.PayType;
import com.yifu.platform.single.YFPlatform;
import com.yifu.platform.single.callback.IYFSDKCallBack;
import com.yifu.platform.single.item.GamePropsInfo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PayActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView ib_vip_exit;
    private TextView tv_pay_money;
    private RadioButton rb_weixin;
    private RadioButton rb_zhjifubao;
    private Button pay_btn;

    private int payType = 0;//0weixin ,1alipay
    double fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        initData();

    }

    private void initData() {
        fee = getIntent().getIntExtra("fee", 0);

        tv_pay_money.setText(fee + "元");
    }

    private void initView() {
        ib_vip_exit = (ImageView) findViewById(R.id.ib_vip_exit);
        tv_pay_money = (TextView) findViewById(R.id.tv_pay_money);
        rb_weixin = (RadioButton) findViewById(R.id.rb_weixin);
        rb_zhjifubao = (RadioButton) findViewById(R.id.rb_zhjifubao);
        pay_btn = (Button) findViewById(R.id.pay_btn);
        rb_weixin.setChecked(true);
        ib_vip_exit.setOnClickListener(this);
        pay_btn.setOnClickListener(this);

        rb_weixin.setOnCheckedChangeListener(this);
        rb_zhjifubao.setOnCheckedChangeListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_vip_exit:
                finish();
                break;
            case R.id.pay_btn:
                starPay();
                break;
        }
    }

    private void starPay() {
        Date date = new Date(System.currentTimeMillis());
        String orderId = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss").format(date);
        String orderName = getString(R.string.pay_title);

        if (payType == 0) {//微信
            TDGAVirtualCurrency.onChargeRequest(orderId, "永久会员", fee, "CNY", fee, "微信");

            GamePropsInfo gamePropsInfo = new GamePropsInfo(fee + "", orderName, orderId);
            YFPlatform.getInstance().invokePay(this, gamePropsInfo,
                    PayType.TENCENTMM, new MyCallback(orderId));


        } else if (payType == 1) {//支付宝
            TDGAVirtualCurrency.onChargeRequest(orderId, "永久会员", fee, "CNY", fee, "支付宝");
            GamePropsInfo gamePropsInfo = new GamePropsInfo(fee + "", orderName, orderId);
            YFPlatform.getInstance().invokePay(this, gamePropsInfo,
                    PayType.ALIPAY, new MyCallback(orderId));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_weixin:
                if (isChecked) {
                    payType = 0;
                    rb_zhjifubao.setChecked(false);
                    //rb_weixin.setChecked(true);

                }

                break;
            case R.id.rb_zhjifubao:
                if (isChecked) {
                    payType = 1;
                    rb_weixin.setChecked(false);
                    //rb_zhjifubao.setChecked(true);
                }
                break;
        }
    }


    public void paySuccess() {
        Contants.VipLevel++;
        if(Contants.VipLevel>3){
            Contants.VipLevel= 0;
        }
        SpUtil.updateInt(this, SpUtil.VIP_LEVEL, Contants.VipLevel);

    }

    /**
     * 支付处理过程的结果回调函数
     */


    class MyCallback implements IYFSDKCallBack {
        private String orderId_back;

        public MyCallback() {
        }

        public MyCallback(String orderId_back) {
            this.orderId_back = orderId_back;
        }

        @Override
        public void onResponse(int code, String paramString) {
            if (code == 3) {//3成功
                paySuccess();

                Log.i("PayActivity", "success-orderID=" + orderId_back);
                TDGAVirtualCurrency.onChargeSuccess(orderId_back);
                Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT)
                        .show();
            } else {
               // paySuccess();
                Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT)
                        .show();

            }

        }
    }

    ;

}
