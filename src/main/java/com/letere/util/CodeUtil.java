package com.letere.util;

/**
 * 随机码工具类
 */
public class CodeUtil {

    /**
     * 获取数字随机码
     * @param size 随机码长度
     * @return 数字随机码
     */
    public static String getNumCode(int size) {
        // 默认6位
        if (size <= 0) {
            size = 6;
        }
        int code = (int)((Math.random()*9+1) * Math.pow(10, size-1));
        return String.valueOf(code);
    }
}
