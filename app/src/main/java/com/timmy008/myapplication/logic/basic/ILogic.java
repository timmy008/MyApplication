package com.timmy008.myapplication.logic.basic;

import android.os.Handler;


/**
 * 业务逻辑的统一接口定义
 * Created by Timmy on 2016/4/26.
 */
public interface ILogic {

    /**
     * 添加通讯用的Handlers
     */
    public void addHandler(Handler handler);

    /**
     * 移除通讯用的Handlers
     */
    public void romoveHander(Handler handler);

    /**
     * 执行异步操作
     */
    public void invokeAsync(Runnable runnable);

    /**
     * 取消请求
     */
    public void cancelRequest(String id);
}
