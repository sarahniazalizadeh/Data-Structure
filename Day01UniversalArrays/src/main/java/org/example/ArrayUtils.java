package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.min;

public class ArrayUtils {

    private static final Scanner input = new Scanner(System.in);

//    static int inputInt() {
//        while (true) {
//            try {
//                return input.nextInt();
//            } catch (InputMismatchException ex) {
//                input.nextInt();
//                System.out.println("Not an integer, try again:");
//            }
//        }
//    }

    static void inputArray(int[] data) {
        if (data == null) {
            System.out.println("Array is null");
            return;
        }

        for (int i = 0; i < data.length; i++) {
            System.out.println("Enter value " + (i + 1) + " out of " + data.length + ": ");
            if (input.hasNextInt()) {
                data[i] = input.nextInt();
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
                i--;
            }
        }
        printArray(data);
    }

    static void inputArray(int[][] data2d) {
        if (data2d == null) {
            System.out.println("Array is null");
            return;
        }
        for (int i = 0; i < data2d.length; i++) {
            for (int j = 0; j < data2d[i].length; j++) {
                System.out.println("Enter value row " + (i + 1) + " column " + (j + 1) + ": ");
                if (input.hasNextInt()) {
                    data2d[i][j] = input.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    input.next();
                    j--;
                }
            }
        }
        printArray(data2d);
    }

    static void printArray(int[] data) {
        System.out.println("Array contents:");
        if (data == null) {
            System.out.println("null");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    static void printArray(int[][] data2d) {
        if (data2d == null) {
            System.out.println("null");
            return;
        }
        int longestRowLength = 0;
        for (int i = 0; i < data2d.length; i++) {
//            int length = data2d[i].length;
//            if (length > longestRowLength) {
//                longestRowLength = length;
//            }
            longestRowLength = (data2d[i].length > longestRowLength) ? data2d[i].length : longestRowLength;
        }
        int[] maxLengthNumbers = new int[longestRowLength];

        System.out.println("Array contents:");

        for (int col = 0; col < longestRowLength; col++) {
            for (int row = 0; row < data2d.length; row++) {
                if (col < data2d[row].length) {

                    int length = String.valueOf(data2d[row][col]).length();
                    maxLengthNumbers[col] = (length > maxLengthNumbers[col]) ? length : maxLengthNumbers[col];
//                    if (length > maxLengthNumbers[col]) {
//                        maxLengthNumbers[col] = length;
//                    }
                }
            }
        }

        for (int i = 0; i < data2d.length; i++) {
            for (int j = 0; j < data2d[i].length; j++) {

                System.out.printf("%s%" + maxLengthNumbers[j] + "d", (j==0 ? "" : ", "), data2d[i][j]);
            }
//                if (j < data2d[i].length - 1) {
//                    System.out.print(", ");
//                }
//            }
//            if (i < data2d.length - 1) {
//                System.out.print("\n");
//            }

        }
        System.out.println("\n\ncomputed colsSizes: ");
        printArray(maxLengthNumbers);
    }

    static int[] concatArrays(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            System.out.println("One or more arrays are null");
            return null;
        }
        int[] result = new int[a1.length + a2.length];
        for (int i = 0; i < a1.length; i++) {
            result[i] = a1[i];
        }
        for (int j = 0; j < a2.length; j++) {
            result[a1.length + j] = a2[j];
        }
        return result;
    }

    static int[] findDuplicates(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            System.out.println("One or more arrays are null");
            return null;
        }
        int[] temp = new int[min(a1.length, a2.length)];
        int nextIndex = 0;
        for (int i : a1) {
            for (int j : a2) {
                if (i == j) {
                    boolean alreadyExists = false;

                    for (int k = 0; k < nextIndex; k++) {
                        if (temp[k] == i) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        temp[nextIndex++] = i;
                    }
                    break;
                }
            }
        }
        int[] result = new int[nextIndex];
        System.arraycopy(temp, 0, result, 0, nextIndex);
        printArray(result);
        return null;
    }

    static int[] findDuplicates(int[][] a1, int[][] a2) {
        if (a1 == null || a2 == null) {
            System.out.println("One or more arrays are null");
            return null;
        }
        int totalLength_a1 = 0;
        for (int i = 0; i < a1.length; i++) {
            totalLength_a1 += a1[i].length;
        }
        int totalLength_a2 = 0;
        for (int i = 0; i < a2.length; i++) {
            totalLength_a2 += a2[i].length;
        }

        int[] temp = new int[min(totalLength_a1, totalLength_a2)];
        int nextIndex = 0;
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) {
                for (int m = 0; m < a2.length; m++) {
                    for (int n = 0; n < a2[m].length; n++) {
                        if (a1[i][j] == a2[m][n]) {
                            boolean alreadyExists = false;

                            for (int k = 0; k < nextIndex; k++) {
                                if (temp[k] == a1[i][j]) {
                                    alreadyExists = true;
                                    break;
                                }

                            }
                            if (!alreadyExists) {
                                temp[nextIndex++] = a1[i][j];
                            }
                            break;

                        }
                    }
                }
            }
        }
        int[] result = new int[nextIndex];
        System.arraycopy(temp, 0, result, 0, nextIndex);
        printArray(result);
        return null;
    }
}
