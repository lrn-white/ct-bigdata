package com.liu.common.util;

import java.text.DecimalFormat;

/**
 * 数字处理
 */
public class NumberUtil {
    /**
     * 将数字格式化为字符串
     *
     * @param num
     * @param length
     * @return
     */
    public static String format(int num, int length) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append("0");
        }
        DecimalFormat df = new DecimalFormat(stringBuilder.toString());
        return df.format(num);
    }
}
