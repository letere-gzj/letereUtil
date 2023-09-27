package com.letere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 计算数据占比
     * @param nums 数据大小数组(Double类型)
     * @return 数据占比数组
     */
    public static List<BigDecimal> calRateByDouble(List<Double> nums) {
        if (nums == null || nums.size() == 0) {
            return new ArrayList<>();
        }
        // 转成decimal数据
        BigDecimal totalNum = BigDecimal.ZERO;
        List<BigDecimal> decimalNums = new ArrayList<>(nums.size());
        BigDecimal decimalNum;
        for (Double num : nums) {
            decimalNum = BigDecimal.valueOf(num);
            totalNum = totalNum.add(decimalNum);
            decimalNums.add(decimalNum);
        }
        // 计算占比
        return calRate(decimalNums, totalNum);
    }

    /**
     * 计算数据占比
     * @param nums 数据大小数组(Long类型)
     * @return 数据占比数组
     */
    public static List<BigDecimal> calRateByLong(List<Long> nums) {
        if (nums == null || nums.size() == 0) {
            return new ArrayList<>();
        }
        // 转成decimal数据
        BigDecimal totalNum = BigDecimal.ZERO;
        List<BigDecimal> decimalNums = new ArrayList<>(nums.size());
        BigDecimal decimalNum;
        for (Long num : nums) {
            decimalNum = BigDecimal.valueOf(num);
            totalNum = totalNum.add(decimalNum);
            decimalNums.add(decimalNum);
        }
        // 计算占比
        return calRate(decimalNums, totalNum);
    }

    /**
     * 计算数据占比
     * @param nums 数据大小数组
     * @param totalNum 总数据大小
     * @return 数据占比数组
     */
    private static List<BigDecimal> calRate(List<BigDecimal> nums, BigDecimal totalNum) {
        if (nums == null || nums.size() == 0) {
            return new ArrayList<>();
        }
        List<BigDecimal> rates = new ArrayList<>(16);
        BigDecimal totalRate = BigDecimal.ONE;
        BigDecimal rate;
        for (int i=0; i<nums.size()-1; i++) {
            rate = nums.get(i).divide(totalNum, 4, RoundingMode.DOWN);
            totalRate = totalRate.subtract(rate);
            rates.add(totalRate);
        }
        rates.add(totalRate);
        return rates;
    }
}
