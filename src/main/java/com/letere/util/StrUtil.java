package com.letere.util;

import com.letere.bean.EncodeStarTemplate;

import java.util.Objects;

/**
 * 字符串工具类
 * @author gaozijie
 * @date 2023-05-26
 */
public class StrUtil {
    /**
     * 分割符号
     */
    private final static String SPLIT = ",";

    /**
     * 字符串星号(*)加密
     * @param str 字符
     * @param template 加密模板
     * <p></p>
     * 1234567890 -> 123****890 <p>
     * 样板：3,*4,3，字符长度,*字符串长度,字符长度
     * @return 加密符号
     */
    public static String encodeWithStar(String str, String template) {
        // 字符串为空或模板为空，不进行处理进行返回
        if (Objects.isNull(str) || Objects.isNull(template)) {
            return str;
        }
        // 获取正则表达式
        EncodeStarTemplate parseData = parseTemplate(template);
        if (Objects.isNull(parseData) || !Objects.equals(str.length(), parseData.getStrLen())) {
            throw new RuntimeException("模板错误，解析失败");
        }
        // 正则加密字符串
        return str.replaceAll(parseData.getRegexTemplate(), parseData.getReplaceTemplate());
    }

    /**
     * 解析星号(*)加密模板
     * @param template 加密模板
     * @return
     */
    private static EncodeStarTemplate parseTemplate(String template) {
        if (Objects.isNull(template)) {
            return null;
        }
        StringBuilder regexTemplate = new StringBuilder();
        StringBuilder replaceTemplate = new StringBuilder();
        int index = 1;
        int strLen = 0;
        int paramSize;
        // 解析模板
        String[] params = template.split(",");
        for (String param : params) {
            if (param.contains("*")) {
                paramSize = Integer.parseInt(param.replace("*", ""));
                regexTemplate.append(String.format(".{%d}", paramSize));
                replaceTemplate.append(String.format("%"+ paramSize +"s", "").replace(" ", "*" ));
            } else {
                paramSize = Integer.parseInt(param);
                regexTemplate.append(String.format("(.{%d})", paramSize));
                replaceTemplate.append(String.format("$%d", index++));
            }
            strLen += paramSize;
        }
        return new EncodeStarTemplate(strLen, regexTemplate.toString(), replaceTemplate.toString());
    }

    public static void main(String[] args) {
        String number = "1234567890";
        String str = "*1,1,*2,2,*3";
        String s = encodeWithStar(number, str);
        System.out.println(s);
    }
}
