package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListArrayOfStringsTest {

    LinkedListArrayOfStrings list;

    @BeforeEach
    void setUp() {
        list = new LinkedListArrayOfStrings();
    }

    @Test
    void should_AddAtEnd_When_AddingValues() {
        // given
        int size = 0;
        // when
        list.add("A");
        list.add("B");
        list.add("C");
        // then
        assertAll(
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[A, B, C]", list.toString())
        );
    }

    @Test
    void should_GetValueAtIndex_When_ValidIndex() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        String result = list.get(1);
        // then
        assertEquals("B", result);
    }

    @Test
    void should_ThrowException_When_GetMethodIndexOutOfBounds() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        Executable executable = () -> list.get(5);
        // then
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    void should_InsertValueAtIndex_When_ValidIndex() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        list.insertValueAtIndex("X", 1);
        // then
        assertAll(
                () -> assertEquals(4, list.getSize()),
                () -> assertEquals("[A, X, B, C]", list.toString())
        );
    }

    @Test
    void should_ThrowException_When_InsertMethodIndexOutOfBounds() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        Executable executable = () -> list.insertValueAtIndex("X", -1);
        // then
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    void should_ReplaceValueAtIndex_When_ValidIndex() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        list.replaceValueAtIndex("X", 1);
        // then
        assertAll(
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[A, X, C]", list.toString())
        );
    }

    @Test
    void should_deleteByIndex_When_ValidIndex() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        list.deleteByIndex(1);
        // then
        assertAll(
                () -> assertEquals(2, list.getSize()),
                () -> assertEquals("[A, C]", list.toString())
        );
    }

    @Test
    void should_deleteFirstFound_When_ValueExists() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("B");
        // when
        boolean result = list.deleteByValue("B");
        // then
        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[A, C, B]", list.toString())
        );
    }

    @Test
    void should_stayTheSame_When_ValueNotExists() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        boolean result = list.deleteByValue("X");
        // then
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(3, list.getSize()),
                () -> assertEquals("[A, B, C]", list.toString())
        );
    }

    @Test
    void should_returnEmptyArray_When_AllItemsDeleted() {
        // given
        list.add("A");
        list.add("B");
        list.add("C");
        // when
        list.deleteByIndex(0);
        list.deleteByValue("B");
        list.deleteByIndex(0);
        // then
        assertAll(
                () -> assertEquals(0, list.getSize()),
                () -> assertEquals("[]", list.toString())
        );
    }
}