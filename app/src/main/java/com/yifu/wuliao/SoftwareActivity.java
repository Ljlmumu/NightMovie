package com.yifu.wuliao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SoftwareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);
        setViews();
        setListeners();
    }

    private ImageView close;
    private ImageView imgView;
    private ImageView imgView2;

    private void setListeners()
    {
       close.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                SoftwareActivity.this.finish();
            }
        });
        imgView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                SoftwareActivity.this.imgView.setVisibility(View.GONE);
                SoftwareActivity.this.imgView2.setVisibility(View.VISIBLE);
            }
        });
        this.imgView2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                SoftwareActivity.this.imgView2.setVisibility(View.GONE);
                SoftwareActivity.this.imgView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setViews()
    {
        this.close = ((ImageView)findViewById(R.id.close));
        this.imgView = ((ImageView)findViewById(R.id.on));
        this.imgView2 = ((ImageView)findViewById(R.id.off));
    }
}
