package day04customhashmap;

import java.util.Arrays;

public class CustomHashMap<K extends Comparable<K>, V> {
    
    private class Container <K2 extends Comparable<K2>,V2> {
        Container next;
        K2 key;
        V2 value;
    }

    // size must be a prime number always
    private Container[] hashTable = new Container[5]; // ???

    // for Container without generic parameters
    // private CustomHashMap.Container[] hashTable = new CustomHashMap.Container[5];

    private int totalItems;

    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length
    void putValue(K key, V value) {
        int hash = key.hashCode(); // hashCode is deterministic, can be redefined in custom classes, rules in Javadoc
        int index = hash % hashTable.length;
        Container currCont = hashTable[index];
        while (currCont != null) {
            // if key already exists the update value, do not add
            if (currCont.key.equals(key)) {
                currCont.value = value; // update an existing entry
                return;
            }
            currCont = currCont.next; // go to next container
        }
        // we only reach this code if key was not found
        Container<K, V> newCont = new Container();
        newCont.key = key;
        newCont.value = value;
        // insert at the beginning of this hash table entry
        newCont.next = hashTable[index];
        hashTable[index] = newCont;
        totalItems++;
    }

    boolean hasKey(K key) {
        int hash = key.hashCode();
        int index = hash % hashTable.length;
        Container currCont = hashTable[index];
        while (currCont != null) {
            if (currCont.key.compareTo(key) == 0) { // key found
                return true;
            }
            currCont = currCont.next; // go to next container
        }
        return false;
    }

    V getValue(K key) {
        int hash = key.hashCode();
        int index = hash % hashTable.length;
        // for (Container current = hashTable[index]; current != null; current = current.next) {
        Container<K, V> currCont = hashTable[index];
        while (currCont != null) {
            if (currCont.key.equals(key)) {
                return currCont.value;
            }
            currCont = currCont.next; // go to next container
        }
        throw new NoSuchElementException();
    }

    // throw custom unchecked KeyNotFoundException
    void deleteByKey(K key) {
        int hash = key.hashCode();
        int index = hash % hashTable.length;
        //
        Container previous = null;
        Container current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) { // found it
                break;
            }
            previous = current;
            current = current.next;
        }
        // either key was found or current is null if not found
        if (current == null) { // key not found
            throw new NoSuchElementException();
        }
        //
        if (previous == null) { // removing the first item on the list (special case)
            hashTable[index] = current.next;
        } else { // remove one of the later items (common case)
            previous.next = current.next;
        }
        totalItems--;
    }

    public K[] getAllKeys(K[] template) {
        K[] result = (K[]) java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), totalItems);
        int nextResultIndex = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (Container<K, V> currCont = hashTable[i]; currCont != null; currCont = currCont.next) {
                result[nextResultIndex++] = currCont.key;
            }
        }
        return result;
    }

    public Pair<K, V>[] getAllKeyValPairs() {
        Pair<K, V>[] result = new Pair[totalItems];
        int nextResultIndex = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (Container<K, V> currCont = hashTable[i]; currCont != null; currCont = currCont.next) {
                Pair<K, V> pair = new Pair(currCont.key, currCont.value);
                result[nextResultIndex++] = pair;
            }
        }
        return result;
    }

    int getSize() {
        return totalItems;
    }

    public void printDebug() { // print hashTable content, see example below
        for (int i = 0; i < hashTable.length; i++) {
            System.out.printf("Entry %d:\n", i);
            for (Container<K, V> currCont = hashTable[i]; currCont != null; currCont = currCont.next) {
                System.out.printf("- Key %s, Value %s\n", currCont.key, currCont.value);
            }
        }
    }

    @Override
    public String toString() { // comma-separated key->value pair list
        // to be able to use this as validation in Unit tests keys must be sorted
        // e.g. [ Key1 => Val1, Key2 => Val2, ... ]
        var pairsArray = getAllKeyValPairs();
        Arrays.sort(pairsArray);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean first = true;
        for (var pair : pairsArray) {
            sb.append(first ? "" : ",");
            first = false;
            sb.append(pair.key).append("->").append(pair.val);
        }
        sb.append("]");
        return sb.toString();
    }
}
