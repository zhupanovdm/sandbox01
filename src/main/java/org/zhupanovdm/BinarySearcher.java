package org.zhupanovdm;

public class BinarySearcher<T extends Comparable<T>> {
    private final T[] data;

    public BinarySearcher(T[] data) {
        this.data = data;
    }

    public int search(T value) {
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int i = low + (high - low) / 2;
            int result = value.compareTo(data[i]);
            if (result == 0)
                return i;

            if (result > 0) {
                low = i + 1;
            } else {
                high = i - 1;
            }
        }
        return -1;
    }

    public int[] searchBounds(T value) {
        int found = search(value);
        if (found < 0)
            return null;

        int[] bounds = new int[2];
        boolean goOn = true;
        for (int i = 0; goOn; i++) {
            goOn = false;
            if (found - i >= 0 && data[found - i].equals(value)) {
                bounds[0] = found - i;
                goOn = true;
            }
            if (found + i < data.length && data[found + i].equals(value)) {
                bounds[1] = found + i;
                goOn = true;
            }
        }
        return bounds;
    }

}
