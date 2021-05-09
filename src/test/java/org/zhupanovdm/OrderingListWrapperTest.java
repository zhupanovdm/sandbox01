package org.zhupanovdm;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderingListWrapperTest {

    @Test
    public void testDescending() {
        OrderingListWrapper<Integer> wrapper = new OrderingListWrapper<>(new ArrayList<>(), (i1, i2) -> -Integer.compare(i1, i2));
        for (int i = 0; i < 100; i++)
            wrapper.add(RandomUtils.nextInt(0, 100));

        List<Integer> list = wrapper.getList();
        for (int i = 1; i < list.size(); i++)
            assertTrue(list.get(i - 1) >= list.get(i));
    }

    @Test
    public void testAscending() {
        OrderingListWrapper<Integer> wrapper = new OrderingListWrapper<>(new ArrayList<>(), Integer::compare);
        for (int i = 0; i < 100; i++)
            wrapper.add(RandomUtils.nextInt(0, 100));

        List<Integer> list = wrapper.getList();
        for (int i = 1; i < list.size(); i++)
            assertTrue(list.get(i - 1) <= list.get(i));
    }

    @Test
    public void testSingle() {
        OrderingListWrapper<Integer> wrapper = new OrderingListWrapper<>(new ArrayList<>(), Integer::compare);

        int random = RandomUtils.nextInt();
        wrapper.add(random);

        List<Integer> list = wrapper.getList();
        assertEquals(random, list.get(0));
    }

}