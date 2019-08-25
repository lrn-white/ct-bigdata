package com.liu.producer.io;

import com.liu.common.bean.DataOut;

import java.io.*;

/**
 * 本地文件输出
 *
 * @author lrn
 */
public class LocalFileDataOut implements DataOut {
    private PrintWriter writer = null;

    public void setPath(String path) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
    }

    public void write(Object data) throws Exception {
        write(data.toString());
    }

    /**
     * 将数据字符串生成到文件中
     *
     * @param data
     * @throws Exception
     */
    public void write(String data) throws Exception {
        writer.println(data);
        writer.flush();
    }

    public LocalFileDataOut(String path) throws FileNotFoundException, UnsupportedEncodingException {
        setPath(path);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    public void close() throws IOException {
        if (writer != null){
            writer.close();
        }
    }
}
