package com.gun.common.utils;

public class ObjectUtils {
	
	public static boolean isEmpty(Object o) {
        return o == null || o.toString().length() == 0;
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

}
