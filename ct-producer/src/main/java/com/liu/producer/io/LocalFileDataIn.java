package com.liu.producer.io;

import com.liu.common.bean.Data;
import com.liu.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地文件输入
 */
public class LocalFileDataIn implements DataIn {

    private BufferedReader reader = null;

    public void setPath(String path) throws FileNotFoundException, UnsupportedEncodingException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
    }

    public Object read() throws IOException {
        return null;
    }

    /**
     * 读取数据，返回数据集合
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException, IllegalAccessException, InstantiationException {

        List<T> ts = new ArrayList<T>();
//        从数据文件中读取所有的数据
        String line = null;
        while ((line = reader.readLine()) != null) {
            T t = clazz.newInstance();
            t.setValue(line);
            ts.add(t);
        }
//        将我们的数据转换为指定类型的对象，封装为集合返回
        return ts;
    }

    public LocalFileDataIn(String path) throws FileNotFoundException, UnsupportedEncodingException {
        setPath(path);
    }

    /**
     * 关闭资源
     * @throws IOException
     */
    public void close() throws IOException {
        if (reader != null){
            reader.close();
        }
    }
}
