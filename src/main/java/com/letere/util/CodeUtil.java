package com.letere.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

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

    /**
     * 使用'SHA-1'方式进行加密
     * @param text 文本
     * @return 加密文本
     */
    public static String encodeBySha(String text) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        md.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        // 字节数组转成16进制字符串
        return HexFormat.of().formatHex(digest);
    }
}
