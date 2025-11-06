package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeStringIntTest {
    BinaryTreeStringInt tree = new BinaryTreeStringInt();

    @BeforeEach
    void setUp() {
        tree.put("Ali", 40);
        tree.put("Samira", 28);
        tree.put("Liz", 31);
        tree.put("Jonathan", 81);
        tree.put("Yuji", 17);
        tree.put("Minh", 90);
        tree.put("Belinda", 38);
    }

    @Test
    void should_addValue_When_keyNotExist() {
        // given: setUp()
        // when
        tree.put("Carlos", 55);
        int value = tree.getValByKey("Carlos");
        // then
        assertEquals(55, value);
    }
}