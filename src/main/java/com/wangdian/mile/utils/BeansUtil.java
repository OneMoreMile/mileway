package com.wangdian.mile.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 保存系统加载的Class和已经实例化该class的实例对象的映射
 * 这样下次就不需要再次重复创建，以及保证反射执行时可以找到正确的对象.
 * Created by bigv on 3/20/2017.
 */
public final class BeansUtil {

    private BeansUtil(){}

    private static Map<Class<?>, Object> objectMap = new ConcurrentHashMap<>();

    public static void addObjectMap(Class cls, Object obj){
        objectMap.putIfAbsent(cls, obj);
    }

    public static Object getObjectMapValue(Class cls){
        return objectMap.get(cls);
    }

    public static void updateObjectMap(Class cls, Object newObj){
        objectMap.put(cls, newObj);
    }
}
