package com.letere.util;

public class MathUtil {

    /**
     * Greatest Common Divisor 最大公约数 <p>
     * 【辗转相除法】
     * @param a 数字A
     * @param b 数字B
     * @return 最大公约数
     */
    public static int getGreatestCommonDivisor(int a, int b) {
        // 保证a最大
        int temp;
        if (b > a) {
            temp = b;
            b = a;
            a= temp;
        }
        // 存在余数，继续求余数的余数
        while ((temp=a%b) != 0) {
            a = b;
            b = temp;
        }
        return b;
    }

    /**
     * Least Common Multiple 最小公倍数 <p>
     * [(a * b) / ab的最大公约数]
     * @param a 数字A
     * @param b 数字B
     * @return 最小公倍数
     */
    public static int getLeastCommonMultiple(int a, int b) {
        int gcd = getGreatestCommonDivisor(a, b);
        return a * b / gcd;
    }
}
