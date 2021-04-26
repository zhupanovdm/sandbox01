package org.zhupanovdm;

import java.util.*;

public class Dictionary<K, V> implements Map<K, V> {
    private List<Pair<K, V>>[] data;
    private int load;
    private final float threshold;

    public Dictionary(int capacity) {
        threshold = 0.75f;
        init(capacity, null);
    }

    public Dictionary() {
        this(256);
    }

    @Override
    public int size() {
        return load;
    }

    @Override
    public boolean isEmpty() {
        return load == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        List<Pair<K, V>> pairs = data[Math.abs(key.hashCode()) % data.length];
        if (pairs != null) {
            for (Pair<K, V> pair : pairs) {
                if (pair.getKey().equals(key))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<Pair<K, V>> pairs : data) {
            if (pairs != null) {
                for (Pair<K, V> pair : pairs)
                    if (pair.equals(value))
                        return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        List<Pair<K, V>> pairs = data[Math.abs(key.hashCode()) % data.length];
        if (pairs != null) {
            for (Pair<K, V> pair : pairs) {
                if (pair.getKey().equals(key))
                    return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (isOverloaded())
            init(data.length * 5 / 3, data);

        List<Pair<K, V>> pairs = data[Math.abs(key.hashCode()) % data.length];
        if (pairs == null)
            pairs = data[Math.abs(key.hashCode()) % data.length] = new LinkedList<>();

        pairs.add(new Pair<>(key, value));
        load++;

        return value;
    }

    @Override
    public V remove(Object key) {
        List<Pair<K, V>> pairs = data[Math.abs(key.hashCode()) % data.length];
        if (pairs != null) {
            Iterator<Pair<K, V>> iterator = pairs.iterator();
            while (iterator.hasNext()) {
                Pair<K, V> pair = iterator.next();
                if (pair.getKey().equals(key)) {
                    iterator.remove();
                    if (pairs.isEmpty())
                        data[key.hashCode() % data.length] = null;
                    load--;
                    return pair.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        load = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (List<Pair<K, V>> pairs : data) {
            if (pairs != null) {
                for (Pair<K, V> pair : pairs)
                    keys.add(pair.getKey());
            }
        }
        return Collections.unmodifiableSet(keys);
    }

    @Override
    public Collection<V> values() {
        List<V> values = new LinkedList<>();
        for (List<Pair<K, V>> pairs : data) {
            if (pairs != null) {
                for (Pair<K, V> pair : pairs)
                    values.add(pair.getValue());
            }
        }
        return Collections.unmodifiableCollection(values);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (List<Pair<K, V>> pairs : data) {
            if (pairs != null)
                entries.addAll(pairs);
        }
        return Collections.unmodifiableSet(entries);
    }

    private void init(int capacity, List<Pair<K, V>>[] dataToMove) {
        if (capacity == 0)
            throw new IllegalArgumentException("Dictionary capacity must be non zero value");

        //noinspection unchecked
        this.data = (List<Pair<K, V>>[]) new List[capacity];
        load = 0;

        if (dataToMove != null) {
            for (List<Pair<K, V>> pairs : dataToMove) {
                if (pairs != null) {
                    for (Pair<K, V> pair: pairs)
                        put(pair.getKey(), pair.getValue());
                }
            }
        }
    }

    private boolean isOverloaded() {
        return threshold < (float) load / data.length;
    }

    public static class Pair<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }
    }

}
