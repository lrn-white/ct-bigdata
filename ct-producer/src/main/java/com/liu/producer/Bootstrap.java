package com.liu.producer;

import com.liu.common.bean.Producer;
import com.liu.producer.bean.LocalFileProducer;
import com.liu.producer.io.LocalFileDataIn;
import com.liu.producer.io.LocalFileDataOut;

/**
 * 启动对象
 */
public class Bootstrap {
    public static void main(String[] args) throws Exception {
//        构建生产者对象
        Producer producer = new LocalFileProducer();
        producer.setIn(new LocalFileDataIn("/home/lrn/IdeaProjects/ct-bigdata/ct-producer/src/main/resources/contact.log"));
        producer.setOut(new LocalFileDataOut("/home/lrn/IdeaProjects/ct-bigdata/ct-producer/src/main/resources/call.log"));
//        生产数据
        producer.produce();
//        关闭生产者对象
        producer.close();
    }
}
