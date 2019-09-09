package com.liu.consumer;

import com.liu.common.bean.Consumer;
import com.liu.consumer.bean.CalllogConsumer;

import java.io.IOException;

/**
 * 启动消费者
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
//        使用kafka消费者获取flume采集的数据
//        创建消费者
        Consumer consumer = new CalllogConsumer();
//        消费数据
        consumer.consume();
//        关闭资源
        consumer.close();
//        将数据存入hbase中
    }
}
