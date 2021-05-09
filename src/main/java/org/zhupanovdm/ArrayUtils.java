package org.zhupanovdm;

import java.util.Arrays;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void swap(T[] data, int i, int j) {
        T temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    public static <T> int[] siblingBounds(T[] data, int index) {
        return ListUtils.siblingBounds(Arrays.asList(data), index);
    }

}
