package com.wangdian.mile.mvc.upload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigv on 3/28/2017.
 */
public class Multiparts {
    private List<Multipart> multipartList = new ArrayList<Multipart>();

    public Multiparts(List<Multipart> multipartList) {
        this.multipartList = multipartList;
    }

    public int size() {
        return multipartList.size();
    }

    public List<Multipart> getAll() {
        return multipartList;
    }

    public Multipart getOne() {
        return size() == 1 ? multipartList.get(0) : null;
    }
}
