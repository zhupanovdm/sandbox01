package org.zhupanovdm;

import java.util.function.BiFunction;

public class RecursiveReducer<T> {
    private final T[] data;
    private final BiFunction<T, T, T> function;

    public RecursiveReducer(T[] data, BiFunction<T, T, T> function) {
        this.data = data;
        this.function = function;
    }

    public T reduce() {
        return reduce(0);
    }

    private T reduce(int i) {
        if (data.length == 0)
            return null;
        if (i == data.length - 1)
            return data[i];
        return function.apply(data[i], reduce(i + 1));
    }

    public static <T extends Comparable<T>> T max(T[] data) {
        return new RecursiveReducer<>(data, (v1, v2) -> v1.compareTo(v2) < 0 ? v2 : v1).reduce();
    }

    public static <T extends Comparable<T>> T min(T[] data) {
        return new RecursiveReducer<>(data, (v1, v2) -> v1.compareTo(v2) < 0 ? v1 : v2).reduce();
    }

}
