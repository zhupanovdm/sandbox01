package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RucksackTest {

    @Test
    public void testBasic() {

        Rucksack<Rucksack.ItemString> rucksack = new Rucksack<>(500);

        HashSet<Rucksack.ItemString> stuff = new HashSet<>();
        stuff.add(new Rucksack.ItemString("Player", 150));
        stuff.add(new Rucksack.ItemString("Disks", 30));
        stuff.add(new Rucksack.ItemString("Water", 100));
        stuff.add(new Rucksack.ItemString("Book", 127));
        stuff.add(new Rucksack.ItemString("Cup", 87));
        stuff.add(new Rucksack.ItemString("GPS", 65));
        stuff.add(new Rucksack.ItemString("Phone", 29));
        stuff.add(new Rucksack.ItemString("Papers", 5));

        System.out.println(stuff.stream()
                .map(item -> item.weight)
                .reduce(0, Integer::sum));

        assertEquals(499, rucksack.pack(stuff));

        List<Rucksack.ItemString> items = rucksack.getItems();
        System.out.println(items);
        for (int i = 1; i < items.size(); i++)
            assertTrue(items.get(i - 1).weight >= items.get(i).weight);

    }

}