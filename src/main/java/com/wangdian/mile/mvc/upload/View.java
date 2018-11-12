package com.wangdian.mile.mvc.upload;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bigv on 3/21/2017.
 */
public class View implements Serializable {
    private String path;              // 视图路径
    private Map<String, Object> data; // 相关数据

    public View(String path) {
        this.path = path;
        data = new HashMap<String, Object>();
    }

    public View data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
