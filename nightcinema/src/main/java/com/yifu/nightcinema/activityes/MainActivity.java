package com.yifu.nightcinema.activityes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yifu.nightcinema.R;
import com.yifu.nightcinema.fragments.AdultFragment;
import com.yifu.nightcinema.fragments.BaseFragment;
import com.yifu.nightcinema.fragments.MineFragment;
import com.yifu.nightcinema.fragments.TryFragment;
import com.yifu.nightcinema.fragments.WumaFragment;
import com.yifu.nightcinema.utils.Contants;

public class MainActivity extends BaseActivity implements BaseFragment.OnFragmentInteractionListener {
    private final String TAG = "MainActivity";
    private Toolbar mToolbar;
    private TextView tv_title;
    private FrameLayout fl_Contains;
    private BaseFragment mAdultFragment, mMineFragment, mTryFragment, mWumaFragment;
    private RadioGroup rg_main;
    private RadioButton rb_try,rb_adult,rb_wuma,rb_mine;
    String[] tags = new String[]{"try","adult","wuma","mine"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitle();
        initView(savedInstanceState);
        initData();
        Log.d(TAG, "Oncreate");
    }


    @Override
    protected void initTitle() {
        super.initTitle();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_title = (TextView) findViewById(R.id.tv_title);
        setTitle("");
        setTitleName("体验区");
        setSupportActionBar(mToolbar);
    }

    public void setTitleName(String name) {
        tv_title.setText(name);
    }


    protected void initView(Bundle savedInstanceState) {
        fl_Contains = (FrameLayout) findViewById(R.id.fl_Contains);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        rb_try = (RadioButton) findViewById(R.id.rb_try);
        rb_adult = (RadioButton) findViewById(R.id.rb_adult);
        rb_wuma = (RadioButton) findViewById(R.id.rb_wuma);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        setRadioDrawable();


        rb_try.setChecked(true);
        stateCheck(savedInstanceState);
        rg_main.setOnCheckedChangeListener(new MyOnRadioChangeListener());

    }

    private void setRadioDrawable() {
        if(Contants.VipLevel>0){
            rb_try.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.rb_gold,0,0);
            rb_adult.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.rb_zuanshi,0,0);
            rb_wuma.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.rb_vr,0,0);
        }
    }


    private void stateCheck(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            showFragment();
        } else {
            mTryFragment = (TryFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[0]);
            mAdultFragment = (AdultFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[1]);
            mWumaFragment = (WumaFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[2]);
            mMineFragment = (MineFragment) getSupportFragmentManager()
                    .findFragmentByTag(tags[3]);


            getSupportFragmentManager().beginTransaction().show(mTryFragment).hide(mAdultFragment).hide(mWumaFragment)
                    .hide(mMineFragment).commit();
        }
    }

    private void showFragment() {
        FragmentManager  manager = getSupportFragmentManager();
        FragmentTransaction fts = manager.beginTransaction();

        mTryFragment  = new TryFragment();
        mAdultFragment = new AdultFragment();
        mWumaFragment = new WumaFragment();
        mMineFragment = new MineFragment();

        fts.add(R.id.fl_Contains, mTryFragment,tags[0]).add(R.id.fl_Contains,mAdultFragment,tags[1]).add(R.id.fl_Contains,mWumaFragment,tags[2]).add(R.id.fl_Contains,mMineFragment,tags[3]);
        fts.show(mTryFragment).hide(mAdultFragment).hide(mWumaFragment)
                .hide(mMineFragment);
        fts.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    class MyOnRadioChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.d(TAG,"checkedId="+checkedId);
            switch (checkedId) {
                case R.id.rb_try:
                    Log.e(TAG, "mTryFragment");
                    setTitleName("体验区");
                    if(Contants.VipLevel>0){
                        setTitleName("黄金区");
                    }
                    getSupportFragmentManager().beginTransaction().show(mTryFragment).hide(mAdultFragment).hide(mWumaFragment)
                            .hide(mMineFragment).commit();
                    break;
                case R.id.rb_adult:
                    Log.e(TAG, "mAdultFragment");
                    if (Contants.VipLevel < 2) {
                        startActivity(new Intent(MainActivity.this, VipActivity.class));
                        rb_try.setChecked(true);
                        return;
                    }

                    setTitleName("成人区");
                    if(Contants.VipLevel>0){
                        setTitleName("钻石区");
                    }
                    getSupportFragmentManager().beginTransaction().show(mAdultFragment).hide(mTryFragment).hide(mWumaFragment)
                            .hide(mMineFragment).commit();
                    break;
                case R.id.rb_wuma:
                    if (Contants.VipLevel < 2) {
                        startActivity(new Intent(MainActivity.this, VipActivity.class));
                        rb_try.setChecked(true);
                        return;
                    }
                    setTitleName("无码区");
                    if(Contants.VipLevel>0){
                        setTitleName("VR区");
                    }
                    getSupportFragmentManager().beginTransaction().show(mWumaFragment).hide(mTryFragment).hide(mAdultFragment)
                            .hide(mMineFragment).commit();
                    break;
                case R.id.rb_mine:
                    Log.e(TAG, "mWumaFragment");
                    setTitleName("我的");

                    getSupportFragmentManager().beginTransaction().show(mMineFragment).hide(mAdultFragment).hide(mWumaFragment)
                            .hide(mTryFragment).commit();
                    break;
                default:
                    break;
            }


        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK ){
           // startActivity(new Intent(this,ExitActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode,  event);

    }



    @Override
    protected void onRestart() {
        super.onRestart();

    }

    /**
     * 刷新页面
     */
    @Override
    protected void onResume() {
        super.onResume();
        setRadioDrawable();
        FragmentManager  manager = getSupportFragmentManager();
        for(int i=0;i<tags.length;i++){
            BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tags[i]);
            if(fragment==null)continue;
            fragment.refreshData();
        }

    }
}
