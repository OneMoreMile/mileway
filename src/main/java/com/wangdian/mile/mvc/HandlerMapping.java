package com.wangdian.mile.mvc;

/**
 * Created by bigv on 3/28/2017.
 */
public interface HandlerMapping {

    Handler getHandler(String currentRequestMethod, String currentRequestPath);
}
