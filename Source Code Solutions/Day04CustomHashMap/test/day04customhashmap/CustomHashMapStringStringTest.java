package day04customhashmap;

// @Timeout(1) // will this work as global timeout ?

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CustomHashMapStringStringTest {
    
// maximum time given for any single test to execute
    // @Rule public Timeout globalTimeout = new Timeout(10*1000); // 10s max for any single test

    // @Timeout(1) // will this work ?
    @Test
    public void putGet_1() {
        CustomHashMapStringString map = new CustomHashMapStringString();
        map.putValue("Jerry", "Blue");
        map.putValue("Jerry123", "Blue");
        map.putValue("JerryAAA", "Blue");
        map.putValue("JerryBBB", "Blue");
        map.putValue("Jerry2", "Blue");
        map.putValue("JerryNN", "Blue");
        map.putValue("Jerry", "White");
        map.putValue("Jerry", "Yellow");
        map.putValue("Jerry", "Red");
        assertEquals("Blue", map.getValue("JerryNN"));
        assertEquals("Red", map.getValue("Jerry"));
        // while (true) { }
    }


    @Test
    public void customHashMapPutTest() {
        CustomHashMapStringString instance = new CustomHashMapStringString();
        instance.putValue("Jerry", "Blue");
        instance.putValue("Barry", "Violet");
        instance.putValue("Jerry", "Yellow");
        instance.putValue("Eva", "Green");
        assertEquals(3,instance.getSize());
        assertEquals("[Barry->Violet,Eva->Green,Jerry->Yellow]", instance.toString());
    }

    @Test
    public void customHashMapDeleteTest() {
        CustomHashMapStringString instance = new CustomHashMapStringString();
        instance.putValue("Jerry", "Blue");
        instance.putValue("Barry", "Violet");
        instance.putValue("Terry", "Yellow");
        instance.putValue("Eva", "Green");
        assertEquals(4,instance.getSize());
        assertEquals("[Barry->Violet,Eva->Green,Jerry->Blue,Terry->Yellow]", instance.toString());
        // boolean result1 = instance.deleteByKey("Jimmy"); exception - create a separate method to test it
        assertEquals(4,instance.getSize());
        instance.deleteByKey("Jerry"); // no exception
        assertEquals(3,instance.getSize());
        assertEquals("[Barry->Violet,Eva->Green,Terry->Yellow]", instance.toString());
    }

    @Test
    public void printDebugTest() {
        String expected = "Entry 0:\nEntry 1:\n- Key Barry, Value Violet\nEntry 2:\n" +
                "- Key Jerry, Value Blue\nEntry 3:\n- Key Terry, Value Yellow\n" +
                "Entry 4:\n- Key Eva, Value Green\n";
        PrintStream standardOut = System.out;
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            CustomHashMapStringString instance = new CustomHashMapStringString();
            instance.putValue("Jerry", "Blue");
            instance.putValue("Barry", "Violet");
            instance.putValue("Terry", "Yellow");
            instance.putValue("Eva", "Green");
            instance.printDebug(); // writes to outputStreamCaptor
            assertEquals(expected, outputStreamCaptor.toString());
        } finally {
            System.setOut(standardOut);
        }
    }

    
}
