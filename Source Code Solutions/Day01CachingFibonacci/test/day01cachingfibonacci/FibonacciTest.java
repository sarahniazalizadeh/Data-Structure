
package day01cachingfibonacci;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import org.junit.Test;


public class FibonacciTest {
    
    // TODO: test exception on getNthFib(-1)
    
    @Test
    public void testGetNthFib() {
        System.out.println("getNthFib");
        int n = 0;
        Fibonacci instance = new Fibonacci(false);
        long expResult = 55L;
        long result = instance.getNthFib(10);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNthFibMany() {
        long expRes [] = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 };
        System.out.println("getNthFib");
        int n = 0;
        Fibonacci instance = new Fibonacci(false);
        for (int i = 0; i < expRes.length; i++) {
            long exp = expRes[i];
            long result = instance.getNthFib(i);
            assertEquals(exp, result);
        }
    }
    
    /**
     * Test of getCountOfFibsComputed method, of class Fibonacci.
     */
    @Test
    public void testGetCountOfFibsComputed() {
        System.out.println("getCountOfFibsComputed");
        Fibonacci instance = new Fibonacci(false);
        instance.getNthFib(10);
        int expResult = 90;
        int result = instance.getCountOfFibsComputed();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCountOfFibsComputedCache() {
        System.out.println("getCountOfFibsComputed");
        Fibonacci instance = new Fibonacci(true);
        instance.getNthFib(10);
        int expResult = 11;
        int result = instance.getCountOfFibsComputed();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of toString method, of class Fibonacci.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Fibonacci instance = new Fibonacci(true);
        instance.getNthFib(9);
        String expResult = "0, 1, 1, 2, 3, 5, 8, 13, 21, 34";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testToStringNocache() {
        System.out.println("toString");
        Fibonacci instance = new Fibonacci(false);
        instance.getNthFib(9);
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}
