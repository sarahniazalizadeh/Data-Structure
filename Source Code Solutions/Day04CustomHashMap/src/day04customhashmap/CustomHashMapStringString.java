package day04customhashmap;

import java.util.Arrays;
import java.util.Iterator;

public class CustomHashMapStringString implements Iterable<String> {

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            
            String [] keysArray = getAllKeys();
            int currentKeyIndex = 0;
            
            @Override
            public boolean hasNext() {
                return (currentKeyIndex < keysArray.length);
            }

            @Override
            public String next() {
                return keysArray[currentKeyIndex++];
            }
            
        };
    }

    private class Container {

        Container next;
        String key;
        String value;
    }

    // size must be a prime number always
    private Container[] hashTable = new Container[5];

    private int totalItems;

    private int computeHashValue(String key) { // hashing function
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            // hash *= 3;
            hash <<= 1;  // same as: hash *= ...
            char c = key.charAt(i);
            hash += c;
        }
        return hash;
    }

    void putValue(String key, String value) {
        // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length
        int hash = computeHashValue(key);
        int index = hash % hashTable.length;
        for (Container currCont = hashTable[index]; currCont != null; currCont = currCont.next) {
            if (currCont.key.equals(key)) { // found the same key, replace value
                currCont.value = value; // update existing entry
                return;
            }
        }
        // we only reach this place in code if key was NOT found
        Container newCont = new Container();
        newCont.key = key;
        newCont.value = value;
        // insert at the start of this hash table entry
        newCont.next = hashTable[index];
        hashTable[index] = newCont;
        totalItems++;
    }

    boolean hasKey(String key) {
        int hash = computeHashValue(key);
        int index = hash % hashTable.length;
        Container currCont = hashTable[index];
        while(currCont != null) {
            if (currCont.key.equals(key)) { // key found
                return true;
            }
            currCont = currCont.next; // go to next container
        }
        return false;
    }

    // throw custom unchecked KeyNotFoundException
    String getValue(String key) {
        int hash = computeHashValue(key);
        int index = hash % hashTable.length;
        // for (Container current = hashTable[index]; current != null; current = current.next) {
        Container currCont = hashTable[index];
        while(currCont != null) {
            if (currCont.key.equals(key)) {
                return currCont.value;
            }
            currCont = currCont.next; // go to next container
        }
        throw new NoSuchElementException();
    }

    // throw custom unchecked KeyNotFoundException
    void deleteByKey(String key) {
        int hash = computeHashValue(key);
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
        if (previous == null) { // removing the first item on the list (special case)
            hashTable[index] = current.next;
        } else { // remove one of the later items (common case)
            previous.next = current.next; // or: ...  = previous.next.next;
        }
        totalItems--;
    }

    public String[] getAllKeys() {
        String [] result = new String[totalItems];
        int nextResultIndex = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (Container currCont = hashTable[i]; currCont != null; currCont = currCont.next) {
                result[nextResultIndex++] = currCont.key;
            }
        }
        return result;
    }
    // Generic version: public K[] getAllKeys(K[] template) { ... }

    public Pair<String, String>[] getAllKeyValPairs() {
        Pair<String, String>[] result = new Pair[totalItems];
        int nextResultIndex = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (Container currCont = hashTable[i]; currCont != null; currCont = currCont.next) {
                Pair<String,String> pair = new Pair(currCont.key, currCont.value);
                result[nextResultIndex++] = pair;
            }
        }
        return result;
    }

    int getSize() {
        return totalItems;
    }

    public void printDebug() { // print hashTable content
        for (int i = 0; i < hashTable.length; i++) {
            System.out.printf("Entry %d:\n", i);
            for (Container currCont = hashTable[i]; currCont != null; currCont = currCont.next) {
                System.out.printf("- Key %s, Value %s\n", currCont.key, currCont.value);
            }
        }
    }

    @Override
    public String toString() { // comma-separated key->value pair list
        // to be able to use this as validation in Unit tests keys must be sorted
        // e.g. [ Key1 => Val1, Key2 => Val2, ... ]
        String [] keysArray = getAllKeys(); // O(n)
        Arrays.sort(keysArray);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < keysArray.length; i++) {
            String key = keysArray[i];
            String val = getValue(key); // O(1) repeated n times => O(n)
            sb.append(i == 0 ? "" : ",");
            sb.append(key).append("->").append(val);
        }
        sb.append("]");
        return sb.toString();
    }

}
