package org.zhupanovdm;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSorter<T> {
    private final T[] data;
    private final Comparator<T> comparator;

    public QuickSorter(T[] data, Comparator<T> comparator) {
        this.data = data;
        this.comparator = comparator;
    }

    public T[] sort() {
        return sort(data);
    }

    private T[] sort(T[] array) {
        if (array.length < 2)
            return array;

        int pivot = getPivot(array);

        int l = 0; int h = 0;
        T[] temp = Arrays.copyOf(array, array.length);

        for (int i = 0; i < array.length; i++) {
            if (i == pivot)
                continue;
            if (comparator.compare(array[i], array[pivot]) >= 0) {
                temp[array.length - 1 - h++] = array[i];
            } else {
                temp[l++] = array[i];
            }
        }

        T[] low = sort(Arrays.copyOfRange(temp, 0, l));
        System.arraycopy(low, 0, temp, 0, low.length);
        temp[low.length] = array[pivot];

        T[] high = sort(Arrays.copyOfRange(temp, array.length - h, array.length));
        System.arraycopy(high, 0, temp, low.length + 1, high.length);

        return temp;
    }

    private int getPivot(T[] array) {
        return array.length / 2;
    }

}
