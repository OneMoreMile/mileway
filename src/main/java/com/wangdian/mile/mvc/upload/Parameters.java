package com.wangdian.mile.mvc.upload;

import com.wangdian.mile.utils.CastUtil;

import java.util.Map;

/**
 * Created by bigv on 3/28/2017.
 */
public class Parameters {
    private final Map<String, Object> fieldMap;

    public Parameters(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public String getString(String name) {
        return CastUtil.castString(get(name));
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(get(name));
    }

    public long getLong(String name) {
        return CastUtil.castLong(get(name));
    }

    public int getInt(String name) {
        return CastUtil.castInt(get(name));
    }

    private Object get(String name) {
        return fieldMap.get(name);
    }
}
