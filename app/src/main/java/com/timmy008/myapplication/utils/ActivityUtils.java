package com.timmy008.myapplication.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.util.List;


/**
 * @Description : ActivityUtils工具类
 * Created by Timmy on 2016/4/26.
 */
public final class ActivityUtils {

    // 日志标识
    private static final String TAG = "ActivityUtil";

    private ActivityUtils() {

    }

    /**
     * 获取当前程序包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        String pkgName = null;
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            pkgName = info.packageName;
        }
        if (StringUtils.isNEmpty(pkgName)) {
            pkgName = context.getPackageName();
        }
        return pkgName;
    }

    /**
     * 获取当前程序版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        int versionCode = 0;
        try {
            PackageInfo info = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取当前程序版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        String versionName = "1.0.0";
        try {
            PackageInfo info = pm.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            versionName = info.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取Application Meta数据
     *
     * @return
     */
    public static Object getApplicationMetaData(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(getPackageName(context), PackageManager.GET_META_DATA);
            return appInfo.metaData.get(key);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Activity Meta数据
     *
     * @return
     */
    public static Object getActivityMetaData(Context context, Class<?> clazz, String key) {
        try {
            ComponentName cn = new ComponentName(context, clazz);
            ActivityInfo info = context.getPackageManager().getActivityInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.get(key);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Service Meta数据
     *
     * @return
     */
    public static Object getServiceMetaData(Context context, Class<?> clazz, String key) {
        ComponentName cn = new ComponentName(context, clazz);
        try {
            ServiceInfo info = context.getPackageManager().getServiceInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.get(key);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Receiver Meta数据
     *
     * @return
     */
    public static Object getReceiverMetaData(Context context, Class<?> clazz, String key) {
        ComponentName cn = new ComponentName(context, clazz);
        try {
            ActivityInfo info = context.getPackageManager().getReceiverInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.get(key);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 隐藏键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 获取手机名
     *
     * @return
     */
    public static String getPhoneName() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        String deviceId = "";
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if (StringUtils.isNotEmpty(imei)) {
            deviceId = imei;
        } else {
            deviceId = ""; // TODO 创建随机数
        }
        return deviceId;
    }

    /**
     * 获取Wifi Ip
     *
     * @param context
     * @return
     */
    public static String getWifiIp(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    /***
     * 判断是否开启其他应用程序
     *
     * @param context context
     * @param intent  intent
     * @return boolean
     */
    public static boolean isOpenOtherIntent(Context context, Intent intent) {
        boolean isOtherApp = false;
        try {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> acitvitys = packageManager.queryIntentActivities(intent, 0);
            if (acitvitys != null && acitvitys.size() > 0) {
                String packageName = acitvitys.get(0).activityInfo.packageName;
                isOtherApp = !context.getPackageName().equals(packageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOtherApp;
    }

    /**
     * 判断应用是否是前台运行
     * 注：经测试发现，有时会出现判断失误，故目前使用isForegroundV2。
     *
     * @param context
     * @return
     */
    public static boolean isForeground(Context context) {
        try {
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName topName = manager.getRunningTasks(1).get(0).topActivity;
            String currentPackageName = topName.getPackageName();
            if (!StringUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断应用是否是前台运行
     *
     * @param context
     * @return
     */
    public static boolean isForegroundV2(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 安装APK
     *
     * @param context  上下文
     * @param filePath 文件路径
     */
    public static void installApk(Context context, String filePath) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(filePath)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 拨号
     *
     * @param context
     * @param phoneNum
     */
    public static void dial(Context context, String phoneNum) {
        Intent dial = new Intent(Intent.ACTION_DIAL);
        dial.setData(Uri.parse("tel:" + phoneNum));
        context.startActivity(dial);
    }

    /**
     * 通过类名启动Activity
     *
     * @param context
     * @param pClass
     */
    public static void openActivity(Context context, Class<?> pClass) {
        openActivity(context, pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public static void openActivity(Context context, Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(context, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过类名启动Activity，并可设置Flag参数
     *
     * @param pClass
     * @param flags
     */
    public static void openActivity(Context context, Class<?> pClass, int flags) {
        Intent intent = new Intent(context, pClass);
        intent.addFlags(flags);
        context.startActivity(intent);
    }

    /**
     * 通过类名启动Activity，并可设置Bundle和Flag参数
     *
     * @param pClass
     * @param pBundle
     * @param flags
     */
    public static void openActivity(Context context, Class<?> pClass, Bundle pBundle, int flags) {
        Intent intent = new Intent(context, pClass);
        intent.addFlags(flags);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public static void openActivityForResult(Context context, Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(context, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 通过类名启动Activity，不含有Bundle数据
     *  @param context
     * @param pClass
     * @param requestCode
     */
    public static void openActivityForResult(Context context, Class<?> pClass, int requestCode) {
        Intent intent = new Intent(context, pClass);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 获取控件
     *
     * @param container
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T getView(View container, int id) {
        T result = null;
        if (container != null) {
            result = (T) container.findViewById(id);
            if (result == null) {
                throw new IllegalArgumentException("view 0x" + Integer.toHexString(id) + " doesn't exist");
            }
        } else {
            throw new IllegalArgumentException("container must be not null!");
        }
        return result;
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    @SuppressLint("NewApi")
    public static void copy(Context context, String content) {
        if (VERSION.SDK_INT <= VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager cm = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText(content.trim());
        } else {
            android.content.ClipboardManager cm = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setText(content.trim());
        }
    }

    /**
     * 实现文本粘贴功能
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static String paste(Context context) {
        if (VERSION.SDK_INT <= VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager cm = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            return cm.getText().toString().trim();
        } else {
            android.content.ClipboardManager cm = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            return cm.getText().toString().trim();
        }
    }


}
