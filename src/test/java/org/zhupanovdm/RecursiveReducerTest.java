package org.zhupanovdm;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecursiveReducerTest {

    @Test
    public void testReduce() {
        int expect = 0;
        Integer[] data = new Integer[100];
        for (int i = 0; i < data.length; i++) {
            int random = RandomUtils.nextInt();
            data[i] = random;
            expect += random;
        }

        assertEquals(expect, new RecursiveReducer<>(data, Integer::sum).reduce());

        assertEquals(1, new RecursiveReducer<>(new Integer[] { 1 }, Integer::sum).reduce());
        assertNull(new RecursiveReducer<>(new Integer[] {}, Integer::sum).reduce());

    }

    @Test
    public void testMax() {
        int expect = Integer.MIN_VALUE;
        Integer[] data = new Integer[100];
        for (int i = 0; i < data.length; i++) {
            int random = RandomUtils.nextInt();
            data[i] = random;
            expect = Math.max(expect, random);
        }

        assertEquals(expect, RecursiveReducer.max(data));

    }

    @Test
    public void testMin() {
        int expect = Integer.MAX_VALUE;
        Integer[] data = new Integer[100];
        for (int i = 0; i < data.length; i++) {
            int random = RandomUtils.nextInt();
            data[i] = random;
            expect = Math.min(expect, random);
        }

        assertEquals(expect, RecursiveReducer.min(data));

    }

}