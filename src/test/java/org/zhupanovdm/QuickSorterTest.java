package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSorterTest {

    @Test
    public void testBasic() {

        Integer[] data = { 4, 5, 0, -1, 7, 10, -6, 8, -9, 4, 5, 6, 10, -5 };
        QuickSorter<Integer> sorter = new QuickSorter<>(data, Integer::compareTo);
        assertArrayEquals(new Integer[] { -9, -6, -5, -1, 0, 4, 4, 5, 5, 6, 7, 8, 10, 10 }, sorter.sort());

    }

    @Test
    public void testEmpty() {

        Integer[] data = { };
        QuickSorter<Integer> sorter = new QuickSorter<>(data, Integer::compareTo);
        assertEquals(0, sorter.sort().length);

    }

    @Test
    public void testSingle() {

        Integer[] data = { 1 };
        QuickSorter<Integer> sorter = new QuickSorter<>(data, Integer::compareTo);
        assertArrayEquals(new Integer[] { 1 }, sorter.sort());

    }

    @Test
    public void testDouble() {

        Integer[] data = { 2, 1 };
        QuickSorter<Integer> sorter = new QuickSorter<>(data, Integer::compareTo);
        assertArrayEquals(new Integer[] { 1, 2 }, sorter.sort());

    }

    @Test
    public void testTriple() {

        Integer[] data = { 2, 1, 8 };
        QuickSorter<Integer> sorter = new QuickSorter<>(data, Integer::compareTo);
        assertArrayEquals(new Integer[] { 1, 2, 8 }, sorter.sort());

    }

}