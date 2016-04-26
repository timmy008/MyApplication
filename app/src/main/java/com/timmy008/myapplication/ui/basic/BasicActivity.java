package com.timmy008.myapplication.ui.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.timmy008.myapplication.BaseApplication;
import com.timmy008.myapplication.utils.ActivityUtils;

/**
 * Created by Timmy on 2016/4/26.
 */
public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAddToStack()) {
            ((BaseApplication) getApplication()).addActivity(this);
        }
    }


    /**
     * 当前是否顶级页面
     *
     * @return
     */
    protected boolean isTopActivity() {
        BaseApplication application = BaseApplication.getInstance();
        if (application != null) {
            return application.isCurrentActivity(this);
        } else {
            return false;
        }
    }


    protected <T extends View> T getView(int id) {
        T result = (T) findViewById(id);
        if (result == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id) + " doesn't exist");
        }
        return result;
    }

    protected <T extends View> T getView(View container, int id) {
        return ActivityUtils.getView(container, id);
    }

    /**
     * 通过类名启动Activity
     *
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 通过类名启动Activity，并可设置Flag参数
     *
     * @param pClass
     * @param flags
     */
    protected void openActivity(Class<?> pClass, int flags) {
        Intent intent = new Intent(this, pClass);
        intent.addFlags(flags);
        startActivity(intent);
    }

    /**
     * 通过类名启动Activity，并可设置Bundle和Flag参数
     *
     * @param pClass
     * @param pBundle
     * @param flags
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, int flags) {
        Intent intent = new Intent(this, pClass);
        intent.addFlags(flags);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    protected void openActivityForResult(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 通过类名启动Activity，不含有Bundle数据
     *
     * @param pClass
     * @param requestCode
     */
    protected void openActivityForResult(Class<?> pClass, int requestCode) {
        Intent intent = new Intent(this, pClass);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 通过Action启动Activity
     *
     * @param pAction
     */
    protected void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    /**
     * 通过Action启动Activity，并且含有Bundle数据
     *
     * @param pAction
     * @param pBundle
     */
    protected void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }


    /**
     * 是否添加至堆栈
     *
     * @return
     */
    protected boolean isAddToStack() {
        return true;
    }

    @Override
    public void finish() {
        if (isAddToStack()) {
            ((BaseApplication) getApplication()).removeActivty(this);
        }
        super.finish();
    }
}
