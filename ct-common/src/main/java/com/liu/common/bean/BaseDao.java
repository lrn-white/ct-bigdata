package com.liu.common.bean;


import com.liu.common.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.w3c.dom.html.HTMLCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基础数据访问对象
 */
public abstract class BaseDao {

    private ThreadLocal<Connection> connHolder = new ThreadLocal<Connection>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();

    protected void start() throws IOException {
        getConnection();
        getAdmin();
    }

    /**
     * 创建命名空间，如果命名空间已经存在，不需要创建，否则，创建新的
     *
     * @param namespace
     */
    protected void createNamespaceNX(String namespace) throws IOException {
        Admin admin = getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace);
        } catch (NamespaceNotFoundException e) {
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }
    }

    /**
     * 创建表，如果表已经存在，那么删除后在创建新的
     *
     * @param name
     * @param families
     */
    protected void createTableXX(String name, String... families) throws IOException {
        createTableXX(name, null, families);
    }

    protected void createTableXX(String name, Integer regionCount, String... families) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);

        if (admin.tableExists(tableName)) {
//            表存在，删除表
            deleteTable(name);
        }

//        创建表
        createTable(name, regionCount, families);
    }

    private void createTable(String name, Integer regionCount, String... families) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

        if (families == null || families.length == 0) {
            families = new String[1];
            families[0] = Names.CF_INFO.getValue();
        }
        for (String family : families) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
            tableDescriptor.addFamily(hColumnDescriptor);
        }
//        正价预分区

        if (regionCount == null || regionCount <= 0) {
            admin.createTable(tableDescriptor);
        } else {
//            分区键
            byte[][] spiltKeys = getSplitKeys(regionCount);
            admin.createTable(tableDescriptor, spiltKeys);
        }
    }

    private byte[][] getSplitKeys(int regionCount) {
        int splitKeyCount = regionCount - 1;
        byte[][] bs = new byte[splitKeyCount][];
        List<byte[]> bsList = new ArrayList<byte[]>();

        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey = i + "|";
            bsList.add(Bytes.toBytes(splitKey));
        }

        bsList.toArray(bs);
        return bs;
    }

    protected void deleteTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        Admin admin = getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    protected void end() throws IOException {
        Admin admin = getAdmin();
        if (admin != null) {
            admin.close();
            adminHolder.remove();
        }

        Connection connection = getConnection();
        if (connection != null) {
            connection.close();
            connHolder.remove();
        }
    }

    /**
     * 获取数据连接对象
     *
     * @ret
     */
    protected synchronized Admin getAdmin() throws IOException {
        Admin admin = adminHolder.get();
        if (admin == null) {
            admin = getConnection().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }

    /**
     * 获取数据连接对象
     *
     * @return
     */
    protected synchronized Connection getConnection() throws IOException {
        Connection conn = connHolder.get();
        if (conn == null) {
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }
}
