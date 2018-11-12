package com.wangdian.mile.mvc;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by bigv on 3/20/2017.
 */
public class MvcMap implements Serializable {
    private Method method;
    private Class classInstance;

    public MvcMap(Method method, Class classInstance){
        this.method = method;
        this.classInstance = classInstance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getClassInstance() {
        return classInstance;
    }

    public void setClassInstance(Class classInstance) {
        this.classInstance = classInstance;
    }
}
