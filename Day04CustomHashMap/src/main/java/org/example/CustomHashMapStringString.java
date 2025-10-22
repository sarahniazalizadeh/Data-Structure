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

        Container current = hashTable[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // key not found, insert new container at the beginning
        Container newContainer = new Container();
        newContainer.key = key;
        newContainer.value = value;
        newContainer.next = hashTable[index];
        hashTable[index] = newContainer;
        totalItems++;
    }
    // LATER: expand hashTable by about *2 when totalItems > 3*hashTable.length

//    boolean hasKey(String key) { }

    // throw custom unchecked KeyNotFoundException
//    String getValue(String key) {
//
//    }

    // throw custom unchecked KeyNotFoundException
    void deleteByKey(String key) { }

//    public String[] getAllKeys() {
//
//    }
    // Generic version: public K[] getAllKeys(K[] template) { ... }

//    public Pair<String,String>[] getAllKeyValPairs() {
//        Pair<String,String> [] result = new Pair[totalItems];
//
//
//    }


    int getSize() { return totalItems; }

    public void printDebug() {
        for(int i=0; i < hashTable.length; i++){
            System.out.println(i + ": ");
            Container temp = hashTable[i];
            while(temp != null){
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
