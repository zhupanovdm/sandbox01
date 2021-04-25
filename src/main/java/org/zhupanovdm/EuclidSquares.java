package org.zhupanovdm;

public class EuclidSquares {

    public static int getMaxSquare(int width, int height) {
        if (width == 0 || height == 0)
            throw new IllegalArgumentException("Argument value can not be zero");

        int l = Math.min(width, height);
        int w = width % l; int h = height % l;
        if (w == 0 && h != 0)
            return getMaxSquare(width, h);
        if (w != 0 && h == 0)
            return getMaxSquare(w, height);

        return l;
    }

}
