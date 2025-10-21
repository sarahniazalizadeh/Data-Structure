package org.example;

import java.util.HashMap;

public class Fibonacci {

    private boolean isCacheOn;

    private HashMap<Integer, Long> fibsCached = new HashMap<>();

    Fibonacci(boolean cacheOn) {
        isCacheOn = cacheOn;
        fibsCached.put(0, 0L); // #0
        fibsCached.put(1, 1L); // #1
    }

    // when you implement caching, make this method print out the number of fib values it had to compute (as opposed to take from cache)
    public long getNthFib(int n) {
        if (isCacheOn && fibsCached.containsKey(n)) {
            return fibsCached.get(n);
        }
        long result = computeNthFib(n);

        int computed = n - getLargestCachedN();
        System.out.println("Computed " + computed + " new Fibonacci value(s) for F(" + n + ").");

        return result;
    }

    private int getLargestCachedN() {
        int maxN = 0;
        for (int key : fibsCached.keySet()) {
            if (key > maxN) {
                maxN = key;
            }
        }
        return maxN;
    }

    // You can find implementation online, recursive or non-recursive.
    // For 100% solution you should use values in fibsCached as a starting point
    // instead of always starting from the first two values of 0, 1.
    private long computeNthFib(int n) {
        if (n < 2) {
            return fibsCached.get(n);
        }
        int startN = getLargestCachedN();

        if (n <= startN) {
            return fibsCached.get(n);
        }

        long f_n_minus_2;
        long f_n_minus_1;

        if (startN == 0) {
            f_n_minus_2 = fibsCached.get(0);
            f_n_minus_1 = fibsCached.get(1);
        } else {
            f_n_minus_1 = fibsCached.get(startN);
            f_n_minus_2 = fibsCached.get(startN - 1);
        }

        long currentFib = 0L;

        int newlyComputedCount = n - startN;

        for (int i = startN + 1; i <= n; i++) {
            currentFib = f_n_minus_1 + f_n_minus_2;

            if (isCacheOn) {
                fibsCached.put(i, currentFib);
            }

            f_n_minus_2 = f_n_minus_1;
            f_n_minus_1 = currentFib;
        }
        return currentFib;
    }

    public int getCountOfFibsComputed() {
        return fibsCached.size();
    }

    @Override
    public String toString() {
        if (fibsCached.isEmpty()) {
            return "Cache is empty.";
        }

        int maxN = 0;
        for (int key : fibsCached.keySet()) {
            if (key > maxN) {
                maxN = key;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= maxN; i++) {
            if (fibsCached.containsKey(i)) {
                sb.append(fibsCached.get(i));
                if (i < maxN) {
                    sb.append(", ");
                }
            }
        }
        return sb.toString();

    }
}
