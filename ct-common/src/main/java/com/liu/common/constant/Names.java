package com.liu.common.constant;

import com.liu.common.bean.Val;

/**
 * 名称常量枚举类
 */
public enum Names implements Val {
    /**
     * 命名
     */
    NAMESPACE("ct"),
    TABLE("ct:calllog"),
    CF_CALLLOG("caller"),
    CF_INFO("info"),
    TOPIC("ct");

    private String name;

    private Names(String name) {
        this.name = name;
    }

    public void setValue(Object val) {
        this.name = (String) val;
    }

    public String getValue() {
        return name;
    }
}
