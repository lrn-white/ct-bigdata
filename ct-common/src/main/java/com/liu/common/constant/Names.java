package com.liu.common.constant;

import com.liu.common.bean.Val;

/**
 * 名称常量枚举类
 */
public enum Names implements Val {
    /**
     * 命名
     */
    NAMESPACE("ct");

    private String name;

    private Names(String name) {
        this.name = name;
    }

    public void setValue(Object val) {
        this.name = (String) val;
    }

    public Object getValue() {
        return null;
    }
}
