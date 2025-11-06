package day01cachingfibonacci;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private boolean isCacheOn;

    public Fibonacci(boolean cacheOn) {
        isCacheOn = cacheOn;
        if (isCacheOn) {
            fibsCached.put(0, 0L); // #0
            fibsCached.put(1, 1L); // #1
        }
    }

    private HashMap<Integer, Long> fibsCached = new HashMap<>();
    private int fibsCompCount = 2; // may not be needed
    // in a correct caching implementation fibsCompCount will end up the same as fibsCached.size();

    // when you implement caching, make this method print out the number of fib values it had to compute (as opposed to take from cache)
    public long getNthFib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("No negative fibonacci exist");
        }
        return computeNthFib(n);
    }

    // You can find implementation online, recursive or non-recursive.
    // For 100% solution you should use values in fibsCached as a starting point
    // instead of always starting from the first two values of 0, 1.
    private long computeNthFib(int n) {
        if (isCacheOn && fibsCached.containsKey(n)) { // O(1)
            return fibsCached.get(n); // O(1)
        }
        if (n <= 1) { // 0 => 0, 1 => 1
            return n;
        }
        fibsCompCount++;
        long NthFib = computeNthFib(n - 1) + computeNthFib(n - 2);
        if (isCacheOn) {
            fibsCached.put(n,NthFib); // remember the fib you just computed
        }
        return NthFib;
    }

    // You are allowed to add another private method for fibonacci generation
    // if you want to use recursive approach. I recommend non-recursive though.
    // How many fibonacci numbers has your code computed as opposed to returned cached?
    // Use this in your testing to make sure your caching actually works properly.
    public int getCountOfFibsComputed() {
        return fibsCompCount;
    }

    @Override
    public String toString() {
        // returns all cached Fib values, comma-space-separated on a single line
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<Integer, Long> entry : fibsCached.entrySet()) {
            if (first) {
                result.append(String.format("%d", entry.getValue()));
                first = false;
            } else {
                result.append(String.format(", %d", entry.getValue()));
            }
        }
        return result.toString();
    }
}
