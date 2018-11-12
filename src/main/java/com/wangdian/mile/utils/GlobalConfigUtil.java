package com.wangdian.mile.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * 读取全局配置文件.
 * Created by walter on 3/16/2017.
 */
public class GlobalConfigUtil {

    private static Logger logger = LoggerFactory.getLogger(GlobalConfigUtil.class);

    private static Properties props = new Properties();

    private GlobalConfigUtil() {
    }

    static {
        Optional<InputStream> is = Optional.of(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("mile-config.properties"));
        if (is.isPresent()) {
            InputStream inputStream = is.get();

            try {
                props.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            logger.error("configuration file mile-config.properties not found!");
            throw new RuntimeException("configuration file mile-config.properties not found!");
        }
    }

    public static String getBasePackageName() {
        return (String) props.get("com.mile.basic.package");
    }

    public static String getAOPBaseClassName(){
        return (String) props.get("aop.class");
    }

    public static String getAOPMethod(){
        return (String) props.get("aop.method");
    }

    public static String getString(String cacheKey) {
        return (String)props.get(cacheKey);
    }

    public static String getString(String s, String s1) {
        return getString(s) != null ? getString(s) : null;
    }
}
