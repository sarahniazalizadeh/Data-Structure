package org.example;

public class LinkedListArray<T> {

    private class Container {
        Container next;
        T value;
    }

    private Container start, end;
    private int size;

    public void add(T value) {
        Container newContainer = new Container();
        newContainer.value = value;
        if (size == 0) {
            start = newContainer;
            end = newContainer;
        } else {
            end.next = newContainer;
            end = newContainer;
        }
        size++;
    }

    public T get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        Container current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void insertValueAtIndex(T value, int index) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        Container newContainer = new Container();
        newContainer.value = value;
        if (index == 0) {
            newContainer.next = start;
            start = newContainer;
            if( size == 0) {
                end = newContainer;
            }
            size++;
            return;
        }
        if (index == size) {
            end.next = newContainer;
            end = newContainer;
            size++;
            return;
        }
        Container previous = start;
        for (int i = 0; i < index-1; i++) {
            previous = previous.next;
        }
        newContainer.next = previous.next;
        previous.next = newContainer;
        size++;
    }

    public void replaceValueAtIndex(T value, int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        Container current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.value = value;
    } // put

    public void deleteByIndex(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) {
            Container oldStart = start;
            start = start.next;
            oldStart.next = null;
            size--;
            if (size == 0) {
                end = null;
            }
            return;
        }
        Container current = start.next;
        Container previous = start;
        for (int i = 0; i < index-1; i++) {
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        current.next = null;
        size--;
        if (previous.next == null) {
            end = previous;
        }
    }


    public boolean deleteByValue(T value) {
        if (start == null) return false;
        if (start.value.equals(value)) {
            Container oldStart = start;
            start = start.next;
            oldStart.next = null;
            size--;
            if (size == 0) {
                end = null;
            }
            return true;
        }
        Container current = start.next;
        Container previous = start;
        while (current != null) {
            if (current.value.equals(value)) {
                previous.next = current.next;
                current.next = null;
                size--;
                if (previous.next == null) {
                    end = previous;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    } // delete first value found

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String result = "[";
        Container current = start;
        while(current != null) {
            result += current.value;
            if (current.next != null) {
                result += ", ";
            }
            current = current.next;
        }
        result += "]";
        return result;
    } // comma-separated values list similar to: [5,8,11]

    public T[] toArray(T[] template) {
        // T[] result = new T[size]; // this won't compile
        // T[] result = (T[]) new Object[size]; // will compile but then crash at runtime
        // T[] result = Arrays.newInstance(template.getClass(), size); // fails on storing value into the array
        T[] result = (T[])java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        Container current = start;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
    } // could be used for Unit testing

}
