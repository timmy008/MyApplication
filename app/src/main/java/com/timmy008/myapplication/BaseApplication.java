package com.timmy008.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.timmy008.myapplication.utils.BeanUtils;

import java.util.Stack;

/**
 * Created by Timmy on 2016/4/26.
 */
public class BaseApplication extends Application {

    /**
     * Activity Stack
     */
    private Stack<Activity> mActivityStack = new Stack<Activity>();
    private static final String TAG = "CApplication";

    private static BaseApplication mInstance;

    private Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mInstance = this;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * 添加Activity至堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (!mActivityStack.contains(activity)) {
            mActivityStack.push(activity);
        }
    }

    /**
     * 删除堆栈中Activity
     *
     * @param activity
     */
    public void removeActivty(Activity activity) {
        if (mActivityStack.contains(activity)) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 判断Activity是否为当前页面
     * @param activity
     * @return
     */
    public boolean isCurrentActivity(Activity activity) {
        if (BeanUtils.isNotEmpty(mActivityStack) && activity != null) {
            return activity == mActivityStack.peek();
        }
        return false;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
