package com.hjx.util;

import com.hjx.enums.CodeEnum;

/**
 * code获取msg
 * Created by hjx
 * 2018/1/4 0004.
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
