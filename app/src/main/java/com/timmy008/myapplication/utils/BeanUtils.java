package com.timmy008.myapplication.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;

import java.io.Closeable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 * @Description :通用的对象操作帮助类
 * Created by Timmy on 2016/4/26.
 */
public class BeanUtils {

    /**
     * 判断对象不为null或为空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isNEmpty(obj);
    }

    /**
     * 判断对象为null或为空
     */
    public static boolean isNEmpty(Object obj) {
        return obj == null ? true : isEmpty(obj);
    }

    /**
     * 判断字符串为空，集合为空，数组为空(后续可以拓展hashSet,hashMap ...)
     */
    public static boolean isEmpty(Object obj) {
        boolean isEmpty = true;
        if (obj != null) {
            if (obj instanceof String) {
                // 字符串
                String tmp = obj.toString();
                isEmpty = tmp.trim().equals("");
            } else if (obj instanceof Collection<?>) {
                // 集合
                Collection<?> collections = (Collection<?>) obj;
                isEmpty = collections.size() == 0;
            } else if (obj instanceof Map<?, ?>) {
                // Map
                Map<?, ?> map = (Map<?, ?>) obj;
                isEmpty = map.size() == 0;
            } else if (obj instanceof Vector) {
                // Vector
                Vector<?> vector = (Vector<?>) obj;
                isEmpty = vector.size() == 0;
            } else if (obj instanceof Object[]) {
                // 数组
                Object[] objarray = (Object[]) obj;
                isEmpty = objarray.length == 0;
            } else {
                // 其它类型对象统一为非空
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    /**
     * 判断当前索引是否存在
     *
     * @param list
     * @param position
     * @return
     */
    public static boolean containIndex(List<?> list, int position) {
        return BeanUtils.isNotEmpty(list) && position < list.size() && position >= 0;
    }

    /**
     * 关闭所有需要关闭的资源
     */
    public static void close(Object closeObj) {
        if (closeObj != null) {
            try {
                if (closeObj instanceof Closeable) {
                    // IO 操作的流对象
                    ((Closeable) closeObj).close();
                } else if (closeObj instanceof Cursor) {
                    // 关闭Cursor
                    ((Cursor) closeObj).close();
                } else if (closeObj instanceof SQLiteClosable) {
                    // 关闭SQLite数据库相关引用
                    ((SQLiteClosable) closeObj).releaseReference();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
