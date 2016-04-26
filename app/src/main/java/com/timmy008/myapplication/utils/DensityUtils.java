package com.timmy008.myapplication.utils;

import android.content.Context;


/**
 * @Description : 像素工具类
 * Created by Timmy on 2016/4/26.
 */
public final class DensityUtils {

    private static final float ADD = 0.5f;

    private DensityUtils() {
    }

    /**
     * 将dip转为 pix(像素)
     *
     * @param context context
     * @param dpValue dp值
     * @return int
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + ADD);
    }

    /**
     * 将 pix(像素)转为 dip
     *
     * @param context context
     * @param pxValue dp值
     * @return int
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + ADD);
    }
}
