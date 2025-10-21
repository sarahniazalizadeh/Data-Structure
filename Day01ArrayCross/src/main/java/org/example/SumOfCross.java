package org.example;

public class SumOfCross {

    public static int sumOfCross(int[][] data, int row, int col) {
        int sum = data[row][col] + getIfExists(data, row-1, col) + getIfExists(data, row, col-1)
                        + getIfExists(data, row+1, col) + getIfExists(data, row, col+1);
        System.out.println("Sum of cross at (" + row + ", " + col + ") is: " + sum);
        return sum;
    }


    static int getIfExists(int[][] data, int row, int col) {
        // 1. Check if the array object itself is null
        if (data == null) {
            return 0;
        }

        // 2. Check the row boundary
        // row must be 0 or greater, AND less than the total number of rows (data.length)
        if (row < 0 || row >= data.length) {
            return 0;
        }

        // 3. Check if the specific row object is null (important for jagged arrays)
        if (data[row] == null) {
            return 0;
        }

        // 4. Check the column boundary for THIS specific row
        // col must be 0 or greater, AND less than the length of the current row (data[row].length)
        if (col < 0 || col >= data[row].length) {
            return 0;
        }

        // If we pass all checks, the element exists and can be safely returned.
        return data[row][col];
    }
}
