package org.zhupanovdm;

import lombok.EqualsAndHashCode;

import java.util.*;

public class Schedule<T extends Comparable<T>> {
    OrderingListWrapper<Item<T>> items = new OrderingListWrapper<>(new ArrayList<>(), Comparator.comparing(o -> o.begin));

    public Schedule<T> add(T from, T till) {
        items.add(new Item<>(from, till));
        return this;
    }

    public List<Item<T>> compose() {
        List<Item<T>> result = new LinkedList<>();
        List<Item<T>> list = items.getList();
        int i = 0;
        while (i < list.size()) {
            Item<T> item = list.get(i);
            result.add(item);
            i = ListUtils.closestIndex(list, item, (l, r) -> l.end.compareTo(r.begin));
        }
        return Collections.unmodifiableList(result);
    }

    public List<Item<T>> getTasks() {
        return Collections.unmodifiableList(items.getList());
    }

    @EqualsAndHashCode(of = { "begin", "end" })
    public static class Item<T extends Comparable<T>> {
        public final T begin;
        public final T end;
        public Item(T begin, T end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("%s - %s", begin, end);
        }
    }
}
