package com.wangdian.mile.ioc;

import com.wangdian.mile.annotation.Inject;
import com.wangdian.mile.utils.BeansUtil;
import com.wangdian.mile.utils.GlobalConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 初始化Bean，完成注入.
 * Created by walter on 3/16/2017.
 */
public class IocInitializer {

    private static Logger logger = LoggerFactory.getLogger(IocInitializer.class);

    private IocInitializer(){}

    public static void init(){
        String basePackageName = GlobalConfigUtil.getBasePackageName();
        List<Class<?>> classList =  null; //ClassUtil.getLoaddedClassList(basePackageName);

        //依赖注入
        for (Class cls : classList){
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields){
                if (f.isAnnotationPresent(Inject.class)){
                    Class c = f.getType();
                    if (c.isInterface()){
                        //找到这个接口类的实现类们.
                        List<Class<?>> implementedClsList = findImplmentedClasses(c, classList);
                        //初始化该域.
                        initializeFiled(cls, f, implementedClsList);
                    }
                }
            }
        }
    }

    /**
     * 在classList中，找到接口interfaceCls的实现类.
     * @param interfaceCls
     * @param classList
     * @return
     */
    private static List<Class<?>> findImplmentedClasses(Class interfaceCls, List<Class<?>> classList){
        List<Class<?>> matchedClassList = new ArrayList<>();
        for (Class cls : classList){
            if (cls.getInterfaces() != null){
                Class[] clsArray = cls.getInterfaces();
                for (Class c : clsArray){
                    if (Objects.equals(c.getName(),interfaceCls.getName())){
                        matchedClassList.add(cls);
                        logger.info("--找到一个匹配的实现类 : " + cls.getName() +" super class: " + interfaceCls);
                    }
                }
            }
        }
        return matchedClassList;
    }

    private static void initializeFiled(Class cls, Field f, List<Class<?>> classList){
        f.setAccessible(true);
        //如何初始化？？
        Class class0 = classList.get(0);
        try {

            Object obj = null;
            if (BeansUtil.getObjectMapValue(cls) == null) {
                obj = cls.newInstance();
                BeansUtil.addObjectMap(cls, obj);
            }else {
                obj = BeansUtil.getObjectMapValue(cls);
            }
            Object value = class0.newInstance();
            f.set(obj, value);
            logger.info("--依赖注入完成for class : " + cls.getName() + ", 被注入的实例变量是:"+f.getName()+",实现类是： " + f.get(obj));
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
