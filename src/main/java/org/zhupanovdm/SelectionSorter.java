package org.zhupanovdm;

import java.util.Comparator;

public class SelectionSorter {

    public static <T> void sort(T[] data, Comparator<T> comparator) {
        for (int i = 0; i < data.length; i++) {
            int max = i;
            for (int j = data.length - 1; j > i; j--) {
                if (comparator.compare(data[j], data[max]) > 0)
                    max = j;
            }
            if (max != i) {
                T value = data[i];
                data[i] = data[max];
                data[max] = value;
            }
        }
    }

}
