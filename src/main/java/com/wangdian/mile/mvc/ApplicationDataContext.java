package com.wangdian.mile.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bigv on 3/28/2017.
 */
public class ApplicationDataContext {
    /**
     * 使每个线程拥有独立的的 ApplicationDataContext 实例
     */
    private static final ThreadLocal<ApplicationDataContext> applicationDataContextThreadLocal =
            new ThreadLocal<ApplicationDataContext>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        ApplicationDataContext dataContext = new ApplicationDataContext();
        dataContext.request = request;
        dataContext.response = response;
        applicationDataContextThreadLocal.set(dataContext);
    }

    public static void destroy() {
        applicationDataContextThreadLocal.remove();
    }
}
