package com.timmy008.myapplication.logic.basic;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.timmy008.myapplication.manager.MessageCenter;


/**
 * 所有Logic的基类
 * Created by Timmy on 2016/4/26.
 */
public abstract class BaseLogic implements ILogic {

    protected Context mcontext;
    protected final String TAG = this.getClass().getName();

    public BaseLogic(Context context) {
        super();
        this.mcontext = context;
    }

    @Override
    public final void addHandler(Handler handler) {
        MessageCenter.getInstance().addHandler(handler);
    }

    @Override
    public final void romoveHander(Handler handler) {
        MessageCenter.getInstance().removeHandler(handler);
    }

    @Override
    public final void invokeAsync(Runnable runnable) {
        //	ExecutorFactory.executeLogic(runnable);
    }

    public void sendMessage(int what, Object obj) {
        MessageCenter.getInstance().sendMessage(what, obj);
    }

    public void sendMessage(Message message) {
        MessageCenter.getInstance().sendMessage(message);
    }

    public void sendEmptyMesage(int what) {
        MessageCenter.getInstance().sendEmptyMesage(what);
    }
}
