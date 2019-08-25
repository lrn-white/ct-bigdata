package com.liu.common.bean;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * 数据去向
 *
 * @author lrn
 */
public interface DataOut extends Closeable {
    void setPath(String path) throws FileNotFoundException, UnsupportedEncodingException;

    public void write(Object data) throws Exception;

    public void write(String data) throws Exception;
}
