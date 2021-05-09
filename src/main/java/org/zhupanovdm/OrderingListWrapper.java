package org.zhupanovdm;

import lombok.Getter;

import java.util.Comparator;
import java.util.List;

public class OrderingListWrapper<T> {
    @Getter
    private final List<T> list;
    private final Comparator<T> comparator;

    public OrderingListWrapper(List<T> list, Comparator<T> comparator) {
        this.list = list;
        this.comparator = comparator;
    }

    public void add(T item) {
        list.add(ListUtils.closestIndex(list, item, comparator), item);
    }

}
