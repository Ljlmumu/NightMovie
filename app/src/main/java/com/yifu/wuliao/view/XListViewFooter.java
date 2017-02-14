package com.yifu.wuliao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yifu.wuliao.R;



public class XListViewFooter extends LinearLayout
{
    public static final int STATE_LOADING = 2;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    private View mContentView;
    private Context mContext;
    private TextView mHintView;
    private View mProgressBar;

    public XListViewFooter(Context paramContext)
    {
        super(paramContext);
        initView(paramContext);
    }

    public XListViewFooter(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        initView(paramContext);
    }

    private void initView(Context paramContext)
    {
        this.mContext = paramContext;
        LinearLayout localLinearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.xlistview_footer, null);
        addView(localLinearLayout);
        localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.mContentView = localLinearLayout.findViewById(R.id.xlistview_footer_content);
        this.mProgressBar = localLinearLayout.findViewById(R.id.xlistview_footer_progressbar);
        this.mHintView = ((TextView)localLinearLayout.findViewById(R.id.xlistview_footer_hint_textview));
    }

    public int getBottomMargin()
    {
        return ((LinearLayout.LayoutParams)this.mContentView.getLayoutParams()).bottomMargin;
    }

    public void hide()
    {
        LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
        localLayoutParams.height = 0;
        this.mContentView.setLayoutParams(localLayoutParams);
    }

    public void loading()
    {
        this.mHintView.setVisibility(GONE);
        this.mProgressBar.setVisibility(VISIBLE);
    }

    public void normal()
    {
        this.mHintView.setVisibility(VISIBLE);
        this.mProgressBar.setVisibility(GONE);
    }

    public void setBottomMargin(int paramInt)
    {
        if (paramInt < 0)
            return;
        LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
        localLayoutParams.bottomMargin = paramInt;
        this.mContentView.setLayoutParams(localLayoutParams);
    }

    public void setState(int paramInt)
    {
        this.mHintView.setVisibility(INVISIBLE);
        this.mProgressBar.setVisibility(INVISIBLE);
        this.mHintView.setVisibility(INVISIBLE);
        if (paramInt == 1)
        {
            this.mHintView.setVisibility(VISIBLE);
            this.mHintView.setText("下拉刷新");
            return;
        }
        if (paramInt == 2)
        {
            this.mProgressBar.setVisibility(VISIBLE);
            return;
        }
        this.mHintView.setVisibility(VISIBLE);
        this.mHintView.setText("刷新完成");
    }

    public void show()
    {
        LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
        localLayoutParams.height = -2;
        this.mContentView.setLayoutParams(localLayoutParams);
    }
}