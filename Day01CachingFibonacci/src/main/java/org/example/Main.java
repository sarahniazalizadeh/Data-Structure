package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Test 1: Caching ON ---");
        Fibonacci fibCacheOn = new Fibonacci(true);

        // F(5): Computes F(2) through F(5). New count: 4
        System.out.println("F(5): " + fibCacheOn.getNthFib(5)); // Expected: 5
        System.out.println("Total computed count after F(5): " + fibCacheOn.getCountOfFibsComputed()); // Expected: 2 + 4 = 6

        // F(3): Taken entirely from cache. (0 new values computed)
        System.out.println("F(3): " + fibCacheOn.getNthFib(3)); // Expected: 2
        System.out.println("Total computed count after F(3): " + fibCacheOn.getCountOfFibsComputed()); // Expected: 6 (no change)

        // F(10): Starts from F(5). Computes F(6) through F(10). (5 new values computed)
        System.out.println("F(10): " + fibCacheOn.getNthFib(10)); // Expected: 55
        System.out.println("Total computed count after F(10): " + fibCacheOn.getCountOfFibsComputed()); // Expected: 6 + 5 = 11

        // F(15): Starts from F(10). Computes F(11) through F(15). (5 new values computed)
        System.out.println("F(15): " + fibCacheOn.getNthFib(15)); // Expected: 610
        System.out.println("Total computed count after F(15): " + fibCacheOn.getCountOfFibsComputed()); // Expected: 11 + 5 = 16

        // Test a large index (F(92) is the highest F value before 'long' overflows)
        int largeN = 90;
        long fibN = fibCacheOn.getNthFib(largeN);

        System.out.println("\n--- Testing F(" + largeN + ") ---");
        // Starts from F(15). Computes F(16) through F(90). (75 new values computed)
        // Total computed count: 16 + 75 = 91

        // IMPORTANT FIX: Using String.valueOf() to print the long safely.
        System.out.println("F(" + largeN + "): " + String.valueOf(fibN));
        System.out.println("Total computed count after F(" + largeN + "): " + fibCacheOn.getCountOfFibsComputed());

        // Final check on cache contents
        System.out.println("\nCached Sequence (up to F(90)): " + fibCacheOn.toString().substring(0, 200) + "...");
    }
}