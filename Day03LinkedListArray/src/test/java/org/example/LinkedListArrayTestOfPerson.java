package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListArrayTestOfPerson {

    class Person {

        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("Person(%s:%d)", name, age);
        }

        @Override
        public boolean equals(Object o) {
            Person other = (Person) o;
            return (name.equals(other.name) && this.age == other.age);
        }
    }

    LinkedListArray<Person> list = new LinkedListArray<>();

    @BeforeEach
    void setUp() {
        list = new LinkedListArray<>();
    }

    @Test
    void should_AddAtEnd_When_AddingValues() {
        // given
        int size = 0;
        Person[] expected = new Person[]{new Person("A", 15), new Person("B", 20), new Person("C", 35)};
        // when
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // then
        assertAll(
                () -> assertEquals(3, list.getSize()),
                () -> assertArrayEquals(expected, list.toArray(new Person[0]))
        );
    }

    @Test
    void should_GetValueAtIndex_When_ValidIndex() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        Person result = list.get(1);
        Person expected = new Person("B", 20);
        // then
        assertEquals(expected, result);
    }

    @Test
    void should_ThrowException_When_GetMethodIndexOutOfBounds() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        Executable executable = () -> list.get(5);
        // then
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    void should_InsertValueAtIndex_When_ValidIndex() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        list.insertValueAtIndex(new Person("X", 27), 1);
        // then
        assertAll(
                () -> assertEquals(4, list.getSize()),
                () -> assertEquals("[Person(A:15), Person(X:27), Person(B:20), Person(C:35)]", list.toString())
        );
    }

    @Test
    void should_ThrowException_When_InsertMethodIndexOutOfBounds() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        Executable executable = () -> list.insertValueAtIndex(new Person("X", 27), -1);
        // then
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    void should_ReplaceValueAtIndex_When_ValidIndex() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        list.replaceValueAtIndex(new Person("X", 27), 1);
        // then
        assertAll(
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[Person(A:15), Person(X:27), Person(C:35)]", list.toString())
        );
    }

    @Test
    void should_deleteByIndex_When_ValidIndex() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        list.deleteByIndex(1);
        // then
        assertAll(
                () -> assertEquals(2, list.getSize()),
                () -> assertEquals("[Person(A:15), Person(C:35)]", list.toString())
        );
    }

    @Test
    void should_deleteFirstFound_When_ValueExists() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        list.add(new Person("B", 20));
        // when
        boolean result = list.deleteByValue(new Person("B", 20));
        // then
        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[Person(A:15), Person(C:35), Person(B:20)]", list.toString())
        );
    }

    @Test
    void should_stayTheSame_When_ValueNotExists() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        boolean result = list.deleteByValue(new Person("X", 27));
        // then
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[Person(A:15), Person(B:20), Person(C:35)]", list.toString())
        );
    }

    @Test
    void should_returnEmptyArray_When_AllItemsDeleted() {
        // given
        list.add(new Person("A", 15));
        list.add(new Person("B", 20));
        list.add(new Person("C", 35));
        // when
        list.deleteByIndex(0);
        list.deleteByValue(new Person("B", 20));
        list.deleteByIndex(0);
        // then
        assertAll(
                () -> assertEquals(0, list.getSize()),
                () -> assertEquals("[]", list.toString())
        );
    }
}