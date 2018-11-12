package com.wangdian.mile.mvc;

import com.wangdian.mile.mvc.impl.HandlerExceptionResolverImpl;
import com.wangdian.mile.mvc.impl.HandlerExecutorImpl;
import com.wangdian.mile.mvc.impl.HandlerMappingImpl;
import com.wangdian.mile.utils.GlobalConfigUtil;
import com.wangdian.mile.utils.ObjectUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bigv on 3/28/2017.
 */
public class SingletonFactory {

    /**
     * 缓存创建的对象的实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    public static HandlerMapping getHandlerMapping() {
        return getInstance("com.mile.bigv.framework.handler.mapping", HandlerMappingImpl.class);
    }

    public static HandlerExecutor getHandlerExecutor() {
        return getInstance("com.mile.bigv.framework.handler.executor", HandlerExecutorImpl.class);
    }

    public static HandlerExceptionResolver getHandlerExceptionResolver() {
        return getInstance("com.mile.bigv.framework.handler.resolver", HandlerExceptionResolverImpl.class);
    }


    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        // 若缓存中存在对应的实例，则返回该实例
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        // 从配置文件中获取相应的接口实现类
        String implClassName = GlobalConfigUtil.getString(cacheKey);

        // 若配置不存在，则使用默认实现类
        if (implClassName == null) {
            implClassName = defaultImplClass.getName();
        }
        // 通过反射创建该实现类对应的实例
        T instance = ObjectUtil.newInstance(implClassName);

        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        // 返回该实例
        return instance;
    }
}
