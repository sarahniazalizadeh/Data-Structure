package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

class RoundFIFOQueue<T extends Comparable> implements Iterable<T> {

    private class Node {
        T value;
        Node next;
        int debugId; // optional, for debugging only
    }

    private Node enqueue, dequeue;
    private int size;
    private int maxSize; // maxSize is assigned in the constructor

    class RoundFIFOQueueIterator implements Iterator<T> {
        private Node current;
        private int count;

        public RoundFIFOQueueIterator() {
            this.current = dequeue;
            this.count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Reached end of queue");
            }
            T value = current.value;
            current = current.next;
            count++;
            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundFIFOQueueIterator();
    }


    public RoundFIFOQueue(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be positive.");
        }
        this.maxSize = maxSize;
        this.size = 0;
        // * allocate maxSize of Nodes and as you're doing it
        // * link them up with next references
        // * point enqueue & dequeue to one of these nodes
        Node lastNode = null;
        for (int i = 0; i < maxSize; i++) {
            Node newNode = new Node();
            newNode.debugId = i;
            if (i == 0) {
                enqueue = newNode;
            } else {
                lastNode.next = newNode;
            }
            lastNode = newNode;
        }
        dequeue = enqueue;
        lastNode.next = dequeue;
    }

    /// / Suggestion: for debugging purposes give each Node a debugId (0, 1, 2...)
    /// / so that when you're looking at data in memory or printing them out
    /// / you can tell which Node is which
    /// /
    /// / Suggestion: use debugger to verify that you indeed have linked up all nodes in a circle
    /// / before continuing with this task. If you did not then the rest of your code won't work.


    public void printQueue() {
        Node current = enqueue;
        for (int i = 0; i < maxSize; i++) {
            System.out.println("Node #" + i + "(" + current.debugId + "): " + current.value);
            current = current.next;
        }
        if (current != enqueue) {
            throw new RuntimeException("Internal error: queue may not be circular");
        }
        // for debugging only but required as a part of your solution
        // print out each Node's content on a separate line
        // starting with eneuque reference, following next reference maxSize (or probably maxSize-1) times.
        // after the loop is done you should be back at eneuque - check that and if you're not
        // throw new RuntimeException("Internal error: queue may not be circular");
        // example line of output for node with debugId=2, value="Joe" (String type):
        // Node #0(2): Joe
    }

    /// /     puts value into the queue
    /// / throws RuntimeException("No space in the queue") if queue is full
    /// / unless "grow" is set to true, in which case you can create a new Node so you can insert more than current maxSize
    /// / if you do - you need to update maxSize as well.
    public void enqueue(T value, boolean grow) {
        if (size == maxSize) {
            if (!grow) {
                throw new RuntimeException("No space in the queue");
            }
            Node newNode = new Node();
            newNode.debugId = maxSize;
            Node prev = dequeue;
            for (int i = 0; i < maxSize - 1; i++) {
                prev = prev.next;
            }
            newNode.next = enqueue;
            prev.next = newNode;
            maxSize++;
            enqueue = newNode;
        }
        enqueue.value = value;
        enqueue = enqueue.next;
        size++;
    }

    /// / removes and returns value from the queue (in FIFO order)
    /// / throws RuntimeException("Queue is empty") if queue is empty
    /// / sets value reference to null of the Node whose value will be returned
    public T dequeue() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty");
        }
        T value = dequeue.value;
        dequeue.value = null;
        dequeue = dequeue.next;
        size--;
        return value;
    }

    public int size() {
        return size;
    }

    /// / returns array of "size" number of elements with most recently enqueued elements first (following "next" references)
    public T[] toArray(T[] template) {
        if (template.length < size) {
            template = (T[]) java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        }
        T[] result = template;
        Node current = dequeue;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        for (int i = 0; i < size / 2; i++) {
            T temp = result[i];
            result[i] = result[size - 1 - i];
            result[size - 1 - i] = temp;
        }
        for (int i = size; i < result.length; i++) {
            result[i] = null;
        }
        return result;
    }

    /// / checks if value is present in Queue and returns the number of occurrences of that value (0 if not found)
    /// / must use n.compareTo(v) == 0, not equals()
    public int countValues(T value) {
        int count = 0;
        Node current = dequeue;
        for (int i = 0; i < size; i++) {
            if (current.value != null && current.value.compareTo(value) == 0) {
                count++;
            }
            current = current.next;
        }
        return count;
    }
//// also implement methods required by Iterable interface (and test it in Unit tests)
}