package com.healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void should_ReturnTrue_When_DietRecommended() {
        // given
        double height = 1.65;
        double weight = 81.2;
        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        // then
        assertTrue(recommended);
    }

    @Test
    void should_ReturnFalse_When_DietNotRecommended() {
        // given
        double height = 1.92;
        double weight = 50.0;
        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        // then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowArithmeticException_When_HeightZero() {
        // given
        double height = 0.0;
        double weight = 50.0;
        // when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
        // then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
        // given
        ArrayList<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.65, 81.2));
        coders.add(new Coder(1.92, 50.0));
        coders.add(new Coder(1.52, 98.7));
        // when
        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        // then
        assertAll (
                () -> assertEquals(1.52, coderWithWorstBMI.getHeight()),
                () -> assertEquals(98.7, coderWithWorstBMI.getWeight())
        );
    }

    @Test
    void should_ReturnNull_When_CoderListEmpty() {
        // given
        ArrayList<Coder> coders = new ArrayList<>();
        // when
        Coder coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        // then
        assertNull(coderWithWorstBMI);
    }

    @Test
    void should_ReturnArrayOfBMI_When_CoderListNotEmpty() {
        // given
        ArrayList<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};
        // when
        double[] bmiScores = BMICalculator.getBMIScores(coders);
        // then
        assertArrayEquals(expected, bmiScores);
    }
}