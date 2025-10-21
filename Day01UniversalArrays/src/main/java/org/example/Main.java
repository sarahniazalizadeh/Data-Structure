package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayUtils arrayUtils = new ArrayUtils();

        int[] data = new int[5];
        arrayUtils.inputArray(data);

        int[][] data2d = new int[4][3];
        arrayUtils.inputArray(data2d);

        int[] a1 = {1, 2, 3, 0, 3, 5};
        int[] a2 = {7, 5, 3, 3, 0};
        System.out.println("===============Concat Arrays");
        arrayUtils.printArray(arrayUtils.concatArrays(a1, a2));

        System.out.println("===============Find Duplicate 1D");
        arrayUtils.findDuplicates(a1, a2);

        int[][] a3 = {
                {1, 2, 3, 4},
                {4, 0},
        };
        int[][] a4 = {
                {3, 4},
                {0, 7, 1},
        };
        System.out.println("===============Find Duplicate 2D");
        arrayUtils.findDuplicates(a3, a4);
    }


}

