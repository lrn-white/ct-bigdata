package com.liu.common.bean;

/**
 * 数据对象
 */
public abstract class Data implements Val {
    public String content;

    public void setValue(Object val) {
        content = (String) val;
    }

    public Object getValue() {
        return content;
    }
}
