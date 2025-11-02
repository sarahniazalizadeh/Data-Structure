package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityStack<T> implements Iterable<T> {

    // you may add a constructor if you like (but I don't see why)
    private T[] arrayTemplate;

    public PriorityStack() {
        this.arrayTemplate = null;
    }

    public PriorityStack(T[] template) {
        this.arrayTemplate = template;
    }

    private class Container {
        // add a constructor and/or toString if you like
        T value;
        boolean hasPriority;
        Container nextBelow;
// you are NOT allowed to add "previousAbove" reference
    }


    private Container top; // top of the stack element
    private int size;


    public void push(T value) {
        push(value, false);
    }


    public void push(T value, boolean hasPriority) {
        Container newContainer = new Container();
        newContainer.value = value;
        newContainer.hasPriority = hasPriority;
        newContainer.nextBelow = top;
        top = newContainer;
        size++;
    }


    public T pop() {
        // remove and return the top item
        // if no item found (size == 0) then throw NoSuchElementException
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T returnValue = top.value;
        top = top.nextBelow;
        size--;
        return returnValue;
    }


    public T popPriority() {
        // find an item with priority starting from the top, remove it and return it
        // if no priority item found then remove and return the top item
        // if stack is empty then throw NoSuchElementException
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Container current = top;
        Container previous = null;
        while (current != null) {
            if (current.hasPriority) {
                T returnValue = current.value;
                if (previous == null) {
                    top = current.nextBelow;
                } else {
                    previous.nextBelow = current.nextBelow;
                    current.nextBelow = null;
                }
                size--;
                return returnValue;
            } else {
                previous = current;
                current = current.nextBelow;
            }
        }
        T returnValue = top.value;
        top = top.nextBelow;
        size--;
        return returnValue;
    }


    public int hasValue(T value) {
        // this code only looks for the *first* occurence of the value, starting from top
        // WARNING: you must call value.equals(item.value) to determine whether
        // two values are equal, just like you would do for a String
        // returning value 0 means the value is on top of the stack,
        // 1 means 1 below the top, and so on...
        // returns -1 if value is not on the stack
        Container current = top;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            current = current.nextBelow;
            index++;
        }
        return -1;
    }


    public T removeValue(T value) {
        // removes the first item from top containing the value and returns the value
        // if item with value is not found throw NoSuchElementException
        Container current = top;
        Container previous = null;
        while (current != null) {
            if (current.value.equals(value)) {
                T returnValue = current.value;
                if (previous == null) {
                    top = current.nextBelow;
                } else {
                    previous.nextBelow = current.nextBelow;
                    current.nextBelow = null;
                }
                size--;
                return returnValue;
            } else {
                previous = current;
                current = current.nextBelow;
            }
        }
        throw new NoSuchElementException();
    }


    public int getSize() {
        return size;
    }


    public void reorderByPriority() {
        // reorder items (re-create a new stack, if you like)
        // where all priority items are on top and non-priority items are below them
        // Note: order within the priority items group and non-priority items group must remain the same
        // Suggestion: instead of reordering the existing stack items
        // it may be easier to re-create a new stack with items in the order you need
        if (size <= 1) {
            return;
        }
        PriorityStack<T> priorityGroup = new PriorityStack<>();
        PriorityStack<T> nonPriorityGroup = new PriorityStack<>();
        PriorityStack<T> tempFinalStack = new PriorityStack<>();
        Container current = top;
        while (current != null) {
            if (current.hasPriority) {
                priorityGroup.push(current.value, true);
            } else {
                nonPriorityGroup.push(current.value, false);
            }
            current = current.nextBelow;
        }
        PriorityStack<T> reverseNonPriority = new PriorityStack<>();
        while (nonPriorityGroup.getSize() > 0) {
            reverseNonPriority.push(nonPriorityGroup.pop(), false);
        }
        PriorityStack<T> reversePriority = new PriorityStack<>();
        while (priorityGroup.getSize() > 0) {
            reversePriority.push(priorityGroup.pop(), true);
        }
        while (reversePriority.getSize() > 0) {
            tempFinalStack.push(reversePriority.pop(), true);
        }
        while (reverseNonPriority.getSize() > 0) {
            tempFinalStack.push(reverseNonPriority.pop(), false);
        }
        top = null;
        size = 0;
        while (tempFinalStack.getSize() > 0) {
            boolean hasPriority = tempFinalStack.top.hasPriority;
            T value = tempFinalStack.pop();
            this.push(value, hasPriority);
        }
    }


    @Override
    public String toString() {
        // return string describing the contents of the stack, starting from the top
        // Use value.toString() to conver values kept in the stack to strings.
        // Format exactly like this (assuming T is a String to keep it simple):
        // "[Jerry:N,Terry:N,Martha:P,Tom:P,Jimmy:N]"
        // N means item has no priority, P means item has priority
        // For full marks you must use StringBuilder or StrigBuffer, no + (string concatenation) allowed.
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Container current = top;
        while (current != null) {
            sb.append(current.value.toString());
            sb.append(":");
            sb.append(current.hasPriority ? "P" : "N");
            if (current.nextBelow != null) {
                sb.append(",");
            }
            current = current.nextBelow;
        }
        sb.append("]");
        return sb.toString();
    }


    // you may need these fields to implement toArrayReversed
    private T[] reversed;
    private int reversedCount;


    public T[] toArrayReversed(T[] template/* may need a parameter - template type */) { // Note: this is "the twist"
        // return array with items on the stack
        // WARNING: element 0 of the array must be the BOTTOM of the stack
        // NOTE: To obtain full marks for this method you must use recursion.
        // Collect items on your way back, just before returning from each method call.
        // This case is similar to when constructors of parent classes are called (Programming II course).
        T[] result = (T[]) java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        if (size == 0) {
            return result;
        }
        this.reversed = result;
        this.reversedCount = 0;
        toArrayReversedRecursive(top);
        return this.reversed;
    }

    private void toArrayReversedRecursive(Container current) {
        if (current == null) {
            return;
        }
        toArrayReversedRecursive(current.nextBelow);
        reversed[reversedCount] = current.value;
        reversedCount++;
    }

    // NOTE: *ONLY* implement this method if you can't implement toArrayReversed.
// Make sure to add a unit test for it later.
//    public T[] toArray() {
//    }


    // NOTE: you are only allowed to add private methods and private fields (if needed)

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Container current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.nextBelow;
                return value;
            }
        };
    }
}


// Midterm TODO: to obtain full marks you must also make class PriorityStack<T> implement Iterable<T> so that it works with for-each loop
// For this to work you'll need to instantiate an new array of T, so you'll need a template.
// You'll need to add a constructor that takes a template as a parameter, then saves it in a private field for future use by iterator and toArrayReversed.

//public PriorityStack(T[] template) {
//    arrayTemplate = template;
//}
//
//private T[] arrayTemplate;