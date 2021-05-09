package org.zhupanovdm;

import lombok.Getter;

import java.util.*;

public class Rucksack<T extends Rucksack.Item> {
    @Getter
    private final int capacity;
    @Getter
    private int load = 0;

    private final List<T> items = new LinkedList<>();

    public Rucksack(int capacity) {
        this.capacity = capacity;
    }

    public void clear() {
        items.clear();
        load = 0;
    }

    public int pack(Set<T> stuff) {
        Set<T> remains = new HashSet<>(stuff);
        T suitable;
        while ((suitable = findSuitable(remains)) != null) {
            items.add(suitable);
            remains.remove(suitable);
            load += suitable.weight;
        }
        return load;
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    private T findSuitable(Set<T> stuff) {
        int max = 0;
        T suitable = null;
        for (T item : stuff) {
            if (item.weight > capacity - load)
                continue;
            if (item.weight > max) {
                suitable = item;
                max = item.weight;
            }
        }
        return suitable;
    }

    public static class Item {
        public final int weight;
        public Item(int weight) {
            this.weight = weight;
        }
    }

    public static class ItemString extends Item {
        public final String thing;
        public ItemString(String thing, int weight) {
            super(weight);
            this.thing = thing;
        }

        @Override
        public String toString() {
            return thing + " (" + weight + ')';
        }
    }

}
