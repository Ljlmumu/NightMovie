package com.yifu.nightcinema.activityes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifu.nightcinema.R;

/**
 * Created by lijilei on 2017/1/19.
 */

public class ExitActivity extends Activity implements View.OnClickListener {
private ImageView exit_iv_exit;
    private TextView exit_money;
    private ImageView exit_activity_pay_button;
    private int fee = 19;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_actvivity);
        initView();
    }

    private void initView() {
        exit_iv_exit = (ImageView) findViewById(R.id.exit_iv_exit);
        exit_activity_pay_button = (ImageView) findViewById(R.id.exit_activity_pay_button);
        exit_money = (TextView) findViewById(R.id.exit_money);
        exit_money.setText(fee+"元");
        exit_iv_exit.setOnClickListener(this);
        exit_activity_pay_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.exit_iv_exit:
                finish();
                break;
            case  R.id.exit_activity_pay_button:
                Intent intent = new Intent(this,PayActivity.class);
                intent.putExtra("fee",fee);
                startActivity(intent);
                break;
        }
    }
}
