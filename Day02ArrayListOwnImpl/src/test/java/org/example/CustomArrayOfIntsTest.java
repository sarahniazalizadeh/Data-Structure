package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayOfIntsTest {

    private CustomArrayOfInts customArrayOfInts;

    @BeforeEach
    void setUp() {
        this.customArrayOfInts = new CustomArrayOfInts();
    }


    @Test
    void should_AddToCurrentArray_When_SizeLessThanArrayLength() {
        // given
        customArrayOfInts.add(1);
        // when
        customArrayOfInts.add(2);
        // then
        assertEquals(2, customArrayOfInts.size(), "Size should not change");
    }

    @Test
    void should_DoubleArraySize_When_SizeEqualToArrayLength() {
        // given
        customArrayOfInts.add(1);
        customArrayOfInts.add(2);
        // when
        customArrayOfInts.add(3);
        // then
        assertAll(
                () -> assertEquals(1, customArrayOfInts.get(0)),
                () -> assertEquals(2, customArrayOfInts.get(1)),
                () -> assertEquals(3, customArrayOfInts.get(2)),
                () -> assertEquals(3, customArrayOfInts.size(), "Size should be 3 after adding three elements")
        );
    }

    @Test
    void should_ThrowOutOfBound_When_IndexOutOfBounds() {
        // given
        customArrayOfInts.add(1);
        customArrayOfInts.add(2);
        // when
        Executable executable = () -> customArrayOfInts.get(5);
        // then
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    void should_deleteByValue_When_ValueFound() {
        // given
        customArrayOfInts.add(5);
        customArrayOfInts.add(17);
        customArrayOfInts.add(11);
        customArrayOfInts.add(0);
        customArrayOfInts.add(61);
        // when
        boolean foundAndRemoved = customArrayOfInts.deleteByValue(0);
        // then
        assertTrue(foundAndRemoved);
    }

    @Test
    void should_insertValue_When_IndexNotOutOfBounds() {
        // given
        customArrayOfInts.add(5);
        customArrayOfInts.add(17);
        customArrayOfInts.add(11);
        customArrayOfInts.add(0);
        customArrayOfInts.add(61);
        int[] expected = {17, 11, 0};
        // when
        int[] slicedData = customArrayOfInts.getSlice(1,3);
        // then
        assertArrayEquals(expected, slicedData);
    }


}