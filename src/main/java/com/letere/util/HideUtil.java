package com.letere.util;

import java.util.Objects;

/**
 * @author gaozijie
 * @since 2024-04-03
 */
public class HideUtil {

    /**
     * 隐藏手机号
     * @param phone 手机号
     * @return 手机号
     */
    public static String hidePhone(String phone) {
        if (Objects.isNull(phone) || Objects.equals(phone.length(), 0)) {
            return phone;
        }
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 隐藏邮箱地址
     * @param mail 邮箱地址
     * @return 邮箱地址
     */
    public static String hideMail(String mail) {
        if (Objects.isNull(mail) || Objects.equals(mail.length(), 0) || !mail.contains("@")) {
            return mail;
        }
        String[] mailParam = mail.split("@");
        if (mailParam[0].length() == 1) {
            return String.format("*@%s", mailParam[1]);
        }
        if (mailParam[0].length() == 2) {
            return String.format("%s*@%s", mailParam[0].charAt(0), mailParam[1]);
        }
        return mail.replaceAll("(?<=.{1}).(?=.*.@)", "*");
    }
}
