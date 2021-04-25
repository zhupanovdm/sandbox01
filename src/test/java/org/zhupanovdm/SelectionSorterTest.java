package org.zhupanovdm;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSorterTest {

    @Test
    public void testDescending() {
        Integer[] data = new Integer[100];
        for (int i = 0; i < data.length; i++)
            data[i] = RandomUtils.nextInt();

        SelectionSorter.sort(data, Integer::compareTo);

        for (int i = 1; i < data.length; i++)
            assertTrue(data[i - 1] > data[i]);

    }

    @Test
    public void testAscending() {
        Integer[] data = new Integer[100];
        for (int i = 0; i < data.length; i++)
            data[i] = RandomUtils.nextInt();

        SelectionSorter.sort(data, (i1, i2) -> -Integer.compare(i1, i2));

        for (int i = 1; i < data.length; i++)
            assertTrue(data[i - 1] < data[i]);

    }

    @Test
    public void testEmpty() {
        assertDoesNotThrow(() -> SelectionSorter.sort(new Integer[0], Integer::compareTo));
    }

    @Test
    public void testSingle() {
        int random = RandomUtils.nextInt();

        Integer[] data = new Integer[1];
        data[0] = random;

        SelectionSorter.sort(data, Integer::compareTo);
        assertEquals(random, data[0]);

    }

}