package com.hjx.util;

/**
 * Created by hjx
 * 2018/1/3 0003.
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较金额是否相等
     * @param double1 1
     * @param double2 2
     */
    public static Boolean equals(Double double1,Double double2){
        Double result = Math.abs(double1 - double2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
