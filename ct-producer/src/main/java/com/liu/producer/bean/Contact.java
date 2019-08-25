package com.liu.producer.bean;

import com.liu.common.bean.Data;
import com.liu.common.bean.Val;

/**
 * 联系人
 */
public class Contact extends Data {
    private String tel;
    private String name;

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setValue(Object val) {
        content = (String) val;
        String[] values = content.split("\t");
        setName(values[1]);
        setTel(values[0]);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
