package com.wangdian.mile.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bigv on 3/28/2017.
 */
public interface ViewResolver {
    void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult);
}
