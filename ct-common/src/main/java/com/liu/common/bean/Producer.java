package com.liu.common.bean;

import java.io.Closeable;

/**
 * 生产者接口
 */
public interface Producer extends Closeable {
    /**
     * 生产数据
     */
    void produce();

    void setIn(DataIn data_in);

    void setOut(DataOut data_out);
}
