package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BinaryTreeTestOfPerson {
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

    BinaryTree<Person, String> tree;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Person alice = new Person("Alice", 30);
    Person bob = new Person("Bob", 25);
    Person april = new Person("April", 35);
    Person charlie = new Person("Charlie", 45);
    Person david = new Person("David", 50);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));

        tree = new BinaryTree<>();
        tree.put(alice, "Engineer");
        tree.put(bob, "Designer");
        tree.put(april, "Manager");
        tree.put(charlie, "Analyst");
        tree.put(david, "Consultant");
    }

    @AfterEach
    void restoreStreams() {
        // Restore System.out after each test
        System.setOut(originalOut);
    }

    @Test
    void should_addPersonsToTree() {
        // given: setUp()
        Person sarah = new Person("Sarah", 36);
        // when
        tree.put(sarah, "Developer");
        // then
        assertEquals("Engineer", tree.getValByKey(alice));
        assertEquals("Designer", tree.getValByKey(bob));
        assertEquals("Manager", tree.getValByKey(april));
        assertEquals("Developer", tree.getValByKey(sarah));
    }

    @Test
    void should_getPersonValue() {
        // given: setUp()
        // when
        String role = tree.getValByKey(bob);
        // then
        assertEquals("Designer", role);
    }

    @Test
    void should_throwException_When_personNotExist() {
        // given: setUp()
        Person sarah = new Person("Sarah", 28);
        // when
        Executable executable = () -> tree.getValByKey(sarah);
        // then
        assertThrows(RuntimeException.class, executable);
    }

    @Test
    void should_updateValue_When_keyAlreadyExists() {
        // Given
        assertEquals("Designer", tree.getValByKey(bob));

        // When
        Person newBob = new Person("Bob", 99);
        tree.put(newBob, "UPDATED CEO");

        // Then
        assertEquals("UPDATED CEO", tree.getValByKey(bob));
        assertEquals(5, tree.getSize(), "Size should remain 5 after value update.");
    }

    @Test
    void should_iterateInOrder() {
        // Given
        List<String> namesInOrder = new ArrayList<>();

        // When
        for (Pair<Person, String> pair : tree) {
            namesInOrder.add(pair.key.name);
        }

        // Then
        List<String> expectedOrder = List.of("Alice", "April", "Bob", "Charlie", "David");
        assertEquals(expectedOrder, namesInOrder, "Iterator must return keys in alphabetical order.");
    }

    @Test
    void should_printKeyValPairs() {
        // given: setUp()
        // when
        tree.printAllKeyValPairs();
        // then
        String ls = System.lineSeparator();
        String expectedOutput = "Alice -Left-> April => Manager" +
                "Alice => Engineer" +
                "Alice -Right-> Bob => Designer" +
                "Alice -Right-> Bob -Right-> Charlie => Analyst" +
                "Alice -Right-> Bob -Right-> Charlie -Right-> David => Consultant";

        String actualOutput = outContent.toString().trim();
        assertEquals(expectedOutput.trim(), actualOutput, "Path printing output does not match expected format.");
    }
}