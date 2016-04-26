package com.timmy008.myapplication.ui.basic.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 顶部导航栏
 * Created by Timmy on 2016/4/26.
 */
public class ToolBarView extends LinearLayout{
    public ToolBarView(Context context) {
        super(context);
    }

    public ToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ToolBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
