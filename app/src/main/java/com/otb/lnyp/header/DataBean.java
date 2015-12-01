package com.otb.lnyp.header;

/**
 * Created by lining on 2015/11/26.
 */
public class DataBean {

    public String info;

    public DataBean() {

    }

    public DataBean(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "info='" + info + '\'' +
                '}';
    }
}
