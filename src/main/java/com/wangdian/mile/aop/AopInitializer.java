package com.wangdian.mile.aop;

import com.wangdian.mile.utils.BeansUtil;
import com.wangdian.mile.utils.GlobalConfigUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by bigv on 3/21/2017.
 */
public class AopInitializer {

    private AopInitializer(){}

    public static void init() {
        String basePackageName = GlobalConfigUtil.getBasePackageName();
        List<Class<?>> classList =   null; //ClassUtil.getLoaddedClassList(basePackageName);

        String method = GlobalConfigUtil.getAOPMethod();
        String className = GlobalConfigUtil.getAOPBaseClassName();

        boolean exist = false;
        Class cls0 = null;
        for (Class cls : classList){
            if (cls.getName().equals(className)){
                exist = true;
                cls0 = cls;
                break;
            }
        }

        if (exist){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(cls0);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    System.out.println("--AOP被执行--");
                    return null;
                }
            });

            Object enhancedObj = enhancer.create();

            //找到已经生成的对象
            Object obj = BeansUtil.getObjectMapValue(cls0);
            BeansUtil.updateObjectMap(cls0, enhancedObj);

        }


    }
}