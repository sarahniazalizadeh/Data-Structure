package org.example;

import java.util.Arrays;

public class CustomHashMap<K extends Comparable<K> & HashValueGenerator<K>, V> {

    private class Container<K2 extends Comparable<K2>, V2> {
        Container next;
        K2 key;
        V2 value;
    }

    // size must be a prime number always

    int size = 5;
    private Container[] hashTable = new Container[size];
    private int totalItems;

    void putValue(K key, V value) {
        int hashValue = key.computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;

        Container<K, V> current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Container<K, V> newContainer = new Container();
        newContainer.key = key;
        newContainer.value = value;
        newContainer.next = hashTable[index];
        hashTable[index] = newContainer;
        totalItems++;
    }
    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length

    boolean hasKey(K key) {
        int hashValue = key.computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;
        Container<K, V> temp = hashTable[index];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // throw custom unchecked KeyNotFoundException
    V getValue(K key) {
        int hashValue = key.computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;
        Container<K, V> temp = hashTable[index];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        throw new KeyNotFoundException("Key not found: " + key);
    }    // throw custom unchecked KeyNotFoundException

    void deleteByKey(K key) {
        int hashValue = key.computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;
        Container<K, V> temp = hashTable[index];
        Container<K, V> pre = null;
        while (temp != null) {
            if (temp.key.equals(key)) {
                if (pre == null) {
                    hashTable[index] = temp.next;
                } else {
                    pre.next = temp.next;
                }
                temp.next = null;
                totalItems--;
                return;
            }
            pre = temp;
            temp = temp.next;
        }
        throw new KeyNotFoundException("Key not found: " + key);
    }

    public K[] getAllKeys(K[] template) {
        K[] result = (K[]) java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != null) {
                Container<K, V> temp = hashTable[i];
                while (temp != null) {
                    result[counter++] = temp.key;
                    temp = temp.next;
                }
            }
        }
        Arrays.sort(result);
        return result;
    }
// Generic version: public K[] getAllKeys(K[] template) { ... }

    public Pair<K, V>[] getAllKeyValPairs() {
        Pair<K, V>[] result = (Pair<K, V>[]) new Pair[totalItems];
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != null) {
                Container<K, V> temp = hashTable[i];
                while (temp != null) {
                    result[counter++] = new Pair<>(temp.key, temp.value);
                    temp = temp.next;
                }
            }
        }
        return result;
    }


    int getSize() {
        return totalItems;
    }

    public void printDebug() {
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println(i + ": ");
            Container<K, V> temp = hashTable[i];
            while (temp != null) {
                System.out.println("   { " + temp.key + " : " + temp.value + " }");
                temp = temp.next;
            }
        }
    } // print hashTable content, see example below

    @Override
    public String toString() {
        K[] template = (K[]) java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        K[] keys = getAllKeys(template);
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < keys.length; i++) {
            sb.append((i == 0 ? "" : ", ") + keys[i] + " => " + getValue(keys[i]));
        }
        sb.append(" ]");
        return sb.toString();
    } // comma-separated key->value pair list
// to be able to use this as validation in Unit tests keys must be sorted
// e.g. [Key1=>Val1, Key2 => Val2, ... ]

}
