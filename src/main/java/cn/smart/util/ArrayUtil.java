package cn.smart.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by shu on 2017/3/24.
 */
public final class ArrayUtil {

    private ArrayUtil() {}

    public static boolean isEmpty(Object[] objects) {
        return ArrayUtils.isEmpty(objects);
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !ArrayUtils.isEmpty(objects);
    }
}
