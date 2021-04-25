package org.zhupanovdm;

public class BinaryRecursiveSearcher<T extends Comparable<T>> {
    private final T[] data;

    public BinaryRecursiveSearcher(T[] data) {
        this.data = data;
    }

    public int search(T value) {
        return search(value, 0, data.length - 1);
    }

    private int search(T value, int low, int high) {
        if (low > high)
            return -1;
        int i = low + (high - low) / 2;
        int result = value.compareTo(data[i]);
        if (result == 0)
            return i;
        return result > 0 ? search(value, i + 1, high) : search(value, low, i - 1);
    }

}
