package com.wangdian.mile.mvc.impl;

import com.wangdian.mile.annotation.Request;
import com.wangdian.mile.mvc.Handler;
import com.wangdian.mile.mvc.upload.Requestor;
import com.wangdian.mile.utils.StringUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bigv on 3/28/2017.
 */
public class ActionHelper {
    /**
     * Action Map（HTTP 请求与 Action 方法的映射）
     */
    private static final Map<Requestor, Handler> actionMap = new LinkedHashMap<Requestor, Handler>();

    static {
        // 获取所有 Action 类
        List<Class<?>> actionClassList = null; //ClassUtil.getClassListByAnnotation(Action.class);
        if (actionClassList != null && !actionClassList.isEmpty()) {
            // 定义两个 Action Map
            Map<Requestor, Handler> commonActionMap = new HashMap<Requestor, Handler>(); // 存放普通 Action Map
            Map<Requestor, Handler> regexpActionMap = new HashMap<Requestor, Handler>(); // 存放带有正则表达式的 Action Map
            // 遍历 Action 类
            for (Class<?> actionClass : actionClassList) {
                // 获取并遍历该 Action 类中所有的方法
                Method[] actionMethods = actionClass.getDeclaredMethods();
                if (actionMethods != null) {
                    for (Method actionMethod : actionMethods) {
                        // 处理 Action 方法
                        handleActionMethod(actionClass, actionMethod, commonActionMap, regexpActionMap);
                    }
                }
            }
            // 初始化最终的 Action Map（将 Common 放在 Regexp 前面）
            actionMap.putAll(commonActionMap);
            actionMap.putAll(regexpActionMap);
        }
    }

    private static void handleActionMethod(Class<?> actionClass, Method actionMethod, Map<Requestor, Handler> commonActionMap, Map<Requestor, Handler> regexpActionMap) {
        // 判断当前 Action 方法是否带有 Request 注解
        if (actionMethod.isAnnotationPresent(Request.Get.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Get.class).value();
            putActionMap("GET", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Post.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Post.class).value();
            putActionMap("POST", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Put.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Put.class).value();
            putActionMap("PUT", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        } else if (actionMethod.isAnnotationPresent(Request.Delete.class)) {
            String requestPath = actionMethod.getAnnotation(Request.Delete.class).value();
            putActionMap("DELETE", requestPath, actionClass, actionMethod, commonActionMap, regexpActionMap);
        }
    }

    private static void putActionMap(String requestMethod, String requestPath, Class<?> actionClass, Method actionMethod, Map<Requestor, Handler> commonActionMap, Map<Requestor, Handler> regexpActionMap) {
        // 处理占位符
        if (requestPath.matches(".+\\{\\w+\\}.*")) {
            requestPath = StringUtil.replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
            regexpActionMap.put(new Requestor(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        } else {
            commonActionMap.put(new Requestor(requestMethod, requestPath), new Handler(actionClass, actionMethod));
        }
    }

    /**
     * 获取 Action Map
     */
    public static Map<Requestor, Handler> getActionMap() {
        return actionMap;
    }
}
