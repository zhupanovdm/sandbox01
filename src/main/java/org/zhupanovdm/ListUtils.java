package org.zhupanovdm;

import java.util.Comparator;
import java.util.List;

public class ListUtils {

    public static <T> int[] siblingBounds(List<T> list, int index) {
        T item = list.get(index);
        int[] bounds = new int[2];
        boolean goOn = true;
        for (int j = 0; goOn; j++) {
            goOn = false;
            if (index - j >= 0 && list.get(index - j).equals(item)) {
                bounds[0] = index - j;
                goOn = true;
            }
            if (index + j < list.size() && list.get(index + j).equals(item)) {
                bounds[1] = index + j;
                goOn = true;
            }
        }
        return bounds;
    }

    public static <T> int closestIndex(List<T> list, T item, Comparator<T> comparator) {
        if (list.isEmpty())
            return 0;

        int lResult = (int) Math.signum(comparator.compare(item, list.get(0)));
        if (lResult <= 0)
            return 0;

        int rResult = (int) Math.signum(comparator.compare(item, list.get(list.size()-1)));
        if (rResult >= 0)
            return list.size() + rResult - 1;

        return closestIndex(list, item, comparator, 0, list.size() - 1);
    }

    private static <T> int closestIndex(List<T> list, T item, Comparator<T> comparator, int l, int r) {
        if (l > r)
            return l;
        int i = l + (r - l) / 2;
        int result = comparator.compare(item, list.get(i));
        if (result == 0)
            return i;
        if (result > 0)
            return closestIndex(list, item, comparator, i + 1, r);
        else
            return closestIndex(list, item, comparator, l, i - 1);
    }

}
