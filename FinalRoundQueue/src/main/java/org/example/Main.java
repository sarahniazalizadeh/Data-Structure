package org.example;

public class Main {
    public static void main(String[] args) {
        RoundFIFOQueue<String> queue = new RoundFIFOQueue<String>(5);
        queue.enqueue("A", false);
        queue.enqueue("B", false);
        queue.enqueue("C", false);
        queue.enqueue("D", false);
        queue.enqueue("E", false);
        queue.dequeue();
        queue.enqueue("F", false);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("G", false);
        queue.enqueue("H", false);
        queue.enqueue("I", false);

        queue.printQueue();
    }
}