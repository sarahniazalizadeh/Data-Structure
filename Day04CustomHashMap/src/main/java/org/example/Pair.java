package org.example;

public class Pair<K extends Comparable<K>, V> implements Comparable<Pair<K, V>> {
    K key;
    V val;

    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public int compareTo(Pair<K, V> o) {
        return key.compareTo(o.key);
    }
}
