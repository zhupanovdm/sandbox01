package org.zhupanovdm;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void swap(T[] data, int i, int j) {
        T temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    public static <T> int[] siblingBounds(T[] data, int index) {
        T item = data[index];
        int[] bounds = new int[2];
        boolean goOn = true;
        for (int j = 0; goOn; j++) {
            goOn = false;
            if (index - j >= 0 && data[index - j].equals(item)) {
                bounds[0] = index - j;
                goOn = true;
            }
            if (index + j < data.length && data[index + j].equals(item)) {
                bounds[1] = index + j;
                goOn = true;
            }
        }
        return bounds;
    }

}
