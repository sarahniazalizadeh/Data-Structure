package day01arraycross;

public class Day01ArrayCross {

    static int getIfExists(int[][] data, int row, int col) {
        // If exists, return the element, otherwise return 0
        try {
            // seems elegant, but some may argue a simple if would be better
            return data[row][col];
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    static int sumOfCross(int[][] data, int row, int col) {
        // return sum of the element at row/col
        // plus (if they exist) element above, below, to the left and right of it
        return getIfExists(data, row, col) + getIfExists(data, row + 1, col)
                + getIfExists(data, row, col + 1) + getIfExists(data, row - 1, col)
                + getIfExists(data, row, col - 1);
    }
    
    // useful helper for debugging
    static void print2D(int[][] data2d) {
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                System.out.printf("%s%d", (col == 0 ? "" : ", "), data2d[row][col]);
            }
            System.out.println();
        }
    }
    
     // returns: value, row, col array of 3 items
    static int[] getSmallestCrossSum(int[][] data2d) {
        int minVal = sumOfCross(data2d,0,0); // Integer.MAX_VALUE;
        int minRow = 0, minCol = 0;
        for (int row = 0; row < data2d.length; row++) {
            for (int col = 0; col < data2d[row].length; col++) {
                int valSum = sumOfCross(data2d, row, col);
                if (minVal > valSum) { // found a new minimum
                    minVal = valSum;
                    minRow = row;
                    minCol = col;
                }
            }
        }
        // in a more complete system you'd probably define a result class, instantiate and return it here
        int [] retVal = { minVal, minRow, minCol };
        return retVal;
    }
    
    // duplicate a jagged array
    static int[][] duplicateEmptyArray2D(int[][] orig2d) {
        int[][] copy2d = new int[orig2d.length][];
        for (int row = 0; row < copy2d.length; row++) {
            copy2d[row] = new int[orig2d[row].length];
        }
        return copy2d;
    }
    
    static void computeSumsInArray(int[][]source, int[][] target) {
        for (int row = 0; row < source.length; row++) {
            for (int col = 0; col < source[row].length; col++) {
                int sum = sumOfCross(source, row, col);
                target[row][col] = sum;
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] data2D = {
                {1, 3, 6, 8},
                {7, 1, 2, 3},
                {8, 3, 2, 1},
                {1, 7, 1, 9},
        };

        int[][] data2dJagged = {
                {1, 3, 6, 8, 9, 1},
                {7, 1, 2, 3},
                {8, 3, 2},
                {1, 7, 1, 9},
        };
        
        System.out.println("Original");
        print2D(data2dJagged);
        int[][] sum2d = duplicateEmptyArray2D(data2dJagged);
        computeSumsInArray(data2dJagged, sum2d);
        System.out.println("Sums");
        print2D(sum2d);
        int[] minFound = getSmallestCrossSum(data2dJagged);
        System.out.printf("Smallest value %d found at [%d,%d]\n", minFound[0], minFound[1], minFound[2]);
    }
    
}
