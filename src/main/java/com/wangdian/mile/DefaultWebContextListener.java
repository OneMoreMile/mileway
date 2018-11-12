package com.wangdian.mile;

import com.wangdian.mile.aop.AopInitializer;
import com.wangdian.mile.ioc.IocInitializer;
import com.wangdian.mile.mvc.MvcInitializer;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by bigv on 3/16/2017.
 */

@WebListener
public class DefaultWebContextListener implements ServletContextListener {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultWebContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("contextInitialized executed.");

        IocInitializer.init();
        MvcInitializer.init();
        AopInitializer.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("contextDestroyed executed.");
    }
}
