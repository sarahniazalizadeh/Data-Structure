package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashMapStringStringTest {
    CustomHashMapStringString myHashTable;

    @BeforeEach
    void setUp() {
        myHashTable = new CustomHashMapStringString();
        myHashTable.putValue("paint", "red");
        myHashTable.putValue("apple", "green");
        myHashTable.putValue("sky", "blue");
        myHashTable.putValue("banana", "yellow");
        myHashTable.putValue("cloud", "white");
    }

    @Test
    void should_replaceValue_When_keyExists() {
        // given: setup()
        // when
        myHashTable.putValue("apple", "red");
        String value = myHashTable.getValue("apple");
        // then
        assertEquals("red", value);
    }

    @Test
    void should_returnTrue_When_hasKey() {
        // given: setup()
        // when
        boolean hasKey = myHashTable.hasKey("banana");
        // then
        assertTrue(hasKey);
    }

    @Test
    void should_returnFalse_When_KeyNotExist() {
        // given: setup()
        // when
        boolean hasKey = myHashTable.hasKey("earth");
        // then
        assertFalse(hasKey);
    }

    @Test
    void should_throwKeyNotFoundException_When_getValueWithNonExistingKey() {
        // given: setup()
        // when
        Executable executable = () -> myHashTable.getValue("orange");
        //then
        assertThrows(KeyNotFoundException.class, executable);
    }


    @Test
    void should_RemoveFirstValueAtIndex_When_KeyExist() {
        // given: setup()
        // when
        myHashTable.deleteByKey("paint");
        String expected = "[ apple => green, banana => yellow, cloud => white, sky => blue ]";
        //then
        assertEquals(expected, myHashTable.toString());
    }

    @Test
    void should_RemoveSecondValueAtIndex_When_KeyExist() {
        // given: setup()
        // when
        myHashTable.deleteByKey("sky");
        String expected = "[ apple => green, banana => yellow, cloud => white, paint => red ]";
        //then
        assertEquals(expected, myHashTable.toString());
    }

    @Test
    void should_ThrowException_When_KeyNotFoundInDeleteByKey() {
        // given: setup()
        // when
        Executable executable = () -> myHashTable.deleteByKey("ocean");
        //then
        assertThrows(KeyNotFoundException.class, executable);
    }

    @Test
    void should_returnAllKeys_When() {
        // given: setup()
        // when
        String[] keys = myHashTable.getAllKeys();
        String[] expected = new String[]{"apple", "banana", "cloud", "paint", "sky"};
        // then
        assertArrayEquals(expected, keys);
    }

//    @Test
//    void should_returnKeyValPais() {
//        // given: setup()
//        // when
//        Pair<String,String>[] actualValues = myHashTable.getAllKeyValPairs();
//        Set<Pair<String,String>> actualSet = new HashSet<>(Arrays.asList(actualValues));
//        Set<Pair<String,String>> expectedSet = new HashSet<>(Arrays.asList(
//                new Pair<>("paint", "red"),
//                new Pair<>("apple", "green"),
//                new Pair<>("sky", "blue"),
//                new Pair<>("banana", "yellow"),
//                new Pair<>("cloud", "white")
//        ));
//        // then
//        assertEquals(expectedSet, actualSet);
//    }

    @Test
    void should_returnSize() {
        // given: setup()
        // when
        int size = myHashTable.getSize();
        // then
        assertEquals(5, size);
    }

}