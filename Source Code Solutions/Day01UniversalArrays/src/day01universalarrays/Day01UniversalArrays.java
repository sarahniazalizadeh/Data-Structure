package day01universalarrays;

import java.util.InputMismatchException;
import java.util.Scanner;

class ContainerOfInt {

    public ContainerOfInt(int value) {
        this.value = value;
    }

    int value;
}

public class Day01UniversalArrays {

    public static Scanner input = new Scanner(System.in);

    static int inputInt() {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException ex) {
                input.nextLine(); // consume the invalid input
                System.out.println("Not an integer, try again.");
            }
        }
    }

    static void inputArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("Enter value %d: ", i + 1);
            data[i] = inputInt();
        }
    }

    static void inputArray(int[][] data2d) {
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                System.out.printf("Enter value row %d column %d: ", row + 1, col + 1);
                data2d[row][col] = inputInt();
            }
        }
    }

    static void printArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%s%d", (i == 0 ? "" : ", "), data[i]);
        }
        System.out.println();
    }

    static void printArray(int[][] data2d) {
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                System.out.printf("%s%d", (col == 0 ? "" : ", "), data2d[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // dynamically adjust column sizes before printing
    static void printArraySmart(int[][] data2d) {
        // 1: find the maximum row length
        int maxRowLength = 0;
        for (int row = 0; row < data2d.length; row++) {
            // maxRowLength = Math.max(maxRowLength, data2d[row].length);
            maxRowLength = (maxRowLength > data2d[row].length) ? maxRowLength : data2d[row].length;
        }
        // 2: allocate colWidth array and fill it in with column widths (max string size of each cell)
        int[] colsWidth = new int[maxRowLength];
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                // String dataStr = data2d[row][col] + "";
                // int length = dataStr.length();
                int length = String.valueOf(data2d[row][col]).length();
                colsWidth[col] = Math.max(colsWidth[col], length);
            }
        }
        // 3: print data with columns appropriately sized
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                System.out.printf("%s%" + colsWidth[col] + "d", (col == 0 ? "" : ", "), data2d[row][col]);
            }
            System.out.println();
        }
    }

    static int[] findDuplicates(int[] a1, int[] a2) {
        int[] dups = new int[Math.min(a1.length, a2.length)];
        int dupsFound = 0;
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a2.length; j++) {
                if (a1[i] == a2[j]) {
                    // duplicate found but is it unique? (did we see it before?)
                    boolean isUnique = true;
                    for (int k = 0; k < dupsFound; k++) {
                        if (dups[k] == a1[i]) {
                            isUnique = false; // already in dups
                            break;
                        }
                    }
                    if (isUnique) {
                        dups[dupsFound++] = a1[i];
                    }
                }
            }
        }
        // allocate a new array and copy the final result to return it
        int[] finalDups = new int[dupsFound];
        for (int i = 0; i < dupsFound; i++) {
            finalDups[i] = dups[i];
        }
        return finalDups;
    }

    static void modifyInt(int val) {
        val++;
    }

    static void modifyIntContainer(ContainerOfInt valCont) {
        valCont.value++;
        valCont = new ContainerOfInt(3);
    }

    public static void main(String[] args) {
        int data2d[][] = {
            {-234234, 9, 4, 22},
            {3, 128, -2},
            {123, -7, 0, 1, 7}
        };

        printArray(data2d);
        System.out.println("-- smart print --");
        printArraySmart(data2d);
        /*
        int x = 5;
        modifyInt(x);
        System.out.println("x = " + x);

        ContainerOfInt coi = new ContainerOfInt(5);
        modifyIntContainer(coi);
        System.out.printf("coi.val=" + coi.value + "\n");
         */

 /*
        int[] array1D = new int[5];
        inputArray(array1D);
        printArray(array1D); */
    }

}
