package org.example;

public class CustomHashMapStringString {

    private int size = 7;
    private Container[] hashTable;

    private class Container {
        Container next;
        String key;
        String value;
    }

    // size must be a prime number always

    public CustomHashMapStringString() {
        hashTable = new Container[size];
    }

    private int totalItems;

    private int computeHashValue(String key) { // hashing function
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash <<= 2;  // same as: hash *= 4
            char c = key.charAt(i);
            hash += c;
        }
        return hash;
    }

    void putValue(String key, String value) {
        int hashValue = computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;

        if (hashTable[index] == null) {
            Container newContainer = new Container();
            newContainer.key = key;
            newContainer.value = value;
            hashTable[index] = newContainer;
            totalItems++;
            return;
        }
        Container current = hashTable[index];
        Container previous = null;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            previous = current;
            current = current.next;
        }
        Container newContainer = new Container();
        newContainer.value = value;
        newContainer.key = key;
        previous.next = newContainer;
        totalItems++;
    }
    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length

    boolean hasKey(String key) {
        int hashValue = computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;
        Container temp = hashTable[index];
        while(temp!=null) {
            if(temp.key.equals(key)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // throw custom unchecked KeyNotFoundException
    String getValue(String key) {
        int hashValue = computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;
        Container temp = hashTable[index];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        throw new KeyNotFoundException("Key not found: " + key);
    }    // throw custom unchecked KeyNotFoundException

    void deleteByKey(String key) {
        int hashValue = computeHashValue(key);
        int index = Math.abs(hashValue) % hashTable.length;
        if (hashTable[index] == null) {
            return;
        }
        Container temp = hashTable[index];
        Container pre = null;
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
    }

    public String[] getAllKeys() {
        String[] result = new String[totalItems];
        int counter = 0;
        for (int i=0; i < size; i++) {
            if(hashTable[i] != null) {
                Container temp = hashTable[i];
                while (temp != null) {
                    result[counter++] =temp.key;
                    temp = temp.next;
                }
            }
        }
        return result;
    }
    // Generic version: public K[] getAllKeys(K[] template) { ... }

    public Pair<String,String>[] getAllKeyValPairs() {
        Pair<String,String> [] result = new Pair[totalItems];
        int counter = 0;
        for(int i=0;i<size;i++) {
            if(hashTable[i]!=null) {
                Container temp = hashTable[i];
                while(temp!=null) {
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
            Container temp = hashTable[i];
            while (temp != null) {
                System.out.println("   { " + temp.key + " : " + temp.value + " }");
                temp = temp.next;
            }
        }
    } // print hashTable content, see example below

//    @Override
//    public String toString() {
//
//    } // comma-separated key->value pair list
    // to be able to use this as validation in Unit tests keys must be sorted
    // e.g. [ Key1 => Val1, Key2 => Val2, ... ]

}
