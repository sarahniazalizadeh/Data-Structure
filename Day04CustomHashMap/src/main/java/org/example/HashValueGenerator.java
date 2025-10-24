package org.example;

public interface HashValueGenerator<K> {

    public int computeHashValue(K key);
}
