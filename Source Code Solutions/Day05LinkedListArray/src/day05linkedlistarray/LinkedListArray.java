package day05linkedlistarray;

public class LinkedListArray<T> {
    
    private class Container {
        Container next;
        T value;
    }

    private Container start, end;
    private int size;

    public void add(T value) { // O(1) - append
        Container newCont = new Container();
        newCont.value = value;
        if (size == 0) { // list empty - special case
            start = end = newCont;
            size = 1;
        } else { // list is not empty - general
            end.next = newCont;
            end = newCont;
            size++;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Container current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void insertValueAtIndex(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == 0 || index == size) {
            add(value);
            return;
        }
        Container newCont = new Container();
        newCont.value = value;
        if (index == 0) { // insert at the start of a non-empty list
            newCont.next = start;
            start = newCont;
            size++;
            return;
        }
        Container before = start;
        for (int i = 0; i < index - 1; i++) { // find previous item, just before index
            before = before.next;
        }
        newCont.next = before.next;
        before.next = newCont;
        size++;
    }

    public void replaceValueAtIndex(T value, int index) {
        throw new RuntimeException("Not implemented yet");
    } // put

    public void deleteRange(int startIndex, int count) { // ADVANCED
        throw new RuntimeException("Not implemented yet");
    }
    
    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) { // special case: removing the first item
            start = start.next;
            size--;
        } else {
            Container before = start;
            for (int i = 0; i < index - 1; i++) { // find previous item, just before index
                before = before.next;
            }
            // when removing the last item we must adjust "end" reference
            if (index == size - 1) {
                end = before;
            }
            //
            before.next = before.next.next;
            size--;
        }
        // nullify end if size 0 to help garbage collection
        if (size == 0) {
            end = null;
        }
    }

    public boolean deleteByValue(T value) { // delete first value found
        // ineffective and "lazy" way of implementing it
        int count = 0;
        Container current = start;
        while (current != null) {
            if (current.value.equals(value)) {
                deleteByIndex(count);
                return true;
            }
            current = current.next;
            count++;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() { // comma-separated values list similar to: [5,8,11]
        StringBuilder sb = new StringBuilder("[");
        Container current = start;
        // while (current != null) {
        for (int i = 0; i < size; i++) {
            sb.append(i == 0 ? "" : ",");
            sb.append(current.value.toString());
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public String toStringWhile() { // comma-separated values list similar to: [5,8,11]
        StringBuilder sb = new StringBuilder("[");
        boolean isFirst = true;
        for (Container current = start; current != null; current = current.next) {
            sb.append(isFirst ? "" : ",");
            isFirst = false;
            sb.append(current.value.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public T[] toArray(T[] template) { // could be used for Unit testing
        // T[] result = new T[size]; // this won't compile
        // T[] result = (T[]) new Object[size]; // will compile but then crash at runtime, ??? for some people this seems to work ???
        // T[] result = (T[]) Array.newInstance(template.getClass(), size); // fails on storing value into the array
        // T[] array = (T[]) java.lang.reflect.Array.newInstance(start.value.getClass(), size);  WORKS ???
        T[] result = (T[])java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        int i = 0;
        for (Container current = start; current != null; current = current.next) {
            result[i++] = current.value;
        }
        assert (i == size); // internal sanity check
        return result;
    }

}
