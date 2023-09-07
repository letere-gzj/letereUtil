package com.letere.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gaozijie
 * @date 2023-05-26
 */
@Data
@AllArgsConstructor
public class EncodeStarTemplate {
    Integer strLen;
    /**
     * 正则表达式模板
     */
    String regexTemplate;
    /**
     * 替换字符模板
     */
    String replaceTemplate;
}
