package com.liu.common.bean;

/**
 * 数据对象
 */
public class DataValue implements Val {

    public String content;

    public void setValue(String value) {
        content = value;
    }

    public Object value() {
        return null;
    }

    public void setValue(Object val) {

    }

    public Object getValue() {
        return null;
    }
}
