package com.liu.common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * 消费者接口
 */
public interface Consumer extends Closeable {
    /**
     * 消费数据
     */
    void consume() throws IOException;
}
