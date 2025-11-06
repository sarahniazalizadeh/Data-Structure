package day01cachingfibonacci;

public class Day01CachingFibonacci {

    final static int n = 50;
    
    public static void main(String[] args) {
        
        Fibonacci f1 = new Fibonacci(false);
        Fibonacci f2 = new Fibonacci(true);
        System.out.println("Non-cached fibonacci, 50th item:");
        long startTime1 = System.currentTimeMillis();
        System.out.println(f1.getNthFib(n));
        System.out.println("Time passed (miliseconds): " + (System.currentTimeMillis() - startTime1));
        long startTime2 = System.currentTimeMillis();
        System.out.println("Cached fibonacci, 50th item:");
        System.out.println(f2.getNthFib(n));
        System.out.println("Time passed (miliseconds): " + (System.currentTimeMillis() - startTime2));
        System.out.println("Done");
    }
    
}
