package com.yifu.wuliao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyActivity extends AppCompatActivity {


    private ImageView close;
    private Intent intent;
    private RelativeLayout rLayBFJL;
    private RelativeLayout rLayGYWM;
    private RelativeLayout rLayRJSZ;
    private RelativeLayout rLayTSRX;
    private RelativeLayout rLayYHXY;
    private RelativeLayout rLayYJFK;

    private void setViews() {
        this.close = ((ImageView) findViewById(R.id.close));
        this.close.setOnClickListener(new ClickEvent());
        this.rLayTSRX = ((RelativeLayout) findViewById(R.id.rLayTSRX));
        this.rLayTSRX.setOnClickListener(new ClickEvent());
        this.rLayBFJL = ((RelativeLayout) findViewById(R.id.rLayBFJL));
        this.rLayBFJL.setOnClickListener(new ClickEvent());
        this.rLayRJSZ = ((RelativeLayout) findViewById(R.id.rLayRJSZ));
        this.rLayRJSZ.setOnClickListener(new ClickEvent());
        this.rLayYJFK = ((RelativeLayout) findViewById(R.id.rLayYJFK));
        this.rLayYJFK.setOnClickListener(new ClickEvent());
        this.rLayYHXY = ((RelativeLayout) findViewById(R.id.rLayYHXY));
        this.rLayYHXY.setOnClickListener(new ClickEvent());
        this.rLayGYWM = ((RelativeLayout) findViewById(R.id.rLayGYWM));
        this.rLayGYWM.setOnClickListener(new ClickEvent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setViews();
    }

    private class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            intent = new Intent();
            if (v == MyActivity.this.rLayTSRX) {

                AlertDialog.Builder localBuilder = new AlertDialog.Builder(MyActivity.this);
                localBuilder.setTitle("投诉热线");
                localBuilder.setMessage("拨打投诉电话:"+getString(R.string.phone_number));
                localBuilder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:4000515869"));
                        MyActivity.this.startActivity(localIntent);
                    }
                });
                localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    }
                });
                localBuilder.create().show();
                return;
            }

            if (v == rLayBFJL) {
               // MyActivity.this.intent.addFlags(268435456);
                MyActivity.this.intent.setClass(MyActivity.this, RecordActivity.class);
                MyActivity.this.startActivity(MyActivity.this.intent);
                return;
            }
            if (v == MyActivity.this.rLayRJSZ) {
              //  MyActivity.this.intent.addFlags(268435456);
                MyActivity.this.intent.setClass(MyActivity.this, SoftwareActivity.class);
                MyActivity.this.startActivity(MyActivity.this.intent);
                return;
            }
            if (v == MyActivity.this.rLayYJFK) {
              //  MyActivity.this.intent.addFlags(268435456);
                MyActivity.this.intent.setClass(MyActivity.this, FeedbackActivity.class);
                MyActivity.this.startActivity(MyActivity.this.intent);
                return;
            }
            if (v == MyActivity.this.rLayYHXY) {
                MyActivity.this.intent.addFlags(268435456);
                MyActivity.this.intent.setClass(MyActivity.this, AgreementActivity.class);
                MyActivity.this.startActivity(MyActivity.this.intent);
                return;
            }
            if (v == MyActivity.this.rLayGYWM) {
              //  MyActivity.this.intent.addFlags(268435456);
                MyActivity.this.intent.setClass(MyActivity.this, AboutUsActivity.class);
                MyActivity.this.startActivity(MyActivity.this.intent);
                return;
            }
            if(v ==MyActivity.this.close);
            {
                MyActivity.this.finish();
                return;
            }
        }


    }

}

