package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundFIFOQueueTest {

    class Person implements Comparable<Person> {
        @Override
        public int compareTo(Person o) {
            return this.name.compareTo(o.name);
        }
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            Person other = (Person) o;
            return (name.equals(other.name) && this.age == other.age);
        }
    }

    RoundFIFOQueue<Person> queue;
    final int MAX_SIZE = 5;

    Person alice = new Person("Alice", 30);
    Person bob = new Person("Bob", 25);
    Person april = new Person("April", 35);
    Person charlie = new Person("Charlie", 45);
    Person david = new Person("David", 50);

    Person zoe = new Person("Zoe", 20);
    Person duplicateAlice = new Person("Alice", 99);

    @BeforeEach
    void setUp() {
        queue = new RoundFIFOQueue<>(MAX_SIZE);
        queue.enqueue(alice, false);
        queue.enqueue(bob, false);
        queue.enqueue(april, false);
    }

    @Test
    void InitialStateAndBasicDequeueTest() {
        assertEquals(3, queue.size(), "3 nodes added during the setup");

        Person result1 = queue.dequeue();
        assertEquals(alice, result1, "Alice is the first node to be dequed");
        assertEquals(2, queue.size(), "Size should be down to 2 after first dequeue");

        Person result2 = queue.dequeue();
        assertEquals(bob, result2, "Bob must be the second node to be dequeued");
        assertEquals(april, queue.dequeue(), "Third dequeued node must be April");
        assertEquals(0, queue.size(), "Size must be 0 after removing all nodes");
    }

    @Test
    public void FullQueueTest() {
        queue.enqueue(charlie, false);
        queue.enqueue(david, false); //Queue should be full now

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals(2, queue.size(), "size should be down to 2");

        //Enqueue 3 new nodes
        final Person sarah = new Person("Sarah", 1);
        final Person neda = new Person("Neda", 2);
        final Person elham = new Person("Elham", 3);
        queue.enqueue(sarah, false);
        queue.enqueue(neda, false);
        queue.enqueue(elham, false);
        assertEquals(MAX_SIZE, queue.size(), "Queue should be full again");

        // verify final FIFO order: Charlie, David, Sarah, Neda, Elham
        assertEquals(charlie, queue.dequeue());
        assertEquals(david, queue.dequeue());
        assertEquals(sarah, queue.dequeue());
        assertEquals(neda, queue.dequeue());
        assertEquals(elham, queue.dequeue());
        assertEquals(0, queue.size(), "Queue should be empty.");
    }

    @Test
    public void EnqueueWhenFullThrowsExceptionTest() {
        queue.enqueue(charlie, false);
        queue.enqueue(david, false);   //Queue should be full now

        // try to enqueue without grow
        Executable executable = () -> queue.enqueue(zoe, false);

        assertThrows(RuntimeException.class, executable);
        assertEquals(MAX_SIZE, queue.size(), "Size should not change");
    }

    @Test
    public void EnqueueAndGrowTest() {
        queue.enqueue(charlie, false);
        queue.enqueue(david, false);   //Queue should be full now

        // add new node and grow queue
        queue.enqueue(zoe, true);

        assertEquals(MAX_SIZE + 1, queue.size(), "size should increase to 6");
    }

    @Test
    public void DequeueFromEmptyQueueThrowsExceptionTest() {
        queue.dequeue();
        queue.dequeue();
        queue.dequeue(); //queue should be empty now

        Executable executable = () -> queue.dequeue();

        assertThrows(RuntimeException.class, executable);
    }


    @Test
    public void ToArrayTest() {
        Person[] result = queue.toArray(new Person[0]);

        //expected order: April, Bob, Alice (Alice is the oldest)
        Person[] expected = new Person[]{april, bob, alice};

        assertArrayEquals(expected, result);
    }

    @Test
    public void CountValuesTest() {
        queue.enqueue(duplicateAlice, false); //same name, different age
        queue.enqueue(zoe, false);

        assertEquals(5, queue.size());

        //there are 2 Alice nodes:
        assertEquals(2, queue.countValues(alice));

        //April is unique
        assertEquals(1, queue.countValues(april));

        // Charlie doesn't exist in the queue
        assertEquals(0, queue.countValues(charlie));
    }

    @Test
    public void IteratorOrderIsFIFOTest() {
        //current nodes:: [Alice, Bob, April]
        List<Person> iteratedOrder = new ArrayList<>();

        for (Person p : queue) {
            iteratedOrder.add(p);
        }

        List<Person> expectedOrder = List.of(alice, bob, april);
        assertEquals(expectedOrder, iteratedOrder);
        assertEquals(3, queue.size());
    }
}