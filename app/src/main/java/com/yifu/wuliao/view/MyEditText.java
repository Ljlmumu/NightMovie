package com.yifu.wuliao.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by tf on 2016/8/5.
 */
public class MyEditText extends EditText{

    private static final String TAG = "UnderlineEditText";
    private static Paint mPaint;
    private float add = 2.0F;
    private Rect mRect;
    private float mult = 1.25F;

    public MyEditText(Context paramContext)
    {
        super(paramContext);
        init();
    }

    public MyEditText(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        init();
    }

    private void init()
    {
        this.mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        setLineSpacing(this.add, this.mult);
    }

    public static void setBlack()
    {
        mPaint.setColor(Color.BLACK);
    }

    public static void setWhite()
    {
        mPaint.setColor(-1);
    }

    public void onDraw(Canvas paramCanvas)
    {
        Log.d("UnderlineEditText", "func [onDraw]");
        int i = getLineCount();
        for (int j = 0; ; j++)
        {
            if (j >= i)
            {
                super.onDraw(paramCanvas);
                return;
            }
            getLineBounds(j, this.mRect);
            int k = (j + 1) * getLineHeight();
            paramCanvas.drawLine(this.mRect.left, k, this.mRect.right, k, mPaint);
        }
    }
}
