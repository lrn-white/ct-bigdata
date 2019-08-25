package com.liu.producer.bean;

import com.liu.common.bean.DataIn;
import com.liu.common.bean.DataOut;
import com.liu.common.bean.Producer;
import com.liu.common.util.DateUtil;
import com.liu.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/***
 * 本地数据文件的生产者
 */
public class LocalFileProducer implements Producer {
    private DataIn dataIn;
    private DataOut dataOut;
    private volatile boolean produceFlag = true;

    /**
     * 生产数据
     */
    public void produce() {

        try {
//            读取通讯录的数据
            List<Contact> contacts = dataIn.read(Contact.class);

            while (produceFlag) {
//                从通讯录中随机查找2个电话号码（主叫，被叫）
                int call1Index = new Random().nextInt(contacts.size());
                int call2Index;
                do {
                    call2Index = new Random().nextInt(contacts.size());
                } while (call1Index == call2Index);

                Contact call1 = contacts.get(call1Index);
                Contact call2 = contacts.get(call2Index);

//                生成随机的通话时间
                String startDate = "20180101000000";
                String endDate = "20190101000000";
                long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate, "yyyyMMddHHmmss").getTime();
//                通话时间
                long callTime = startTime + (long) ((endTime - startTime) * Math.random());
//                通话时间字符串
                String callTimeString = DateUtil.format(new Date(callTime), "yyyyMMddHHmmss");
//                生成随机的通话时长
                String duration = NumberUtil.format(new Random().nextInt(3000), 4);

//                生成通话记录
                Calllog log = new Calllog(call1.getTel(), call2.getTel(), callTimeString, duration);
                System.out.println(log);
//                将通话记录刷写到数据文件中
                dataOut.write(log);
                Thread.sleep(500);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setIn(DataIn dataIn) {
        this.dataIn = dataIn;
    }

    public void setOut(DataOut dataOut) {
        this.dataOut = dataOut;
    }

    /**
     * 关闭生产者
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (dataIn != null) {
            dataIn.close();
        }
        if (dataOut != null) {
            dataOut.close();
        }
    }
}
