package day04arraylistownimpl;

public class CustomArrayOfInts {

    private int[] data = new int[2]; // only grows by doubling size, never shrinks
    private int size = 0; // how many items do you really have

    public int size() {
        return size;
    }

    public void add(int value) {
        ensureSpaceForOneMoreItem();
        data[size++] = value;
    }
    
    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size-1; i++) {
            // i is destination index for a copied value
            data[i] = data[i+1];
        }
        size--;
    }

    public boolean deleteByValue(int value) {
        // delete first value matching, true if value found, false otherwise
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                deleteByIndex(i);
                return true;
            }
        }
        return false;
    }

    public void insertValueAtIndex(int value, int index) {
        ensureSpaceForOneMoreItem();
        if (index < 0 || index > size) { // careful with the off by one error ?
            throw new IndexOutOfBoundsException();
        }
        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = value;
        size++;
    }

    public void clear() {
        size = 0;
    }

    public int get(int index) {
        // may throw ArrayIndexOutOfBoundsException
        if (index < 0 || index >= size) { // careful with the off by one error ?
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    public int[] getSlice(int startIdx, int length) {
        // may throw ArrayIndexOutOfBoundsException
        if (startIdx < 0 || startIdx >= size || length < 0 || startIdx + length > size) {
            throw new IndexOutOfBoundsException();
        }
        int[] result = new int[length];
        for (int i = startIdx; i < startIdx+length; i++) {
            result[i-startIdx] = data[i];
        }
        return result;
    }

    public int[] toArray() { // return a copy, always
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }

    @Override
    public String toString() {
        // returns String similar to: [3, 5, 6, -23]
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(i == 0 ? "" : ",");
            sb.append(data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void ensureSpaceForOneMoreItem() {
        if (size == data.length) {
            // array is full - grow it
            int[] newData = new int[data.length * 2]; // new array is double the size of current one
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }
}
