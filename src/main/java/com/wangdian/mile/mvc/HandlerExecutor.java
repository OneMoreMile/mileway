package com.wangdian.mile.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bigv on 3/28/2017.
 */
public interface HandlerExecutor {

    void invokeHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception;
}
