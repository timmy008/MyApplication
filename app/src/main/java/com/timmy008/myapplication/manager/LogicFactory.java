package com.timmy008.myapplication.manager;


import com.timmy008.myapplication.logic.basic.ILogic;

import java.util.HashMap;
import java.util.Map;

/**
 * Logic工厂类
 * Created by Timmy on 2016/4/26.
 */
public class LogicFactory {

    /**
     * Logic缓存对象
     */
    private static Map<Class<? extends ILogic>, ILogic> LOGICS_CACHE = new HashMap<Class<? extends ILogic>, ILogic>();

    /**
     * 工程类
     */
    private LogicFactory() {
    }

    /**
     * 注册Logic
     */
    public static void registerLogic(Class<? extends ILogic> logicClass, ILogic logic) {
        LOGICS_CACHE.put(logicClass, logic);
    }

    @SuppressWarnings("unchecked")
    public static <E> E getLogicByClass(Class<E> logicClass) {
        return (E) LOGICS_CACHE.get(logicClass);
    }

}
