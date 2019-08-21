package com.liu.common.bean;

/**
 * 生产者借口
 */
public interface Producer {
    /**
     * 生产数据
     */
    void produce();

    void setIn(DataIn data_in);

    void setOut(DataOut data_out);
}
