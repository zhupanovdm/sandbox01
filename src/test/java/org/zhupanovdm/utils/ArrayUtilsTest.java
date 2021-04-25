package org.zhupanovdm.utils;

import org.junit.jupiter.api.Test;
import org.zhupanovdm.ArrayUtils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayUtilsTest {

    @Test
    public void testSwap() {
        Integer[] data = {1, 2};
        ArrayUtils.swap(data, 0, 1);
        assertArrayEquals(new Integer[] { 2, 1 }, data);
    }

    @Test
    public void testSiblingBounds() {
        Integer[] data = {0, 1, 5, 5, 5, 5, 6, 7, 8, 9};
        assertArrayEquals(new int[] { 0, 0 }, ArrayUtils.siblingBounds(data, 0));
        assertArrayEquals(new int[] { 2, 5 }, ArrayUtils.siblingBounds(data, 5));
        assertArrayEquals(new int[] { 8, 8 }, ArrayUtils.siblingBounds(data, 8));
        assertArrayEquals(new int[] { 9, 9 }, ArrayUtils.siblingBounds(data, 9));
    }

    @Test
    public void testSiblingBoundsEdges() {
        Integer[] left = {5, 5, 5, 5, 9};
        assertArrayEquals(new int[] { 0, 3 }, ArrayUtils.siblingBounds(left, 0));
        assertArrayEquals(new int[] { 0, 3 }, ArrayUtils.siblingBounds(left, 3));

        Integer[] right = {9, 5, 5, 5, 5};
        assertArrayEquals(new int[] { 1, 4 }, ArrayUtils.siblingBounds(right, 1));
        assertArrayEquals(new int[] { 1, 4 }, ArrayUtils.siblingBounds(right, 4));
    }

}