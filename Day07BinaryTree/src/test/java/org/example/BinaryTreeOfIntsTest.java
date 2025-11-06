package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeOfIntsTest {
    BinaryTreeOfInts tree = new BinaryTreeOfInts();

    @BeforeEach
    void setUp() {
        tree.put(40);
        tree.put(28);
        tree.put(31);
        tree.put(81);
        tree.put(17);
        tree.put(90);
        tree.put(38);
    }

    @Test
    void should_throwIllegalArgumentException_When_insertingDuplicate() {
        // given: setUp()
        // when
        Executable executable = () -> tree.put(31);
        // then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    void should_returnTrue_When_hasValue() {
        // given: setUp()
        // when
        boolean hasValue = tree.hasValue(38);
        // then
        assertTrue(hasValue);
    }

    @Test
    void should_returnFalse_When_valueNotExist() {
        // given: setUp()
        // when
        boolean hasValue = tree.hasValue(100);
        // then
        assertFalse(hasValue);
    }

    @Test
    void should_returnSumOfValues_When_sumValues() {
        // given: setUp()
        // when
        int sum = tree.getSumOfAllValues();
        // then
        assertEquals(325, sum);
    }

    @Test
    void should_returnInOrder_When_traverseInOrder() {
        // given: setUp()
        // when
        int[] inOrder = tree.getValuesInOrder();
        int[] expected = {90, 81, 40, 38, 31, 28, 17};
        // then
        assertArrayEquals(expected, inOrder);
    }
}