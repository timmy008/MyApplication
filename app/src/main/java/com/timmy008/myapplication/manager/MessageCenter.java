package com.timmy008.myapplication.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.os.Handler;
import android.os.Message;


/**
 * 消息中心
 * Created by Timmy on 2016/4/26.
 */
public class MessageCenter {

    private List<Handler> mHandlerList = new CopyOnWriteArrayList<Handler>();

    private volatile static MessageCenter instance;

    private MessageCenter() {
    }

    public static MessageCenter getInstance() {
        if (instance == null) {
            synchronized (MessageCenter.class) {
                if (instance == null) {
                    instance = new MessageCenter();
                }
            }
        }
        return instance;
    }

    public void addHandler(Handler handler) {
        mHandlerList.add(handler);
    }

    public void removeHandler(Handler handler) {
        mHandlerList.remove(handler);
    }

    public void sendMessage(int what, Object obj) {
        Message message = new Message();
        message.obj = obj;
        message.what = what;
        sendMessage(message);
    }

    public void sendMessage(Message message) {
        for (Handler handler : mHandlerList) {
            handler.sendMessage(Message.obtain(message));
        }
    }

    public void sendMessageDelay(Message message, long delaytime) {
        for (Handler handler : mHandlerList) {
            handler.sendMessageDelayed(Message.obtain(message), delaytime);
        }
    }

    public void sendMessageDelay(int what, Object obj, long delaytime) {
        Message message = new Message();
        message.obj = obj;
        message.what = what;
        for (Handler handler : mHandlerList) {
            handler.sendMessageDelayed(Message.obtain(message), delaytime);
        }
    }

    public void sendEmptyMesage(int what) {
        for (Handler handler : mHandlerList) {
            handler.sendEmptyMessage(what);
        }
    }

    public void sendEmptyMesageDelay(int what, long delaytime) {
        for (Handler handler : mHandlerList) {
            handler.sendEmptyMessageDelayed(what, delaytime);
        }
    }

}
