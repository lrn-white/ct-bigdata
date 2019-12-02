package com.liu.consumer.dao;

import com.liu.common.bean.BaseDao;
import com.liu.common.constant.Names;
import com.liu.common.constant.ValueConstant;

import java.io.IOException;

public class HBaseDao extends BaseDao {
    /**
     * 初始化
     */
    public void init() throws IOException {
        start();

        createNamespaceNX(Names.NAMESPACE.getValue());
        createTableXX(Names.TABLE.getValue(), ValueConstant.REGION_COUNT, Names.CF_CALLLOG.getValue());

        end();

    }

    /**
     * 插入数据
     *
     * @param value
     */
    public void insertData(String value) {
    }
}
