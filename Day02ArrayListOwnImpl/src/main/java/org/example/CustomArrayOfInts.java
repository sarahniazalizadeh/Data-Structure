package org.example;

public class CustomArrayOfInts {

    private int[] data = new int[2]; // only grows by doubling size, never shrinks
    private int size = 0; // how many items do you really have

    public int size() {
        return size;
    }

    public void ensureCapacity() {
        if (size == data.length) {
            int[] newData = new int[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    public void add(int value) {
        ensureCapacity();
        data[size++] = value;
    }

    public void deleteByIndex(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
    }

    public boolean deleteByValue(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    } // delete first value matching, true if value found, false otherwise

    public void insertValueAtIndex(int value, int index) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
        ensureCapacity();
        for (int i = size + 1; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    public void clear() {
        size = 0;
    }

    public int get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return data[index];
    } // may throw ArrayIndexOutOfBoundsException

    public int[] getSlice(int startIdx, int length) {
        if (startIdx >= size || startIdx < 0 || startIdx + length > size) throw new IndexOutOfBoundsException();
        int[] slicedData = new int[length];
        for (int i = startIdx; i < startIdx + length; i++) {
            slicedData[i - startIdx] = data[i];
        }
        return slicedData;
    } // may throw ArrayIndexOutOfBoundsException

    public int[] toArray() {
        int[] arrayCopy = new int[size];
        for (int i = 0; i <= size; i++) {
            arrayCopy[i] = data[i];
        }
        return arrayCopy;
    } // ensure no reference leak

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            result += (i == size) ? "" : "," + data[i];
        }
        result += "]";
        return result;
    } // returns String similar to: [3, 5, 6, -23]
}
