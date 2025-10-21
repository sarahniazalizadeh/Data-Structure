package org.example;

public class Main {
    public static void main(String[] args) {
        int[][] data2D = {
                {1, 3, 6, 8},
                {7, 1, 2, 3},
                {8, 3, 2, 1},
                {1, 7, 1, 9},
        };

        int[][] data2Djagged = {
                {1, 3, 6, 8, 9, 1},
                {7, 1, 2, 3},
                {8, 3, 2},
                {1, 7, 1, 9},
        };

        SumOfCross.sumOfCross(data2D, 1, 2);
    }
}