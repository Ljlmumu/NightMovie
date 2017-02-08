package com.yifu.nightcinema.activityes;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yifu.nightcinema.R;

/**
 * Created by lijilei on 2017/1/19.
 */
public class AgumentActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intView();
    }

    private void intView() {
        ScrollView scrollView = new ScrollView(this);
        ViewGroup.LayoutParams params =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setText(getString(R.string.user_agreement));
        scrollView.addView(textView,params);
        setContentView(scrollView);
    }
}
