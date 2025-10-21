package org.example;

public class CustomArrayOfInts {

    private int [] data = new int[2]; // only grows by doubling size, never shrinks
    private int size = 0; // how many items do you really have

    public int size() { return size; }
    public void add(int value) {

    }

    public void deleteByIndex(int index) {

    }

    public boolean deleteByValue(int value) {

    } // delete first value matching, true if value found, false otherwise

    public void insertValueAtIndex(int value, int index) {

    }

    public void clear() { size = 0; }

    public int get(int index) {

    } // may throw ArrayIndexOutOfBoundsException

    public int[] getSlice(int startIdx, int length) {

    } // may throw ArrayIndexOutOfBoundsException

    public int[] toArray() {

    } // ensure no reference leak

    @Override
    public String toString() {

    } // returns String similar to: [3, 5, 6, -23]
}
