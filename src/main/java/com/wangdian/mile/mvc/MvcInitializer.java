package com.wangdian.mile.mvc;

import com.wangdian.mile.annotation.Action;
import com.wangdian.mile.annotation.Controller;
import com.wangdian.mile.utils.GlobalConfigUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by walter on 3/20/2017.
 */
public class MvcInitializer {

    private static Map<String, MvcMap> actionMapping;

    public static Map<String, MvcMap> getActionMapping() {
        return actionMapping;
    }

    public static void init(){
        String basePackageName = GlobalConfigUtil.getBasePackageName();
        List<Class<?>> classList =  null; //ClassUtil.getLoaddedClassList(basePackageName);

        List<Class<?>> controllerClassList = new ArrayList<>();
        Map<String, MvcMap> methodMap = new ConcurrentHashMap<>();

        for (Class cls : classList){
            if (cls.isAnnotationPresent(Controller.class)){
                System.out.println("--found Controller class-- " + cls.getName());

                //找到其中带action的方法
                Method[] methods = cls.getDeclaredMethods();

                for (Method method : methods){
                    if (method.isAnnotationPresent(Action.class)){
                        System.out.println("--action method found!");
                        //匹配URL

                        String[] values = method.getDeclaredAnnotation(Action.class).value();
                        System.out.println("--paths in action found : " + values[0]);

                        methodMap.put(values[0], new MvcMap(method, cls));
                    }
                }
                controllerClassList.add(cls);
            }
        }
        actionMapping = methodMap;
    }
}